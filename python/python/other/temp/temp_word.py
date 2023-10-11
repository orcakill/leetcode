# @Time: 2023年10月11日 18:23
# @Author: orcakill
# @File: temp_word.py
# @Description: 测试

if __name__ == '__main__':
    string = 'JHBM:"汉字井号", JHDM:"井号代码"'

    # 使用正则表达式提取汉字井号和井号代码
    import re

    JHBM = re.search(r'JHBM:"(.*?)"', string).group(1)
    JHDM = re.search(r'JHDM:"(.*?)"', string).group(1)

    # 构建新的字符串
    new_string = f'null JHBM /*{JHBM}*/, null JHDM /*{JHDM}*/'

    # 以逗号换行输出
    new_string = new_string.replace(', ', ',\n')

    print(new_string)