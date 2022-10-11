from loguru import logger

def print_hi(name):
    # 在下面的代码行中使用断点来调试脚本。
    logger.debug('this is a debug message')
    print(f'Hi, {name}')  # 按 Ctrl+F8 切换断点。


# 按间距中的绿色按钮以运行脚本。
if __name__ == '__main__':
    logger.debug('this is a debug message')
    logger.info('this is another debug message')
    logger.warning('this is another debug message')
    print_hi('PyCharm1')
    logger.error('this is another debug message')
    logger.info('this is another debug message')
    logger.success('this is success message!')
    logger.critical('this is critical message!')
    print_hi('PyCharm2')

# 访问 https://www.jetbrains.com/help/pycharm/ 获取 PyCharm 帮助
