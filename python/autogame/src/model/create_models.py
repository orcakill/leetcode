import configparser
import re

from sqlalchemy import create_engine, MetaData


def create_models():
    # 第一部分
    model_file1 = "# coding: utf-8\n\r" \
                  "from sqlalchemy import BigInteger, Column, DateTime, Integer, String\n\r" \
                  "from sqlalchemy.ext.declarative import declarative_base\n\r\n\r" \
                  "Base = declarative_base()\n\r" \
                  "metadata = Base.metadata\n\r\n\r"
    # 第二部分
    model_file2 = ""
    config = configparser.ConfigParser()
    config.read("config.ini", encoding="utf-8")
    url = config.get("database", "url")
    # 创建数据库连接
    engine = create_engine(url)
    # 创建元数据对象
    metadata = MetaData()
    # 获取所有表名和字段信息
    metadata.reflect(engine)
    # 打印所有表名和字段信息
    for table_name, table in metadata.tables.items():
        # 类名大写
        class_name = table_name.capitalize()
        # 转换成驼峰命名
        class_name = re.sub(r'_(\w)', lambda m: m.group(1).upper(), class_name)
        model_file2 = model_file2 + "class " + class_name + "(Base):\n\r" + "    __tablename__ = " + table_name \
                      + "\n\r\n\r"
        for column_name, column in table.columns.items():
            type=mysql_type_to_python_type(column.type.__class__.__name__)
            length=column.length
            column_file=column_name + "=Column(" +type
            if type=="String":
                column_file=column_file+"("+length
            model_file2 = model_file2 + "    " +column_file+ "\n\r"
        model_file2 = model_file2 + "\n\r"
    model_file = model_file1 + model_file2
    print(model_file)


def mysql_type_to_python_type(mysql_type):
    python_type = None
    if mysql_type == 'VARCHAR':
        python_type = 'String'
    elif mysql_type == 'INT':
        python_type = 'int'
    elif mysql_type == 'INTEGER':
        python_type = 'Integer'
    elif mysql_type == 'TEXT':
        python_type = 'String'
    elif mysql_type == 'DATE':
        python_type = 'datetime.date'
    elif mysql_type == 'DATETIME':
        python_type = 'DateTime'
    elif mysql_type == 'BOOLEAN':
        python_type = 'bool'
    elif mysql_type == 'FLOAT':
        python_type = 'float'
    elif mysql_type == 'DOUBLE':
        python_type = 'float'
    elif mysql_type == 'BLOB':
        python_type = 'bytes'
    elif mysql_type == 'BIGINT':
        python_type = 'BigInteger'

    return python_type


if __name__ == '__main__':
    # print(f"\tColumn: {column_name}, Type: {column.type.__class__.__name__}")
    create_models()
