"""
# @Time: 2024年08月19日23:49
# @Author: orcakill
# @File: sql转字符.py
# @Description: sql字符
"""

if __name__ == '__main__':
    with open('D:\\1.txt', 'r', encoding='utf-8') as file:
        for line in file:
            line1 = '                                            '+line.replace('\n','') + " " + '\\'
            print(line1)
