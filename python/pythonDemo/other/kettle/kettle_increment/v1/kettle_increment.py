# @Time: 2024年06月04日 10:14
# @Author: orcakill
# @File: kettle_increment.py
# @Description: 根据数据库配置文件获取库里所有的表，生成所有的增量更新sql,然后将增量更新sql生成ktr文件
import configparser
import math
import os

import chardet
import cx_Oracle
from loguru import logger

from other.kettle.kettle_increment.v1.kettle_str import KettleStr


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
    dict1 = {'database_type': config.get(ini_name, "database_type"),
             'ip': config.get(ini_name, "ip"),
             'port': config.get(ini_name, "port"),
             'servername': config.get(ini_name, "servername"),
             'username': config.get(ini_name, "username"),
             'password': config.get(ini_name, "password"),
             'password1': config.get(ini_name, "password1"),
             }
    return dict1


def get_tables(database_info):
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
    # connection = cx_Oracle.connect('用户名/密码@主机名:端口号/服务名')
    connection_txt = username + f'/' + password + '@' + ip + ':' + port + f'/' + server_name
    logger.info(connection_txt)
    connection = cx_Oracle.connect(connection_txt)

    # 创建游标
    cursor = connection.cursor()

    # 获取数据库中的所有表
    cursor.execute(
        "SELECT table_name from (SELECT table_name FROM user_tables  order by table_name) where table_name not like 'YXYY_%' order by table_name")

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


def deal_connection(database_info):
    """
    处理数据库连接
    """
    str_connection = ""
    database_type = database_info['database_type']
    ip = database_info['ip']
    port = database_info['port']
    servername = database_info['servername']
    username = database_info['username']
    password = database_info['password1']
    if database_type == 'oracle':
        str_connection = ('  <connection>\n'
                          + '    <name>' + database_type + '_' + username + '</name>\n'
                          + '    <server>' + ip + '</server>\n'
                          + '    <type>ORACLE</type>\n'
                          + '    <access>Native</access>\n'
                          + '    <database>' + servername + '</database>\n'
                          + '    <port>' + port + '</port>\n'
                          + '    <username>' + username + '</username>\n'
                          + '    <password>' + password + '</password>\n'
                          )
        str_connection = str_connection + KettleStr.str_connection_oracle
    elif database_type == 'dm':
        str_connection = ('  <connection>\n'
                          + '    <name>' + database_type + '_' + username + '</name>\n'
                          + '    <server/>\n'
                          + '    <type>GENERIC</type>\n'
                          + '    <access>Native</access>\n'
                          + '    <database/>\n'
                          + '    <port>1521</port>\n'
                          + '    <username>' + username + '</username>\n'
                          + '    <password>' + password + '</password>\n'
                          + '    <servername/>\n'
                          + '    <data_tablespace/>\n'
                          + '    <index_tablespace/>\n'
                          + '    <attributes>\n'
                          + '      <attribute>\n'
                          + '        <code>CUSTOM_DRIVER_CLASS</code>\n'
                          + '        <attribute>dm.jdbc.driver.DmDriver</attribute>\n'
                          + '      </attribute>\n'
                          + '      <attribute>\n'
                          + '        <code>CUSTOM_URL</code>\n'
                          + '        <attribute>jdbc:dm://' + ip + ':' + port + '/</attribute>\n'
                          )
        str_connection = str_connection + KettleStr.str_connection_dm
    return str_connection


def deal_order(table_infos):
    """
    处理排序和连接
    """
    # 循环处理order 的内容
    str_order = "  <order>\n"
    for index, (key, value) in enumerate(table_infos.items(), start=1):
        table_name = key
        nr = "\r\n"
        max_index = len(table_infos)
        if index == 1 or index == max_index:
            nr = "\n"
        str_order = (str_order
                     + '    <hop>' + nr
                     + '      <from>' + table_name + ' 表输入</from>' + nr
                     + '      <to>' + table_name + ' 插入 / 更新</to>' + nr
                     + '      <enabled>Y</enabled>' + nr
                     + '    </hop>\n'
                     )
    str_order = (str_order + "  </order>\n")
    return str_order


def deal_step(table_infos, database_info1, database_info2):
    str_step = ""
    # 循环表,先插入更新再表输入
    for index, (key, value) in enumerate(table_infos.items(), start=1):
        str_table_input = ""
        str_table_insert = ""
        connect_name = database_info2['database_type'] + '_' + database_info2['username']
        table_name = key
        # 插入更新
        str_table_insert = (str_table_insert
                            + '  <step>\n'
                            + '    <name>' + table_name + ' 插入 / 更新</name>\n'
                            + '    <type>InsertUpdate</type>\n'
                            + '    <description/>\n'
                            + '    <distribute>Y</distribute>\n'
                            + '    <custom_distribution/>\n'
                            + '    <copies>1</copies>\n'
                            + '    <partitioning>\n'
                            + '      <method>none</method>\n'
                            + '      <schema_name/>\n'
                            + '    </partitioning>\n'
                            + '    <connection>' + connect_name + '</connection>\n'
                            + '    <commit>100</commit>\n'
                            + '    <update_bypassed>N</update_bypassed>\n'
                            + '    <lookup>\n'
                            + '      <schema/>\n'
                            + '      <table>' + table_name + '</table>\n')
        # 循环字段
        table_info = table_infos[table_name]
        for j in range(len(table_info)):
            column_pk = table_info[j][5]
            column_name = table_info[j][3]
            if column_pk == 'Y':
                str_table_insert = (str_table_insert
                                    + '      <key>\n'
                                    + '        <name>' + column_name + '</name>\n'
                                    + '        <field>' + column_name + '</field>\n'
                                    + '        <condition>=</condition>\n'
                                    + '        <name2/>\n'
                                    + '      </key>\n'
                                    )
        for j in range(len(table_info)):
            column_name = table_info[j][3]
            str_table_insert = (str_table_insert
                                + '      <value>\n'
                                + '        <name>' + column_name + '</name>\n'
                                + '        <rename>' + column_name + '</rename>\n'
                                + '        <update>Y</update>\n'
                                + '      </value>\n'
                                )
        str_table_insert = (str_table_insert
                            + '    </lookup>\n'
                            + '    <attributes/>\n'
                            + '    <cluster_schema/>\n'
                            + '    <remotesteps>\n'
                            + '      <input>\n'
                            + '      </input>\n'
                            + '      <output>\n'
                            + '      </output>\n'
                            + '    </remotesteps>\n'
                            + '    <GUI>\n'
                            + '      <xloc>600</xloc>\n'
                            + '      <yloc>' + str(index * 100) + '</yloc>\n'
                            + '      <draw>Y</draw>\n'
                            + '    </GUI>\n'
                            + '  </step>\n'
                            )
        # 表输入
        str_table_input = (str_table_input + '  <step>\n'
                           + '    <name>' + table_name + ' 表输入</name>\n'
                           + '    <type>TableInput</type>\n'
                           + '    <description/>\n'
                           + '    <distribute>Y</distribute>\n'
                           + '    <custom_distribution/>\n'
                           + '    <copies>1</copies>\n'
                           + '    <partitioning>\n'
                           + '      <method>none</method>\n'
                           + '      <schema_name/>\n'
                           + '    </partitioning>\n'
                           + '    <connection>' + database_info1['database_type'] + '_' + database_info1[
                               'username'] + '</connection>\n'
                           + '    <sql>select\r\n'
                           )
        table_info = table_infos[table_name]
        for j in range(len(table_info)):
            if j == len(table_info) - 1:
                str_table_input = str_table_input + '       ' + table_info[j][3].lower() + '\n'
            else:
                str_table_input = str_table_input + '       ' + table_info[j][3].lower() + ',\n'
        str_table_input = (str_table_input + '  from ' + table_name + '\n')
        table_column = []
        for column in table_info:
            table_column.append(column[3])
        if 'RQ' in table_column:
            str_table_input = (
                    str_table_input + '  where \'${v_rq}\' is null or rq=to_date(\'${v_rq}\',\'yyyy-mm-dd\')\n')
        if 'NY' in table_column:
            str_table_input = (str_table_input + '  where \'${v_ny}\' is null or ny=\'${v_ny}\'\n')
        str_table_input = (str_table_input + '</sql>\n'
                           + '    <limit>0</limit>\n'
                           + '    <lookup/>\n'
                           + '    <execute_each_row>N</execute_each_row>\n'
                           + '    <variables_active>Y</variables_active>\n'
                           + '    <lazy_conversion_active>N</lazy_conversion_active>\n'
                           + '    <cached_row_meta_active>N</cached_row_meta_active>\n'
                           + '    <row-meta>\n')
        for j in range(len(table_info)):
            column_name = table_info[j][3]
            column_type = table_info[j][7]
            column_length = str(table_info[j][8])
            precision = -1
            conversion_Mask = '        <conversion_Mask/>\n'
            if column_type.upper() in ['NVARCHAR2', 'VARCHAR', 'VARCHAR2']:
                if column_type in ['NVARCHAR2']:
                    column_length = str(int(int(column_length) / 2))
                column_type = 'String'
            if column_type.upper() in ['NUMBER']:
                column_type = 'Integer'
                precision = 0
                conversion_Mask = '        <conversion_Mask>####0;-####0</conversion_Mask>\n'
            if column_type.upper() in ['DATE']:
                column_type = 'Timestamp'
                column_length = str(0)
            if column_type.upper() in ['TIMESTAMP(6)']:
                column_type = 'None'
            if column_type.upper() in ['INTEGER']:
                column_length = str(38)
            if column_type.upper() in ['BLOB']:
                column_type = 'Binary'
                column_length = str(-1)
            str_table_input = (str_table_input
                               + '      <value-meta>\n'
                               + '        <type>' + column_type + '</type>\n'
                               + '        <storagetype>normal</storagetype>\n'
                               + '        <name>' + column_name + '</name>\n'
                               + '        <length>' + column_length + '</length>\n'
                               + '        <precision>' + str(precision) + '</precision>\n'
                               + '        <origin>表输入</origin>\n'
                               + '        <comments>' + column_name + '</comments>\n'
                               + conversion_Mask
                               + '        <decimal_symbol>.</decimal_symbol>\n'
                               + '        <grouping_symbol>,</grouping_symbol>\n'
                               + '        <currency_symbol/>\n'
                               + '        <trim_type>none</trim_type>\n'
                               + '        <case_insensitive>N</case_insensitive>\n'
                               + '        <collator_disabled>Y</collator_disabled>\n'
                               + '        <collator_strength>0</collator_strength>\n'
                               + '        <sort_descending>N</sort_descending>\n'
                               + '        <output_padding>N</output_padding>\n'
                               + '        <date_format_lenient>N</date_format_lenient>\n'
                               + '        <date_format_locale>zh_CN</date_format_locale>\n'
                               + '        <date_format_timezone>Asia/Shanghai</date_format_timezone>\n'
                               + '        <lenient_string_to_number>N</lenient_string_to_number>\n'
                               + '      </value-meta>\n')
        str_table_input = (str_table_input
                           + '    </row-meta>\n'
                           + '    <attributes/>\n'
                           + '    <cluster_schema/>\n'
                           + '    <remotesteps>\n'
                           + '      <input>\n'
                           + '      </input>\n'
                           + '      <output>\n'
                           + '      </output>\n'
                           + '    </remotesteps>\n'
                           + '    <GUI>\n'
                           + '      <xloc>200</xloc>\n'
                           + '      <yloc>' + str(index * 100) + '</yloc>\n'
                           + '      <draw>Y</draw>\n'
                           + '    </GUI>\n'
                           + '  </step>\n'
                           )
        str_step = str_step + str_table_insert + str_table_input
    return str_step


def create_kettle():
    logger.info("开始")
    logger.info("获取来源库信息")
    database_info1 = get_database_info('database1')
    logger.info("获取目标库信息")
    database_info2 = get_database_info('database2')
    logger.info("获取来源库表信息")
    table_infos = get_tables(database_info1)
    # xml
    file_numer = math.ceil(len(table_infos) / 50)
    logger.info("处理ktr文件内容,每50个表一组,共{}组", file_numer)
    # 按照每组50个数进行分组
    table_infos_group = []
    for i in range(file_numer):
        grouped_dict = {}
        for index, (key, value) in enumerate(table_infos.items(), start=1):
            if (i) * 50 < index <= ((i + 1) * 50):
                grouped_dict[key] = value
        table_infos_group.append(grouped_dict)
    for i in range(file_numer):
        logger.info("第{}个ktr", i + 1)
        table_infos_dict = table_infos_group[i]
        str_xml = KettleStr.str_xml
        str_info = KettleStr.str_info
        str_connection = deal_connection(database_info2) + deal_connection(database_info1)
        str_order = deal_order(table_infos_dict)
        str_step = deal_step(table_infos_dict, database_info1, database_info2)
        str_step_error_handling = KettleStr.str_step_error_handling
        str_transformation = str_info + str_connection + str_order + str_step + str_step_error_handling
        str_all = str_xml + str_transformation
        logger.info("生成ktr文件")
        # 指定文件名和后缀
        file_name = "kettle_increment_" + str(i + 1)
        file_extension = ".ktr"
        str_all = str_all.encode('utf-8')

        # 将字符保存到文件中
        with open(file_name + file_extension, "wb") as file:
            file.write(str_all)

    logger.info("结束")


def deal_example_ktr():
    logger.info("读取文件")
    file_path = "D:/测试2.txt"
    with open(file_path, 'r', encoding='utf-8') as file:
        for line in file:
            print(f"+{line.rstrip()}\\n\\r'")


def compare_file():
    """
    对比2个文件
    """
    file1 = 'kettle_increment.ktr'
    file2 = 'oracledm测试.ktr'
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


def detect_encoding(file_path):
    with open(file_path, 'rb') as f:
        rawdata = f.read()
        result = chardet.detect(rawdata)
        return result['encoding']


if __name__ == '__main__':
    # deal_example_ktr()
    create_kettle()
    # compare_file()
