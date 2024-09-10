# @Time: 2024年06月04日 10:14
# @Author: orcakill
# @File: kettle_increment.py
# @Description: 根据数据库配置文件获取库里所有的表，生成所有的增量更新sql,然后将增量更新sql生成ktr文件
import configparser
import math
import os
import sys

import chardet
import cx_Oracle
from loguru import logger

from kettle_str import KettleStr


def get_project_path():
    """
    获取项目路径
    :return: 项目根路径
    """
    # 获取当前文件的绝对路径
    current_directory = os.path.dirname(os.path.realpath(sys.argv[0]))
    logger.info("文件路径：{}", current_directory)
    return current_directory


def get_database_info(ini_name):
    """
    获取数据库连接信息
    :return: 数据库连接信息
    """
    root_path = get_project_path()
    config_path = root_path + "\\kettle_config.ini"
    config = configparser.ConfigParser()
    config.read(config_path, encoding="utf-8")
    dict1 = {'connect_name': config.get(ini_name, "connect_name"),
             'database_type': config.get(ini_name, "database_type"),
             'ip': config.get(ini_name, "ip"),
             'port': config.get(ini_name, "port"),
             'servername': config.get(ini_name, "servername"),
             'username': config.get(ini_name, "username"),
             'password': config.get(ini_name, "password")
             }
    return dict1


def get_normal_info(ini_name):
    """
    获取数据库常规连接信息
    :return: 数据库连接信息
    """
    root_path = get_project_path()
    config_path = root_path + "\\kettle_config.ini"
    config = configparser.ConfigParser()
    config.read(config_path, encoding="utf-8")
    dict1 = {'basic_database': config.get(ini_name, "basic_database"),
             'database_equally': config.get(ini_name, "database_equally"),
             'database_read': config.get(ini_name, "database_read"),
             'file_name_prefix': config.get(ini_name, "file_name_prefix"),
             'mode_state': config.get(ini_name, "mode_state"),
             'parameter_settings': config.get(ini_name, "parameter_settings"),
             'default_month': config.get(ini_name, "default_month"),
             'default_date': config.get(ini_name, "default_date"),
             'group_number': config.get(ini_name, "group_number"),
             'kettle_path': config.get(ini_name, "kettle_path"),
             'select_table_name': config.get(ini_name, "select_table_name"),
             'no_table_name': config.get(ini_name, "no_table_name")
             }
    return dict1


def get_tables(normal_info, database_info):
    """
    根据数据库连接信息，获取表、字段、是否为主键
    :return: 数据库连接信息
    """
    # 连接到Oracle数据库
    ip = database_info["ip"]
    port = database_info["port"]
    server_name = database_info["servername"]
    username = database_info["username"]
    password = database_info["password"]
    select_table_name = normal_info["select_table_name"]
    no_table_name = normal_info["no_table_name"]
    # connection = cx_Oracle.connect('用户名/密码@主机名:端口号/服务名')
    connection_txt = username + f'/' + password + '@' + ip + ':' + port + f'/' + server_name
    logger.info(connection_txt)
    connection = cx_Oracle.connect(connection_txt)

    # 创建游标
    cursor = connection.cursor()

    sql_select = ""
    sql_no = ""
    if select_table_name:
        sql_select = " and table_name in (" + select_table_name + ') '
    if no_table_name:
        sql_no = " and table_name  not in (" + no_table_name + '） '
    # 获取数据库中的所有表
    table_sql = ("SELECT a.table_name as table_name from "
                 + "(SELECT a.table_name as table_name FROM user_tables a  order by table_name) a where 1=1"
                 + sql_select
                 + sql_no
                 + "order by a.table_name")
    cursor.execute(table_sql)
    logger.info("获取待生成表的sql语句")
    logger.debug(table_sql)

    tables = cursor.fetchall()
    logger.info("来源库数据表数：{}", len(tables))

    # 用于存储表信息的字典
    table_info = {}

    for i in range(len(tables)):
        table_name = tables[i][0]
        logger.info("当前数据表：第{}个,表{}", i + 1, table_name)
        sql = ('select   a.table_name as table_name,\n'
               + '       (select a1.comments\n'
               + '          from user_tab_comments a1\n'
               + '         where a1.table_name = a.table_name) table_comment,\n'
               + '       a.column_id column_id,\n'
               + '       a.column_name as column_name,\n'
               + '       b.comments as column_comments,\n'
               + '       case\n'
               + '         when a.column_name in\n'
               + '              (select cu.column_name\n'
               + '                 from user_cons_columns cu, user_constraints au\n'
               + '                where cu.constraint_name = au.constraint_name\n'
               + "                  and au.constraint_type = 'P'\n"
               + '                  and au.table_name = a.table_name) then\n'
               + "          'Y'\n"
               + '         else\n'
               + "          'N'\n"
               + '       end pk,\n'
               + '       a.nullable as nullable,\n'
               + '       a.data_type data_type,\n'
               + '       case\n'
               + "         when a.data_type = 'NUMBER' then\n"
               + '          a.data_precision\n'
               + '         else\n'
               + '          a.data_length\n'
               + '       end data_length,\n'
               + '       nvl(a.data_scale, 0) data_scale\n'
               + '  from user_tab_columns a, user_col_comments b\n'
               + ' where a.table_name=b.table_name\n'
               + '   and a.column_name = b.column_name\n'
               + '   and a.table_name=\'' + table_name + '\'\n'
               + ' order by a.table_name, a.column_id\n')

        # 获取表中的所有列
        cursor.execute(sql)
        columns = cursor.fetchall()
        table_info[table_name] = columns

    # 关闭游标和连接
    cursor.close()
    connection.close()

    return table_info


def deal_two_connection(normal_info, database_info1, database_info2):
    """
    根据字符顺序排序2个数据连接
    """
    connect_name1 = database_info1['connect_name']
    connect_name2 = database_info2['connect_name']
    deal_connection1 = KettleStr.deal_connection(normal_info, database_info1, True)
    deal_connection2 = KettleStr.deal_connection(normal_info, database_info2, False)
    if connect_name1 > connect_name2:
        str_two_connection = deal_connection2 + deal_connection1
    elif connect_name1 == connect_name2:
        str_two_connection = deal_connection2
    else:
        str_two_connection = deal_connection1 + deal_connection2
    return str_two_connection


def compare_file():
    """
    对比2个文件
    """
    file1 = 'kettle_increment_1.ktr'
    file2 = '参考文件3.ktr'
    logger.info("开始对比")
    with open(file1, 'r', encoding='utf-8', newline='') as f1, open(file2, 'r', encoding='utf-8', newline='') as f2:
        lines1 = f1.readlines()
        lines2 = f2.readlines()
        for i in range(len(lines1)):
            if i == len(lines2) - 1:
                break
            line1 = repr(lines1[i])
            line2 = repr(lines2[i])
            if lines1[i] != lines2[i]:
                print(str(i + 1) + "【" + line1 + "】【" + line2 + "】")
    logger.info("结束对比")


def detect_encoding(file_path):
    with open(file_path, 'rb') as f:
        rawdata = f.read()
        result = chardet.detect(rawdata)
        return result['encoding']


def deal_example_ktr():
    logger.info("读取文件")
    file_path = "D:/测试2.txt"
    with open(file_path, 'r', encoding='utf-8') as file:
        for line in file:
            print(f"+'{line.rstrip()}\\n\\r'")


def create_kettle():
    logger.info("开始")
    logger.info("获取常规信息")
    normal_info = get_normal_info('normal')
    logger.info("获取来源库信息")
    database_info1 = get_database_info('database1')
    logger.info("获取目标库信息")
    database_info2 = get_database_info('database2')
    logger.info("获取库信息")
    if (normal_info['basic_database'] == 'database1'
            and database_info1['connect_name'] != database_info2['connect_name']):
        logger.info("以来源库表为准")
        table_infos = get_tables(normal_info, database_info1)
    else:
        logger.info("以目标库表为准")
        table_infos = get_tables(normal_info, database_info2)
    # xml
    file_numer = math.ceil(len(table_infos) / normal_info['group_number'])
    logger.info("处理ktr文件内容,每50个表一组,共{}组", file_numer)
    # 按照每组50个数进行分组
    table_infos_group = []
    for i in range(file_numer):
        grouped_dict = {}
        for index, (key, value) in enumerate(table_infos.items(), start=1):
            if i * 50 < index <= ((i + 1) * 50):
                grouped_dict[key] = value
        table_infos_group.append(grouped_dict)
    file_name_prefix = normal_info['file_name_prefix']
    mode_state = normal_info['mode_state']
    for i in range(file_numer):
        file_name = file_name_prefix + str(i + 1)
        logger.info("第{}个ktr", i + 1)
        table_infos_dict = table_infos_group[i]
        str_xml = KettleStr.str_xml
        str_info = KettleStr.str_info1 + '    <name>' + file_name + '</name>\n' + KettleStr.str_info2
        str_parameter = KettleStr.deal_parameter(normal_info, '') + KettleStr.str_info3
        # 处理数据库连接
        str_connection = deal_two_connection(normal_info, database_info1, database_info2)
        str_order = KettleStr.deal_order(table_infos_dict, mode_state)
        str_step = KettleStr.deal_step(table_infos_dict, normal_info, database_info1, database_info2)
        str_step_error_handling = KettleStr.deal_step_error_handling(table_infos_dict, normal_info, database_info2)
        str_transformation = str_info + str_parameter + str_connection + str_order + str_step + str_step_error_handling
        str_all = str_xml + str_transformation
        logger.info("生成ktr文件")
        # 指定文件名和后缀
        file_extension = ".ktr"
        str_all = str_all.encode('utf-8')

        # 将字符保存到文件中
        with open(file_name + file_extension, "wb") as file:
            file.write(str_all)

    logger.info("结束")


if __name__ == '__main__':
    # deal_example_ktr()
    create_kettle()
    compare_file()

