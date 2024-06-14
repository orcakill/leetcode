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


class KettleStr:
    str_xml = '<?xml version="1.0" encoding="UTF-8"?>\r\n'
    str_info = ('<transformation>\n'
                + '  <info>\n'
                + '    <name>kettle_increment</name>\n'
                + '    <description/>\n'
                + '    <extended_description/>\n'
                + '    <trans_version/>\n'
                + '    <trans_type>Normal</trans_type>\n'
                + '    <trans_status>0</trans_status>\n'
                + '    <directory>/</directory>\n'
                + '    <parameters>\n'
                + '      <parameter>\n'
                + '        <name>v_ny</name>\n'
                + '        <default_value>202401</default_value>\n'
                + '        <description/>\n'
                + '      </parameter>\n'
                + '      <parameter>\n'
                + '        <name>v_rq</name>\n'
                + '        <default_value>2024-01-01</default_value>\n'
                + '        <description/>\n'
                + '      </parameter>\n'
                + '    </parameters>\n'
                + '    <log>\n'
                + '      <trans-log-table>\n'
                + '        <connection/>\n'
                + '        <schema/>\n'
                + '        <table/>\n'
                + '        <size_limit_lines/>\n'
                + '        <interval/>\n'
                + '        <timeout_days/>\n'
                + '        <field>\n'
                + '          <id>ID_BATCH</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>ID_BATCH</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>CHANNEL_ID</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>CHANNEL_ID</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>TRANSNAME</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>TRANSNAME</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>STATUS</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>STATUS</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_READ</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_READ</name>\n'
                + '          <subject/>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_WRITTEN</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_WRITTEN</name>\n'
                + '          <subject/>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_UPDATED</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_UPDATED</name>\n'
                + '          <subject/>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_INPUT</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_INPUT</name>\n'
                + '          <subject/>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_OUTPUT</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_OUTPUT</name>\n'
                + '          <subject/>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_REJECTED</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_REJECTED</name>\n'
                + '          <subject/>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>ERRORS</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>ERRORS</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>STARTDATE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>STARTDATE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>ENDDATE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>ENDDATE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LOGDATE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LOGDATE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>DEPDATE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>DEPDATE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>REPLAYDATE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>REPLAYDATE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LOG_FIELD</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LOG_FIELD</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>EXECUTING_SERVER</id>\n'
                + '          <enabled>N</enabled>\n'
                + '          <name>EXECUTING_SERVER</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>EXECUTING_USER</id>\n'
                + '          <enabled>N</enabled>\n'
                + '          <name>EXECUTING_USER</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>CLIENT</id>\n'
                + '          <enabled>N</enabled>\n'
                + '          <name>CLIENT</name>\n'
                + '        </field>\n'
                + '      </trans-log-table>\n'
                + '      <perf-log-table>\n'
                + '        <connection/>\n'
                + '        <schema/>\n'
                + '        <table/>\n'
                + '        <interval/>\n'
                + '        <timeout_days/>\n'
                + '        <field>\n'
                + '          <id>ID_BATCH</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>ID_BATCH</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>SEQ_NR</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>SEQ_NR</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LOGDATE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LOGDATE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>TRANSNAME</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>TRANSNAME</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>STEPNAME</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>STEPNAME</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>STEP_COPY</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>STEP_COPY</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_READ</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_READ</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_WRITTEN</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_WRITTEN</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_UPDATED</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_UPDATED</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_INPUT</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_INPUT</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_OUTPUT</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_OUTPUT</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_REJECTED</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_REJECTED</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>ERRORS</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>ERRORS</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>INPUT_BUFFER_ROWS</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>INPUT_BUFFER_ROWS</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>OUTPUT_BUFFER_ROWS</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>OUTPUT_BUFFER_ROWS</name>\n'
                + '        </field>\n'
                + '      </perf-log-table>\n'
                + '      <channel-log-table>\n'
                + '        <connection/>\n'
                + '        <schema/>\n'
                + '        <table/>\n'
                + '        <timeout_days/>\n'
                + '        <field>\n'
                + '          <id>ID_BATCH</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>ID_BATCH</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>CHANNEL_ID</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>CHANNEL_ID</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LOG_DATE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LOG_DATE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LOGGING_OBJECT_TYPE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LOGGING_OBJECT_TYPE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>OBJECT_NAME</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>OBJECT_NAME</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>OBJECT_COPY</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>OBJECT_COPY</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>REPOSITORY_DIRECTORY</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>REPOSITORY_DIRECTORY</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>FILENAME</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>FILENAME</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>OBJECT_ID</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>OBJECT_ID</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>OBJECT_REVISION</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>OBJECT_REVISION</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>PARENT_CHANNEL_ID</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>PARENT_CHANNEL_ID</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>ROOT_CHANNEL_ID</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>ROOT_CHANNEL_ID</name>\n'
                + '        </field>\n'
                + '      </channel-log-table>\n'
                + '      <step-log-table>\n'
                + '        <connection/>\n'
                + '        <schema/>\n'
                + '        <table/>\n'
                + '        <timeout_days/>\n'
                + '        <field>\n'
                + '          <id>ID_BATCH</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>ID_BATCH</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>CHANNEL_ID</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>CHANNEL_ID</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LOG_DATE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LOG_DATE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>TRANSNAME</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>TRANSNAME</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>STEPNAME</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>STEPNAME</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>STEP_COPY</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>STEP_COPY</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_READ</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_READ</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_WRITTEN</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_WRITTEN</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_UPDATED</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_UPDATED</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_INPUT</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_INPUT</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_OUTPUT</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_OUTPUT</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LINES_REJECTED</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LINES_REJECTED</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>ERRORS</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>ERRORS</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LOG_FIELD</id>\n'
                + '          <enabled>N</enabled>\n'
                + '          <name>LOG_FIELD</name>\n'
                + '        </field>\n'
                + '      </step-log-table>\n'
                + '      <metrics-log-table>\n'
                + '        <connection/>\n'
                + '        <schema/>\n'
                + '        <table/>\n'
                + '        <timeout_days/>\n'
                + '        <field>\n'
                + '          <id>ID_BATCH</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>ID_BATCH</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>CHANNEL_ID</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>CHANNEL_ID</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>LOG_DATE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>LOG_DATE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>METRICS_DATE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>METRICS_DATE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>METRICS_CODE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>METRICS_CODE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>METRICS_DESCRIPTION</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>METRICS_DESCRIPTION</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>METRICS_SUBJECT</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>METRICS_SUBJECT</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>METRICS_TYPE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>METRICS_TYPE</name>\n'
                + '        </field>\n'
                + '        <field>\n'
                + '          <id>METRICS_VALUE</id>\n'
                + '          <enabled>Y</enabled>\n'
                + '          <name>METRICS_VALUE</name>\n'
                + '        </field>\n'
                + '      </metrics-log-table>\n'
                + '    </log>\n'
                + '    <maxdate>\n'
                + '      <connection/>\n'
                + '      <table/>\n'
                + '      <field/>\n'
                + '      <offset>0.0</offset>\n'
                + '      <maxdiff>0.0</maxdiff>\n'
                + '    </maxdate>\n'
                + '    <size_rowset>10000</size_rowset>\n'
                + '    <sleep_time_empty>50</sleep_time_empty>\n'
                + '    <sleep_time_full>50</sleep_time_full>\n'
                + '    <unique_connections>N</unique_connections>\n'
                + '    <feedback_shown>Y</feedback_shown>\n'
                + '    <feedback_size>50000</feedback_size>\n'
                + '    <using_thread_priorities>Y</using_thread_priorities>\n'
                + '    <shared_objects_file/>\n'
                + '    <capture_step_performance>N</capture_step_performance>\n'
                + '    <step_performance_capturing_delay>1000</step_performance_capturing_delay>\n'
                + '    <step_performance_capturing_size_limit>100</step_performance_capturing_size_limit>\n'
                + '    <dependencies>\n'
                + '    </dependencies>\n'
                + '    <partitionschemas>\n'
                + '    </partitionschemas>\n'
                + '    <slaveservers>\n'
                + '    </slaveservers>\n'
                + '    <clusterschemas>\n'
                + '    </clusterschemas>\n'
                + '    <created_user>-</created_user>\n'
                + '    <created_date>2024/06/05 00:35:37.663</created_date>\n'
                + '    <modified_user>-</modified_user>\n'
                + '    <modified_date>2024/06/05 00:35:37.663</modified_date>\n'
                + '    <key_for_session_key>H4sIAAAAAAAAAAMAAAAAAAAAAAA=</key_for_session_key>\n'
                + '    <is_key_private>N</is_key_private>\n'
                + '  </info>\n'
                + '  <notepads>\n'
                + '  </notepads>\n'
                )
    str_connection_oracle = ('    <servername/>\n'
                             + '    <data_tablespace/>\n'
                             + '    <index_tablespace/>\n'
                             + '    <attributes>\n'
                             + '      <attribute>\n'
                             + '        <code>FORCE_IDENTIFIERS_TO_LOWERCASE</code>\n'
                             + '        <attribute>N</attribute>\n'
                             + '      </attribute>\n'
                             + '      <attribute>\n'
                             + '        <code>FORCE_IDENTIFIERS_TO_UPPERCASE</code>\n'
                             + '        <attribute>N</attribute>\n'
                             + '      </attribute>\n'
                             + '      <attribute>\n'
                             + '        <code>IS_CLUSTERED</code>\n'
                             + '        <attribute>N</attribute>\n'
                             + '      </attribute>\n'
                             + '      <attribute>\n'
                             + '        <code>PORT_NUMBER</code>\n'
                             + '        <attribute>1521</attribute>\n'
                             + '      </attribute>\n'
                             + '      <attribute>\n'
                             + '        <code>PRESERVE_RESERVED_WORD_CASE</code>\n'
                             + '        <attribute>Y</attribute>\n'
                             + '      </attribute>\n'
                             + '      <attribute>\n'
                             + '        <code>QUOTE_ALL_FIELDS</code>\n'
                             + '        <attribute>N</attribute>\n'
                             + '      </attribute>\n'
                             + '      <attribute>\n'
                             + '        <code>STRICT_NUMBER_38_INTERPRETATION</code>\n'
                             + '        <attribute>N</attribute>\n'
                             + '      </attribute>\n'
                             + '      <attribute>\n'
                             + '        <code>SUPPORTS_BOOLEAN_DATA_TYPE</code>\n'
                             + '        <attribute>Y</attribute>\n'
                             + '      </attribute>\n'
                             + '      <attribute>\n'
                             + '        <code>SUPPORTS_TIMESTAMP_DATA_TYPE</code>\n'
                             + '        <attribute>Y</attribute>\n'
                             + '      </attribute>\n'
                             + '      <attribute>\n'
                             + '        <code>USE_POOLING</code>\n'
                             + '        <attribute>N</attribute>\n'
                             + '      </attribute>\n'
                             + '    </attributes>\n'
                             + '  </connection>\n')
    str_connection_dm = ('      </attribute>\n'
                         + '      <attribute>\n'
                         + '        <code>DATABASE_DIALECT_ID</code>\n'
                         + '        <attribute>Generic database</attribute>\n'
                         + '      </attribute>\n'
                         + '      <attribute>\n'
                         + '        <code>FORCE_IDENTIFIERS_TO_LOWERCASE</code>\n'
                         + '        <attribute>N</attribute>\n'
                         + '      </attribute>\n'
                         + '      <attribute>\n'
                         + '        <code>FORCE_IDENTIFIERS_TO_UPPERCASE</code>\n'
                         + '        <attribute>N</attribute>\n'
                         + '      </attribute>\n'
                         + '      <attribute>\n'
                         + '        <code>IS_CLUSTERED</code>\n'
                         + '        <attribute>N</attribute>\n'
                         + '      </attribute>\n'
                         + '      <attribute>\n'
                         + '        <code>PORT_NUMBER</code>\n'
                         + '        <attribute>1521</attribute>\n'
                         + '      </attribute>\n'
                         + '      <attribute>\n'
                         + '        <code>PRESERVE_RESERVED_WORD_CASE</code>\n'
                         + '        <attribute>Y</attribute>\n'
                         + '      </attribute>\n'
                         + '      <attribute>\n'
                         + '        <code>QUOTE_ALL_FIELDS</code>\n'
                         + '        <attribute>N</attribute>\n'
                         + '      </attribute>\n'
                         + '      <attribute>\n'
                         + '        <code>SUPPORTS_BOOLEAN_DATA_TYPE</code>\n'
                         + '        <attribute>Y</attribute>\n'
                         + '      </attribute>\n'
                         + '      <attribute>\n'
                         + '        <code>SUPPORTS_TIMESTAMP_DATA_TYPE</code>\n'
                         + '        <attribute>Y</attribute>\n'
                         + '      </attribute>\n'
                         + '      <attribute>\n'
                         + '        <code>USE_POOLING</code>\n'
                         + '        <attribute>N</attribute>\n'
                         + '      </attribute>\n'
                         + '    </attributes>\n'
                         + '  </connection>\n')
    str_step_error_handling = ('  <step_error_handling>\n'
                               + '  </step_error_handling>\n'
                               + '  <slave-step-copy-partition-distribution>\n'
                               + '  </slave-step-copy-partition-distribution>\n'
                               + '  <slave_transformation>N</slave_transformation>\n'
                               + '  <attributes/>\n'
                               + '</transformation>')


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
    table_sql = ("SELECT a.table_name as table_name from "
                 + "(SELECT a.table_name as table_name FROM user_tables a  order by table_name) a"
                 + " where a.table_name not like 'YXYY_%' order by table_name")
    cursor.execute(table_sql)

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


def deal_example_ktr():
    logger.info("读取文件")
    file_path = "D:/测试2.txt"
    with open(file_path, 'r', encoding='utf-8') as file:
        for line in file:
            print(f"+{line.rstrip()}\\n\\r'")


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
            if i * 50 < index <= ((i + 1) * 50):
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


if __name__ == '__main__':
    # deal_example_ktr()
    create_kettle()
    # compare_file()
