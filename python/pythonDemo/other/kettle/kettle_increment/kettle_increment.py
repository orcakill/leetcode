# @Time: 2024年06月04日 10:14
# @Author: orcakill
# @File: kettle_increment.py
# @Description: 根据数据库配置文件获取库里所有的表，生成所有的增量更新sql,然后将增量更新sql生成ktr文件
import configparser
import os

import cx_Oracle
from loguru import logger


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
    str_xml = '<?xml version="1.0" encoding="UTF-8"?>\n\r'
    str_info = ('  <info>\n\r'
                + '    <name>测试</name>\n\r'
                + '    <description/>\n\r'
                + '    <extended_description/>\n\r'
                + '    <trans_version/>\n\r'
                + '    <trans_type>Normal</trans_type>\n\r'
                + '    <trans_status>0</trans_status>\n\r'
                + '    <directory>/</directory>\n\r'
                + '    <parameters>\n\r'
                + '      <parameter>\n\r'
                + '        <name>v_ny</name>\n\r'
                + '        <default_value>202401</default_value>\n\r'
                + '        <description/>\n\r'
                + '      </parameter>\n\r'
                + '      <parameter>\n\r'
                + '        <name>v_rq</name>\n\r'
                + '        <default_value>2024-04-01</default_value>\n\r'
                + '        <description/>\n\r'
                + '      </parameter>\n\r'
                + '    </parameters>\n\r'
                + '    <log>\n\r'
                + '      <trans-log-table>\n\r'
                + '        <connection/>\n\r'
                + '        <schema/>\n\r'
                + '        <table/>\n\r'
                + '        <size_limit_lines/>\n\r'
                + '        <interval/>\n\r'
                + '        <timeout_days/>\n\r'
                + '        <field>\n\r'
                + '          <id>ID_BATCH</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>ID_BATCH</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>CHANNEL_ID</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>CHANNEL_ID</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>TRANSNAME</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>TRANSNAME</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>STATUS</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>STATUS</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_READ</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_READ</name>\n\r'
                + '          <subject/>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_WRITTEN</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_WRITTEN</name>\n\r'
                + '          <subject/>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_UPDATED</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_UPDATED</name>\n\r'
                + '          <subject/>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_INPUT</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_INPUT</name>\n\r'
                + '          <subject/>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_OUTPUT</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_OUTPUT</name>\n\r'
                + '          <subject/>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_REJECTED</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_REJECTED</name>\n\r'
                + '          <subject/>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>ERRORS</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>ERRORS</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>STARTDATE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>STARTDATE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>ENDDATE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>ENDDATE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LOGDATE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LOGDATE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>DEPDATE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>DEPDATE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>REPLAYDATE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>REPLAYDATE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LOG_FIELD</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LOG_FIELD</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>EXECUTING_SERVER</id>\n\r'
                + '          <enabled>N</enabled>\n\r'
                + '          <name>EXECUTING_SERVER</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>EXECUTING_USER</id>\n\r'
                + '          <enabled>N</enabled>\n\r'
                + '          <name>EXECUTING_USER</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>CLIENT</id>\n\r'
                + '          <enabled>N</enabled>\n\r'
                + '          <name>CLIENT</name>\n\r'
                + '        </field>\n\r'
                + '      </trans-log-table>\n\r'
                + '      <perf-log-table>\n\r'
                + '        <connection/>\n\r'
                + '        <schema/>\n\r'
                + '        <table/>\n\r'
                + '        <interval/>\n\r'
                + '        <timeout_days/>\n\r'
                + '        <field>\n\r'
                + '          <id>ID_BATCH</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>ID_BATCH</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>SEQ_NR</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>SEQ_NR</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LOGDATE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LOGDATE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>TRANSNAME</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>TRANSNAME</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>STEPNAME</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>STEPNAME</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>STEP_COPY</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>STEP_COPY</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_READ</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_READ</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_WRITTEN</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_WRITTEN</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_UPDATED</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_UPDATED</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_INPUT</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_INPUT</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_OUTPUT</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_OUTPUT</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_REJECTED</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_REJECTED</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>ERRORS</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>ERRORS</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>INPUT_BUFFER_ROWS</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>INPUT_BUFFER_ROWS</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>OUTPUT_BUFFER_ROWS</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>OUTPUT_BUFFER_ROWS</name>\n\r'
                + '        </field>\n\r'
                + '      </perf-log-table>\n\r'
                + '      <channel-log-table>\n\r'
                + '        <connection/>\n\r'
                + '        <schema/>\n\r'
                + '        <table/>\n\r'
                + '        <timeout_days/>\n\r'
                + '        <field>\n\r'
                + '          <id>ID_BATCH</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>ID_BATCH</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>CHANNEL_ID</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>CHANNEL_ID</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LOG_DATE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LOG_DATE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LOGGING_OBJECT_TYPE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LOGGING_OBJECT_TYPE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>OBJECT_NAME</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>OBJECT_NAME</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>OBJECT_COPY</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>OBJECT_COPY</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>REPOSITORY_DIRECTORY</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>REPOSITORY_DIRECTORY</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>FILENAME</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>FILENAME</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>OBJECT_ID</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>OBJECT_ID</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>OBJECT_REVISION</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>OBJECT_REVISION</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>PARENT_CHANNEL_ID</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>PARENT_CHANNEL_ID</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>ROOT_CHANNEL_ID</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>ROOT_CHANNEL_ID</name>\n\r'
                + '        </field>\n\r'
                + '      </channel-log-table>\n\r'
                + '      <step-log-table>\n\r'
                + '        <connection/>\n\r'
                + '        <schema/>\n\r'
                + '        <table/>\n\r'
                + '        <timeout_days/>\n\r'
                + '        <field>\n\r'
                + '          <id>ID_BATCH</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>ID_BATCH</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>CHANNEL_ID</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>CHANNEL_ID</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LOG_DATE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LOG_DATE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>TRANSNAME</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>TRANSNAME</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>STEPNAME</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>STEPNAME</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>STEP_COPY</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>STEP_COPY</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_READ</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_READ</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_WRITTEN</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_WRITTEN</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_UPDATED</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_UPDATED</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_INPUT</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_INPUT</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_OUTPUT</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_OUTPUT</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LINES_REJECTED</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LINES_REJECTED</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>ERRORS</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>ERRORS</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LOG_FIELD</id>\n\r'
                + '          <enabled>N</enabled>\n\r'
                + '          <name>LOG_FIELD</name>\n\r'
                + '        </field>\n\r'
                + '      </step-log-table>\n\r'
                + '      <metrics-log-table>\n\r'
                + '        <connection/>\n\r'
                + '        <schema/>\n\r'
                + '        <table/>\n\r'
                + '        <timeout_days/>\n\r'
                + '        <field>\n\r'
                + '          <id>ID_BATCH</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>ID_BATCH</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>CHANNEL_ID</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>CHANNEL_ID</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>LOG_DATE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>LOG_DATE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>METRICS_DATE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>METRICS_DATE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>METRICS_CODE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>METRICS_CODE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>METRICS_DESCRIPTION</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>METRICS_DESCRIPTION</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>METRICS_SUBJECT</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>METRICS_SUBJECT</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>METRICS_TYPE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>METRICS_TYPE</name>\n\r'
                + '        </field>\n\r'
                + '        <field>\n\r'
                + '          <id>METRICS_VALUE</id>\n\r'
                + '          <enabled>Y</enabled>\n\r'
                + '          <name>METRICS_VALUE</name>\n\r'
                + '        </field>\n\r'
                + '      </metrics-log-table>\n\r'
                + '    </log>\n\r'
                + '    <maxdate>\n\r'
                + '      <connection/>\n\r'
                + '      <table/>\n\r'
                + '      <field/>\n\r'
                + '      <offset>0.0</offset>\n\r'
                + '      <maxdiff>0.0</maxdiff>\n\r'
                + '    </maxdate>\n\r'
                + '    <size_rowset>10000</size_rowset>\n\r'
                + '    <sleep_time_empty>50</sleep_time_empty>\n\r'
                + '    <sleep_time_full>50</sleep_time_full>\n\r'
                + '    <unique_connections>N</unique_connections>\n\r'
                + '    <feedback_shown>Y</feedback_shown>\n\r'
                + '    <feedback_size>50000</feedback_size>\n\r'
                + '    <using_thread_priorities>Y</using_thread_priorities>\n\r'
                + '    <shared_objects_file/>\n\r'
                + '    <capture_step_performance>N</capture_step_performance>\n\r'
                + '    <step_performance_capturing_delay>1000</step_performance_capturing_delay>\n\r'
                + '    <step_performance_capturing_size_limit>100</step_performance_capturing_size_limit>\n\r'
                + '    <dependencies>\n\r'
                + '    </dependencies>\n\r'
                + '    <partitionschemas>\n\r'
                + '    </partitionschemas>\n\r'
                + '    <slaveservers>\n\r'
                + '    </slaveservers>\n\r'
                + '    <clusterschemas>\n\r'
                + '    </clusterschemas>\n\r'
                + '    <created_user>-</created_user>\n\r'
                + '    <created_date>2024/06/05 00:35:37.663</created_date>\n\r'
                + '    <modified_user>-</modified_user>\n\r'
                + '    <modified_date>2024/06/05 00:35:37.663</modified_date>\n\r'
                + '    <key_for_session_key>H4sIAAAAAAAAAAMAAAAAAAAAAAA=</key_for_session_key>\n\r'
                + '    <is_key_private>N</is_key_private>\n\r'
                + '  </info>\n\r'
                + '  <notepads>\n\r'
                + '  </notepads>\n\r'
                )
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
