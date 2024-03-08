
import pandas as pd
import os
import sys

from loguru import logger

my_log_file_path = "test_log"


class MyLogger:
    def __init__(self, log_file_path=my_log_file_path):
        if os.path.exists(my_log_file_path):
            os.remove(my_log_file_path)
        self.logger = logger
        # 清空所有设置
        self.logger.remove()
        # 添加控制台输出的格式,sys.stdout为输出到屏幕;关于这些配置还需要自定义请移步官网查看相关参数说明
        self.logger.add(sys.stdout,
                        format="<green>{time:YYYY-MM-DD HH:mm:ss}</green> | "  # 颜色>时间
                               "{process.name} | "  # 进程名
                               "{thread.name} | "  # 进程名
                               "<cyan>{module}</cyan>.<cyan>{function}</cyan>"  # 模块名.方法名
                               ":<cyan>{line}</cyan> | "  # 行号
                               "<level>{level}</level>: "  # 等级
                               "<level>{message}</level>",  # 日志内容
                        )
        # 输出到文件的格式,注释下面的add',则关闭日志写入
        self.logger.add(log_file_path, level='DEBUG',
                        format='{time:YYYY-MM-DD HH:mm:ss} - '  # 时间
                               "{process.name} | "  # 进程名
                               "{thread.name} | "  # 进程名
                               '{module}.{function}:{line} - {level} -{message}',  # 模块名.方法名:行号
                        rotation="10 MB")

    def get_logger(self):
        return self.logger


def kettle_data():
    strPath = os.getcwd()
    folderFiles = os.walk(strPath)
    filesPath = []
    dir_files = []
    logger.info('查找文件夹下ktr文件插入更新的表及ktr文件路径')
    logger.info('第一步：确定当前文件夹')
    logger.info("当前文件夹" + os.getcwd())
    logger.info('第二步：确定当前文件夹下所有ktr文件')
    for root, dirs, files in folderFiles:
        for name in files:
            files_name = os.path.join(root, name)
            files_suffix = files_name.split(".")[-1]
            if files_suffix == 'ktr':
                logger.info(files_name)
                filesPath.append(files_name)
    logger.info('第三步：查找ktr文件下表的信息')
    for i in range(0, len(filesPath)):
        with open(filesPath[i], 'r', encoding='utf-8-sig') as lines:
            for line in lines:
                if '<table>' in line:
                    line = line.replace(' ', '')
                    line = line.replace('<table>', '')
                    line = line.replace('</table>', '').replace('/n', '').replace('/r', '')
                    logger.info(filesPath[i] + ':' + line)
                    dir_file = {'路径': filesPath[i], '表名': line.lower()}
                    dir_files.append(dir_file)
    resultFile = strPath + 'kettle_excel.xlsx'
    if os.path.exists(resultFile):
        os.remove(resultFile)
    pf = pd.DataFrame(dir_files)
    # 指定字段顺序
    order = ['路径', '表名']
    pf = pf[order]
    # 指定生成的Excel表格名称
    file_path = pd.ExcelWriter(f'kettle_excel.xlsx')
    # 输出
    pf.to_excel(file_path, sheet_name='pandas', index=False)
    # 保存表格
    file_path.close()


if __name__ == '__main__':
    # pyinstaller -F kettle_data.py 最终生成exe文件
    logger = MyLogger().get_logger()
    kettle_data()
    logger.info('执行完成')
