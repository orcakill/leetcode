# @Time: 2024年06月04日 10:14
# @Author: orcakill
# @File: kettle_increment.py
# @Description: 根据数据库配置文件获取库里所有的表，生成所有的增量更新sql,然后将增量更新sql生成ktr文件
import configparser
import os

import cx_Oracle
from loguru import logger

from other.kettle.kettle_increment.kettle_str import KettleStr


def get_project_path():
    """
    获取项目路径
    :return: 项目根路径
    """
    # 获取当前文件的绝对路径
    p_path = os.path.abspath(os.path.dirname(__file__))
    return p_path


def get_database_info(ini_name):
    """
    获取数据库连接信息
    :return: 数据库连接信息
    """
    root_path = get_project_path()
    config_path = root_path + "\\kettle_config.ini"
    config = configparser.ConfigParser()
    config.read(config_path, encoding="utf-8")
    dict1 = {'type': config.get(ini_name, "type"),
             'ip': config.get(ini_name, "ip"),
             'port': config.get(ini_name, "port"),
             'server_name': config.get(ini_name, "server_name"),
             'username': config.get(ini_name, "username"),
             'password': config.get(ini_name, "password")}
    return dict1


def get_tables(database_info):
    """
    根据数据库连接信息，获取表、字段、是否为主键
    :return: 数据库连接信息
    """
    # 连接到Oracle数据库
    ip = database_info["ip"]
    port = database_info["port"]
    server_name = database_info["server_name"]
    username = database_info["username"]
    password = database_info["password"]
    # connection = cx_Oracle.connect('用户名/密码@主机名:端口号/服务名')
    connection_txt = username + f'/' + password + '@' + ip + ':' + port + f'/' + server_name
    logger.info(connection_txt)
    connection = cx_Oracle.connect(connection_txt)

    # 创建游标
    cursor = connection.cursor()

    # 获取数据库中的所有表
    cursor.execute("SELECT table_name FROM user_tables order by table_name")

    tables = cursor.fetchall()

    # 用于存储表信息的字典
    table_info = {}

    for table in tables:
        table_name = table[0]

        # 获取表中的所有列
        cursor.execute(f"SELECT column_name, data_type FROM user_tab_columns WHERE table_name = '{table_name}'")
        columns = cursor.fetchall()

        # 检查列是否为主键
        primary_key_info = {}
        cursor.execute(f"SELECT column_name FROM user_cons_columns WHERE table_name = '{table_name}'")
        primary_keys = [pk[0] for pk in cursor.fetchall()]

        for column in columns:
            column_name = column[0]
            if column_name in primary_keys:
                primary_key_info[column_name] = True
            else:
                primary_key_info[column_name] = False

        table_info[table_name] = primary_key_info

    # 关闭游标和连接
    cursor.close()
    connection.close()

    return table_info


def create_kettle():
    database_info1 = get_database_info('database1')
    database_info2 = get_database_info('database2')
    table_info = get_tables(database_info1)
    # xml
    str_xml = KettleStr.str_xml
    str_info = KettleStr.str_info
    str_connection = KettleStr.str_info
    str_order = "'  <order>\n\r'"
    # 循环处理order 的内容
    for table in table_info:
        table_name = table
        str_order = (str_order
                     + '    <hop>\n\r'
                     + '      <from>' + table_name + ' 表输入</from>\n\r'
                     + '      <to>' + table_name + ' 插入 / 更新</to>\n\r'
                     + '      <enabled>Y</enabled>\n\r'
                     + '    </hop>\n\r'
                     )

    str_step = ""
    str_step_error_handling = ('  <slave-step-copy-partition-distribution>\n\r'
                               + '  </slave-step-copy-partition-distribution>\n\r'
                               + '  <slave_transformation>N</slave_transformation>\n\r'
                               + '  <attributes/>\n\r'
                               + '</transformation>')
    str_transformation = str_info + str_connection + str_order + str_step + str_step_error_handling
    str_all = str_xml + str_transformation
    print(str_all)


def deal_example_ktr():
    logger.info("读取ktr文件")
    file_path = "D:/测试.txt"
    with open(file_path, 'r', encoding='utf-8') as file:
        for line in file:
            print(f"+'{line.rstrip()}\\n\\r'")


if __name__ == '__main__':
    create_kettle()
