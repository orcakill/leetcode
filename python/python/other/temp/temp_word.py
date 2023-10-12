# @Time: 2023年10月11日 18:23
# @Author: orcakill
# @File: temp_word.py
# @Description: 将JHDM:"井号代码"转为 null JHDM /*井号代码*/,

if __name__ == '__main__':
    with open('D:\\1.txt', 'r', encoding='utf-8') as file:
        for line in file:
            line = line.strip()  # 去除行首和行尾的空白字符
            if line:  # 判断是否为空行
                line1 = line.replace(':"', ' /*')
                line2 = line1.replace('",', '*/,')
                print('  null ' + line2)
