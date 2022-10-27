import os

from other.utils.my_logger import MyLogger


def kettle_data(self):
    strPath = os.getcwd()
    folderFiles = os.walk(strPath)
    filesPath = []
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
                    line = line.replace('</table>', '').replace('\n', '').replace('\r', '')
                    logger.info(filesPath[i]+':'+line)


if __name__ == '__main__':
    logger = MyLogger().get_logger()
    kettle_data(0)
    logger.info('执行完成')
