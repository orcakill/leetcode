# @Time: 2024年06月17日 08:35
# @Author: orcakill
# @File: kettle_str.py
# @Description: 存储固定字符
import math
import subprocess

created_date = '2024/08/08 10:26:52.485'
modified_date = '2024/08/08 10:26:52.485'


class KettleStr:
    str_xml = '<?xml version="1.0" encoding="UTF-8"?>\r\n'
    str_info1 = ('<transformation>\n'
                 + '  <info>\n')
    str_info2 = ('    <description/>\n'
                 + '    <extended_description/>\n'
                 + '    <trans_version/>\n'
                 + '    <trans_type>Normal</trans_type>\n'
                 + '    <trans_status>0</trans_status>\n'
                 + '    <directory>/</directory>\n')
    str_info3 = ('    <log>\n'
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
                 + '    <created_date>' + created_date + '</created_date>\n'
                 + '    <modified_user>-</modified_user>\n'
                 + '    <modified_date>' + modified_date + '</modified_date>\n'
                 + '    <key_for_session_key>H4sIAAAAAAAAAAMAAAAAAAAAAAA=</key_for_session_key>\n'
                 + '    <is_key_private>N</is_key_private>\n'
                 + '  </info>\n'
                 + '  <notepads>\n'
                 + '  </notepads>\n'
                 )

    @staticmethod
    def deal_connection(normal_info, database_info, source):
        """
        处理数据库连接
        """
        str_connection = ""
        # 常规设置
        kettle_path = normal_info['kettle_path']
        # 数据库设置
        connect_name = database_info['connect_name']
        database_type = database_info['database_type']
        ip = database_info['ip']
        port = database_info['port']
        servername = database_info['servername']
        username = database_info['username']
        password = database_info['password']
        kettle_password = KettleStr.deal_password(kettle_path, password)
        str_read = ''
        str_pooling = 'N'
        if normal_info['database_read'] == 'true' and source == True:
            str_read = ('      <attribute>\n'
                        + '        <code>POOLING_defaultReadOnly</code>\n'
                        + '        <attribute>true</attribute>\n'
                        + '      </attribute>\n')
            str_pooling = 'Y'
        if normal_info['database_read'] == 'true' and source == True:
            str_read = ('      <attribute>\n'
                        + '        <code>POOLING_defaultReadOnly</code>\n'
                        + '        <attribute>true</attribute>\n'
                        + '      </attribute>\n')
        if database_type == 'oracle':
            str_connection = ('  <connection>\n'
                              + '    <name>' + connect_name + '</name>\n'
                              + '    <server>' + ip + '</server>\n'
                              + '    <type>ORACLE</type>\n'
                              + '    <access>Native</access>\n'
                              + '    <database>' + servername + '</database>\n'
                              + '    <port>' + port + '</port>\n'
                              + '    <username>' + username + '</username>\n'
                              + '    <password>' + kettle_password + '</password>\n'
                              + '    <servername/>\n'
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
                              + str_read
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
                              + '        <attribute>' + str_pooling + '</attribute>\n'
                              + '      </attribute>\n'
                              + '    </attributes>\n'
                              + '  </connection>\n')
        elif database_type == 'dm':
            str_connection = ('  <connection>\n'
                              + '    <name>' + connect_name + '</name>\n'
                              + '    <server/>\n'
                              + '    <type>GENERIC</type>\n'
                              + '    <access>Native</access>\n'
                              + '    <database/>\n'
                              + '    <port>1521</port>\n'
                              + '    <username>' + username + '</username>\n'
                              + '    <password>' + kettle_password + '</password>\n'
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
                              + '      </attribute>\n'
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
        return str_connection

    @staticmethod
    def deal_password(kettle_path, password):
        kettle_password = ""
        # 执行命令
        result = subprocess.run(kettle_path + "/Encr.bat " + password, shell=True, capture_output=True, text=True)

        # 获取返回结果
        output = result.stdout
        # 查找指定子字符串的位置
        substring = "Encrypted"
        start_index = output.find(substring)

        if start_index != -1:
            # 截取从指定位置及之后的部分
            kettle_password = output[start_index:].replace('\n', '')
        else:
            print("未找到指定子字符串")
        return kettle_password

    @staticmethod
    def deal_order(table_infos, mode_state):
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
            if mode_state in [0, "0"]:
                str_order = (str_order
                             + '    <hop>' + nr
                             + '      <from>表输入-' + table_name + '</from>' + nr
                             + '      <to>插入 / 更新-' + table_name + '</to>' + nr
                             + '      <enabled>Y</enabled>' + nr
                             + '    </hop>\n'
                             )
            elif mode_state in [1, "1"]:
                str_order = (str_order
                             + '    <hop>' + nr
                             + '      <from>执行SQL脚本-删除' + table_name + '当前数据</from>' + nr
                             + '      <to>表输入-' + table_name + '</to>' + nr
                             + '      <enabled>Y</enabled>' + nr
                             + '    </hop>\n'
                             + '    <hop>' + nr
                             + '      <from>表输入-' + table_name + '</from>' + nr
                             + '      <to>表输出-' + table_name + '</to>' + nr
                             + '      <enabled>Y</enabled>' + nr
                             + '    </hop>\n'
                             + '    <hop>' + nr
                             + '      <from>表输出-' + table_name + '</from>' + nr
                             + '      <to>JS代码-' + table_name + '错误信息</to>' + nr
                             + '      <enabled>Y</enabled>' + nr
                             + '    </hop>\n'
                             + '    <hop>' + nr
                             + '      <from>JS代码-' + table_name + '错误信息</from>' + nr
                             + '      <to>表输出-' + table_name + '错误日志</to>' + nr
                             + '      <enabled>Y</enabled>' + nr
                             + '    </hop>\n'
                             )
        str_order = (str_order + "  </order>\n")
        return str_order

    @staticmethod
    def deal_parameter(normal_info, parameter_type):
        str_parameter = ""
        parameter_settings = normal_info['parameter_settings']
        default_month = normal_info['default_month']
        default_date = normal_info['default_date']
        if parameter_settings in [0, '0']:
            str_parameter = ('    <parameters>\n'
                             + '      <parameter>\n'
                             + '        <name>v_ny</name>\n'
                             + '        <default_value>' + default_month + '</default_value>\n'
                             + '        <description/>\n'
                             + '      </parameter>\n'
                             + '      <parameter>\n'
                             + '        <name>v_rq</name>\n'
                             + '        <default_value>' + default_date + '</default_value>\n'
                             + '        <description/>\n'
                             + '      </parameter>\n'
                             + '    </parameters>\n')
        if parameter_settings == 1:
            if parameter_type == 'v_rq':
                str_parameter = ('    <parameters>\n'
                                 + '      <parameter>\n'
                                 + '        <name>v_rq</name>\n'
                                 + '        <default_value>' + default_date + '</default_value>\n'
                                 + '        <description/>\n'
                                 + '      </parameter>\n'
                                 + '    </parameters>\n')
            if parameter_type == 'v_ny':
                str_parameter = ('    <parameters>\n'
                                 + '      <parameter>\n'
                                 + '        <name>v_ny</name>\n'
                                 + '        <default_value>' + default_month + '</default_value>\n'
                                 + '        <description/>\n'
                                 + '      </parameter>\n'
                                 + '    </parameters>\n')
        return str_parameter

    @staticmethod
    def deal_step(table_infos, normal_info, database_info1, database_info2):
        str_step = ""
        mode_state = normal_info['mode_state']
        # 循环表,先插入更新再表输入
        for index, (key, value) in enumerate(table_infos.items(), start=1):
            # 表输入
            str_table_input = ""
            # 插入更新
            str_table_insert = ""
            # 删除已有数据
            str_delete_sql = ""
            # 表输出
            str_table_output = ""
            # js错误信息
            str_error_info = ""
            # 表输出-错误日志
            str_error_log = ""
            # where 条件sql
            str_where_sql = ""
            # 表名
            connect_name1 = database_info1['connect_name']
            connect_name2 = database_info2['connect_name']
            table_name = key
            table_length = 12
            if mode_state in [0, "0"]:
                table_length = 0
            name_delete_sql = '执行SQL脚本-删除' + table_name + '当前数据'
            name_table_input = '表输入-' + table_name
            name_table_insert = '插入 / 更新-' + table_name
            name_table_output = '表输出-' + table_name
            name_error_info = 'JS代码-' + table_name + '错误信息'
            name_error_log = '表输出-' + table_name + '错误日志'
            num_x = 50
            add_x = 100
            if len(table_name) > 10:
                add_x = 0
            x_delete_sql = 200
            x_table_input = add_x + x_delete_sql + math.floor(
                (len(name_delete_sql) + len(name_table_input)) * table_length * 1 / 2 / num_x) * num_x
            x_table_insert = add_x + x_table_input + math.floor(
                (len(name_table_input) + len(name_table_insert)) * table_length * 1 / 2 / num_x) * num_x
            x_table_output = add_x + x_table_input + math.floor(
                (len(name_table_input) + len(name_table_output)) * table_length * 1 / 2 / num_x) * num_x
            x_error_info = add_x + x_table_insert + math.floor(
                (len(name_table_insert) + len(name_error_info)) * table_length * 1 / 2 / num_x) * num_x
            x_error_log = add_x + x_error_info + math.floor(
                (len(name_error_info) + len(name_error_log)) * table_length * 1 / 2 / num_x) * num_x
            print(x_delete_sql)
            print(x_table_input)
            print(x_table_insert)
            print(x_table_output)
            print(x_error_info)
            print(x_error_log)
            # 表输入，开始
            str_table_input = (str_table_input + '  <step>\n'
                               + '    <name>' + name_table_input + '</name>\n'
                               + '    <type>TableInput</type>\n'
                               + '    <description/>\n'
                               + '    <distribute>Y</distribute>\n'
                               + '    <custom_distribution/>\n'
                               + '    <copies>1</copies>\n'
                               + '    <partitioning>\n'
                               + '      <method>none</method>\n'
                               + '      <schema_name/>\n'
                               + '    </partitioning>\n'
                               + '    <connection>' + connect_name1 + '</connection>\n'
                               + '    <sql>select\r\n'
                               )
            table_info = table_infos[table_name]
            # 主键信息
            column_pk_info = []
            for j in range(len(table_info)):
                column = table_info[j]
                column_pk = table_info[j][5]
                if column_pk == 'Y':
                    column_pk_info.append(column)
            for j in range(len(table_info)):
                column_lower = table_info[j][3].lower()
                column_comment = table_info[j][4]
                if column_comment is None:
                    column_comment = ''
                if j == len(table_info) - 1:
                    str_table_input = str_table_input + '       ' + column_lower + '    /*' + column_comment + '*/' + '\n'
                else:
                    str_table_input = str_table_input + '       ' + column_lower + ',   /*' + column_comment + '*/' + '\n'
            str_table_input = (str_table_input + '  from ' + table_name + '\n')
            table_column = []
            for column in table_info:
                table_column.append(column[3])
            if 'RQ' in table_column:
                str_where_sql = ('  where \'${v_rq}\' is null or rq=to_date(\'${v_rq}\',\'yyyy-mm-dd\')\n')
            if 'NY' in table_column:
                str_where_sql = ('  where \'${v_ny}\' is null or ny=\'${v_ny}\'\n')
            str_table_input = str_table_input + str_where_sql
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
                if column_type.upper() in ['NVARCHAR2', 'VARCHAR', 'VARCHAR2', 'CHAR']:
                    if column_type in ['NVARCHAR2']:
                        column_length = str(int(int(column_length) / 2))
                    column_type = 'String'
                if column_type.upper() in ['NUMBER']:
                    precision = str(table_info[j][9])
                    if column_length == 'None':
                        column_type = 'BigNumber'
                        column_length = str(-1)
                    else:
                        if precision == '0':
                            column_type = 'Integer'
                            conversion_Mask = '        <conversion_Mask>####0;-####0</conversion_Mask>\n'
                        else:
                            column_type = 'Number'
                            conversion_Mask = ('        <conversion_Mask>####0.0#########;-####0.0#########'
                                               + '</conversion_Mask>\n')
                if column_type.upper() in ['DATE']:
                    column_type = 'Timestamp'
                    column_length = str(0)
                if column_type.upper() in ['TIMESTAMP(6)', 'TIMESTAMP']:
                    column_type = 'Timestamp'
                if column_type.upper() in ['INTEGER']:
                    column_length = str(column_length)
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
                                   + '        <origin>表输入-' + table_name + '</origin>\n'
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
                               + '      <xloc>' + str(x_table_input) + '</xloc>\n'
                               + '      <yloc>' + str(index * 100) + '</yloc>\n'
                               + '      <draw>Y</draw>\n'
                               + '    </GUI>\n'
                               + '  </step>\n'
                               )
            # 表输入，结束
            # 插入更新,开始
            str_table_insert = (str_table_insert
                                + '  <step>\n'
                                + '    <name>' + name_table_insert + '</name>\n'
                                + '    <type>InsertUpdate</type>\n'
                                + '    <description/>\n'
                                + '    <distribute>Y</distribute>\n'
                                + '    <custom_distribution/>\n'
                                + '    <copies>1</copies>\n'
                                + '    <partitioning>\n'
                                + '      <method>none</method>\n'
                                + '      <schema_name/>\n'
                                + '    </partitioning>\n'
                                + '    <connection>' + connect_name2 + '</connection>\n'
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
                                + '      <xloc>' + str(x_table_insert) + '</xloc>\n'
                                + '      <yloc>' + str(index * 100) + '</yloc>\n'
                                + '      <draw>Y</draw>\n'
                                + '    </GUI>\n'
                                + '  </step>\n'
                                )
            # 插入更新,结束
            # 删除已有数据 开始`
            str_delete_sql = ('  <step>\n'
                              + '    <name>' + name_delete_sql + '</name>\n'
                              + '    <type>ExecSQL</type>\n'
                              + '    <description/>\n'
                              + '    <distribute>Y</distribute>\n'
                              + '    <custom_distribution/>\n'
                              + '    <copies>1</copies>\n'
                              + '    <partitioning>\n'
                              + '      <method>none</method>\n'
                              + '      <schema_name/>\n'
                              + '    </partitioning>\n'
                              + '    <connection>' + connect_name2 + '</connection>\n'
                              + '    <execute_each_row>N</execute_each_row>\n'
                              + '    <single_statement>N</single_statement>\n'
                              + '    <replace_variables>Y</replace_variables>\n'
                              + '    <quoteString>N</quoteString>\n'
                              + '    <sql>delete  from  ' + table_name + str_where_sql.replace("\n", '') + '</sql>\n'
                              + '    <set_params>N</set_params>\n'
                              + '    <insert_field/>\n'
                              + '    <update_field/>\n'
                              + '    <delete_field/>\n'
                              + '    <read_field/>\n'
                              + '    <arguments>\n'
                              + '    </arguments>\n'
                              + '    <attributes/>\n'
                              + '    <cluster_schema/>\n'
                              + '    <remotesteps>\n'
                              + '      <input>\n'
                              + '      </input>\n'
                              + '      <output>\n'
                              + '      </output>\n'
                              + '    </remotesteps>\n'
                              + '    <GUI>\n'
                              + '      <xloc>' + str(x_delete_sql) + '</xloc>\n'
                              + '      <yloc>' + str(index * 100) + '</yloc>\n'
                              + '      <draw>Y</draw>\n'
                              + '    </GUI>\n'
                              + '  </step>\n')
            # 删除已有数据 结束
            # 表输出 开始
            str_table_output = (str_table_output
                                + '  <step>\n'
                                + '    <name>' + name_table_output + '</name>\n'
                                + '    <type>TableOutput</type>\n'
                                + '    <description/>\n'
                                + '    <distribute>Y</distribute>\n'
                                + '    <custom_distribution/>\n'
                                + '    <copies>1</copies>\n'
                                + '    <partitioning>\n'
                                + '      <method>none</method>\n'
                                + '      <schema_name/>\n'
                                + '    </partitioning>\n'
                                + '    <connection>' + connect_name2 + '</connection>\n'
                                + '    <schema/>\n'
                                + '    <table>' + table_name + '</table>\n'
                                + '    <commit>1000</commit>\n'
                                + '    <truncate>N</truncate>\n'
                                + '    <ignore_errors>N</ignore_errors>\n'
                                + '    <use_batch>Y</use_batch>\n'
                                + '    <specify_fields>Y</specify_fields>\n'
                                + '    <partitioning_enabled>N</partitioning_enabled>\n'
                                + '    <partitioning_field/>\n'
                                + '    <partitioning_daily>N</partitioning_daily>\n'
                                + '    <partitioning_monthly>Y</partitioning_monthly>\n'
                                + '    <tablename_in_field>N</tablename_in_field>\n'
                                + '    <tablename_field/>\n'
                                + '    <tablename_in_table>Y</tablename_in_table>\n'
                                + '    <return_keys>N</return_keys>\n'
                                + '    <return_field/>\n'
                                + '    <fields>\n')
            for j in range(len(table_info)):
                column_name = table_info[j][3]
                str_table_output = (str_table_output
                                    + '      <field>\n'
                                    + '        <column_name>' + column_name + '</column_name>\n'
                                    + '        <stream_name>' + column_name + '</stream_name>\n'
                                    + '      </field>\n'
                                    )
            str_table_output = (str_table_output
                                + '    </fields>\n'
                                + '    <attributes/>\n'
                                + '    <cluster_schema/>\n'
                                + '    <remotesteps>\n'
                                + '      <input>\n'
                                + '      </input>\n'
                                + '      <output>\n'
                                + '      </output>\n'
                                + '    </remotesteps>\n'
                                + '    <GUI>\n'
                                + '      <xloc>' + str(x_table_output) + '</xloc>\n'
                                + '      <yloc>' + str(index * 100) + '</yloc>\n'
                                + '      <draw>Y</draw>\n'
                                + '    </GUI>\n'
                                + '  </step>\n')
            # 表输出 结束
            # 错误处理信息 开始
            str_error_info = (str_error_info
                              + '  <step>\n'
                              + '    <name>' + name_error_info + '</name>\n'
                              + '    <type>ScriptValueMod</type>\n'
                              + '    <description/>\n'
                              + '    <distribute>Y</distribute>\n'
                              + '    <custom_distribution/>\n'
                              + '    <copies>1</copies>\n'
                              + '    <partitioning>\n'
                              + '      <method>none</method>\n'
                              + '      <schema_name/>\n'
                              + '    </partitioning>\n'
                              + '    <compatible>N</compatible>\n'
                              + '    <optimizationLevel>9</optimizationLevel>\n'
                              + '    <jsScripts>\n'
                              + '      <jsScript>\n'
                              + '        <jsScript_type>0</jsScript_type>\n'
                              + '        <jsScript_name>Script 1</jsScript_name>\n'
                              + '        <jsScript_script>//Script here\n'
                              + '//Script here\n'
                              + 'function fGetTransPath(){\n'
                              + '	var VprogramPath  = replace(getVariable("Internal.Transformation.Filename.Directory",""),"file://","");\n'
                              + '	var FILE_NAME  = getVariable("Internal.Transformation.Filename.Name","");\n'
                              + '\n'
                              + '		// 操作系统\n'
                              + '		var vOsSys = null;\n'
                              + '		// 如处理后的字符串包含 冒号 , 则视为windows系统,  否则视为linux系统\n'
                              + '		if( isRegExp(VprogramPath,"^.*:.*$" ) == 1  ){\n'
                              + '		   vOsSys = "Windows";\n'
                              + '		}else {\n'
                              + '		    vOsSys = "Linux";\n'
                              + '		}\n'
                              + '\n'
                              + '		if(vOsSys == "Windows"){\n'
                              + '		   VprogramPath =  VprogramPath.replace(/\//,"");\n'
                              + '		}\n'
                              + '\n'
                              + '       return VprogramPath+"/"+FILE_NAME;\n'
                              + '}\n'
                              + '\n'
                              + 'var FILE_PATH  = fGetTransPath();\n'
                              + 'var TABLE_NAME = "' + table_name + '";\n'
                              + 'var ERROR_INFO   = '
                              )
            if column_pk_info and len(column_pk_info) > 0:
                for j in range(len(column_pk_info)):
                    column_name = column_pk_info[j][3] if column_pk_info[j][3] is not None else ''
                    column_comment = column_pk_info[j][4] if column_pk_info[j][4] is not None else ''
                    if j == 0 and j == len(column_pk_info) - 1:
                        str_error_info = (str_error_info
                                          + '"' + column_name + '(' + column_comment + ')=" + ' + column_name + ';\n'
                                          )
                    elif j == 0:
                        str_error_info = (str_error_info
                                          + '"' + column_name + '(' + column_comment + ')="+' + column_name
                                          )
                    elif j == len(column_pk_info) - 1:
                        str_error_info = (str_error_info
                                          + '+"\\n' + column_name + '(' + column_comment + ')=" + ' + column_name + ';\n'
                                          )
                    else:
                        str_error_info = (str_error_info
                                          + '+"\\n' + column_name + '(' + column_comment + ')=" + ' + column_name + '\n'
                                          )
            str_error_info = (str_error_info
                              + 'var ERROR_DATE = new Date();\n'
                              + '</jsScript_script>\n'
                              + '      </jsScript>\n'
                              + '    </jsScripts>\n'
                              + '    <fields>\n'
                              + '      <field>\n'
                              + '        <name>fGetTransPath</name>\n'
                              + '        <rename>fGetTransPath</rename>\n'
                              + '        <type>String</type>\n'
                              + '        <length>-1</length>\n'
                              + '        <precision>-1</precision>\n'
                              + '        <replace>N</replace>\n'
                              + '      </field>\n'
                              + '      <field>\n'
                              + '        <name>FILE_PATH</name>\n'
                              + '        <rename>FILE_PATH</rename>\n'
                              + '        <type>String</type>\n'
                              + '        <length>-1</length>\n'
                              + '        <precision>-1</precision>\n'
                              + '        <replace>N</replace>\n'
                              + '      </field>\n'
                              + '      <field>\n'
                              + '        <name>TABLE_NAME</name>\n'
                              + '        <rename>TABLE_NAME</rename>\n'
                              + '        <type>String</type>\n'
                              + '        <length>-1</length>\n'
                              + '        <precision>-1</precision>\n'
                              + '        <replace>N</replace>\n'
                              + '      </field>\n'
                              + '      <field>\n'
                              + '        <name>ERROR_INFO</name>\n'
                              + '        <rename>ERROR_INFO</rename>\n'
                              + '        <type>String</type>\n'
                              + '        <length>-1</length>\n'
                              + '        <precision>-1</precision>\n'
                              + '        <replace>N</replace>\n'
                              + '      </field>\n'
                              + '      <field>\n'
                              + '        <name>ERROR_DATE</name>\n'
                              + '        <rename>ERROR_DATE</rename>\n'
                              + '        <type>Date</type>\n'
                              + '        <length>-1</length>\n'
                              + '        <precision>-1</precision>\n'
                              + '        <replace>N</replace>\n'
                              + '      </field>\n'
                              + '    </fields>\n'
                              + '    <attributes/>\n'
                              + '    <cluster_schema/>\n'
                              + '    <remotesteps>\n'
                              + '      <input>\n'
                              + '      </input>\n'
                              + '      <output>\n'
                              + '      </output>\n'
                              + '    </remotesteps>\n'
                              + '    <GUI>\n'
                              + '      <xloc>' + str(x_error_info) + '</xloc>\n'
                              + '      <yloc>' + str(index * 100) + '</yloc>\n'
                              + '      <draw>Y</draw>\n'
                              + '    </GUI>\n'
                              + '  </step>\n'
                              )
            # 错误处理信息 结束
            # 错误日志  开始
            str_error_log = (str_error_log
                             + '  <step>\n'
                             + '    <name>' + name_error_log + '</name>\n'
                             + '    <type>TableOutput</type>\n'
                             + '    <description/>\n'
                             + '    <distribute>Y</distribute>\n'
                             + '    <custom_distribution/>\n'
                             + '    <copies>1</copies>\n'
                             + '    <partitioning>\n'
                             + '      <method>none</method>\n'
                             + '      <schema_name/>\n'
                             + '    </partitioning>\n'
                             + '    <connection>' + connect_name2 + '</connection>\n'
                             + '    <schema/>\n'
                             + '    <table>log_etl_error</table>\n'
                             + '    <commit>1000</commit>\n'
                             + '    <truncate>N</truncate>\n'
                             + '    <ignore_errors>N</ignore_errors>\n'
                             + '    <use_batch>Y</use_batch>\n'
                             + '    <specify_fields>Y</specify_fields>\n'
                             + '    <partitioning_enabled>N</partitioning_enabled>\n'
                             + '    <partitioning_field/>\n'
                             + '    <partitioning_daily>N</partitioning_daily>\n'
                             + '    <partitioning_monthly>N</partitioning_monthly>\n'
                             + '    <tablename_in_field>N</tablename_in_field>\n'
                             + '    <tablename_field/>\n'
                             + '    <tablename_in_table>N</tablename_in_table>\n'
                             + '    <return_keys>N</return_keys>\n'
                             + '    <return_field/>\n'
                             + '    <fields>\n'
                             + '      <field>\n'
                             + '        <column_name>ERROR_NUM_COL</column_name>\n'
                             + '        <stream_name>ERROR_NUM_COL</stream_name>\n'
                             + '      </field>\n'
                             + '      <field>\n'
                             + '        <column_name>ERROR_DESC_COL</column_name>\n'
                             + '        <stream_name>ERROR_DESC_COL</stream_name>\n'
                             + '      </field>\n'
                             + '      <field>\n'
                             + '        <column_name>ERROR_COL_NAME</column_name>\n'
                             + '        <stream_name>ERROR_COL_NAME</stream_name>\n'
                             + '      </field>\n'
                             + '      <field>\n'
                             + '        <column_name>ERROR_CODE_COL</column_name>\n'
                             + '        <stream_name>ERROR_CODE_COL</stream_name>\n'
                             + '      </field>\n'
                             + '      <field>\n'
                             + '        <column_name>FILE_PATH</column_name>\n'
                             + '        <stream_name>FILE_PATH</stream_name>\n'
                             + '      </field>\n'
                             + '      <field>\n'
                             + '        <column_name>TABLE_NAME</column_name>\n'
                             + '        <stream_name>TABLE_NAME</stream_name>\n'
                             + '      </field>\n'
                             + '      <field>\n'
                             + '        <column_name>ERROR_INFO</column_name>\n'
                             + '        <stream_name>ERROR_INFO</stream_name>\n'
                             + '      </field>\n'
                             + '      <field>\n'
                             + '        <column_name>ERROR_DATE</column_name>\n'
                             + '        <stream_name>ERROR_DATE</stream_name>\n'
                             + '      </field>\n'
                             + '    </fields>\n'
                             + '    <attributes/>\n'
                             + '    <cluster_schema/>\n'
                             + '    <remotesteps>\n'
                             + '      <input>\n'
                             + '      </input>\n'
                             + '      <output>\n'
                             + '      </output>\n'
                             + '    </remotesteps>\n'
                             + '    <GUI>\n'
                             + '      <xloc>' + str(x_error_log) + '</xloc>\n'
                             + '      <yloc>' + str(index * 100) + '</yloc>\n'
                             + '      <draw>Y</draw>\n'
                             + '    </GUI>\n'
                             + '  </step>\n'
                             )
            # 错误日志  结束
            # 根据不同模式组合步骤
            if mode_state in [0, '0']:
                str_step = str_step + str_table_insert + str_table_input
            elif mode_state in [1, '1']:
                str_step = str_step + str_error_info + str_delete_sql + str_table_input + str_table_output + str_error_log
        return str_step

    @staticmethod
    def deal_step_error_handling(table_infos, normal_info, database_info2):
        str_step_error_handling = '  <step_error_handling>\n'
        str_error = ''
        mode_state = normal_info['mode_state']
        # 循环表,先插入更新再表输入
        for index, (key, value) in enumerate(table_infos.items(), start=1):
            connect_name2 = database_info2['connect_name']
            table_name = key
            str_error = (str_error
                         + '    <error>\n'
                         + '      <source_step>表输出-' + table_name + '</source_step>\n'
                         + '      <target_step>JS代码-' + table_name + '错误信息</target_step>\n'
                         + '      <is_enabled>Y</is_enabled>\n'
                         + '      <nr_valuename>ERROR_NUM_COL</nr_valuename>\n'
                         + '      <descriptions_valuename>ERROR_DESC_COL</descriptions_valuename>\n'
                         + '      <fields_valuename>ERROR_COL_NAME</fields_valuename>\n'
                         + '      <codes_valuename>ERROR_CODE_COL</codes_valuename>\n'
                         + '      <max_errors/>\n'
                         + '      <max_pct_errors/>\n'
                         + '      <min_pct_rows/>\n'
                         + '    </error>\n'
                         )
        if mode_state in [0, '0']:
            str_error = ''
        str_step_error_handling = (str_step_error_handling
                                   + str_error
                                   + '  </step_error_handling>\n'
                                   + '  <slave-step-copy-partition-distribution>\n'
                                   + '  </slave-step-copy-partition-distribution>\n'
                                   + '  <slave_transformation>N</slave_transformation>\n'
                                   + '  <attributes/>\n'
                                   + '</transformation>\n'
                                   )
        return str_step_error_handling
