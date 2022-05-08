str = '123456789'

# 输出字符串
print(str)
# 输出字符串第一个至倒数第二个字符
print(str[0:-1])
# 输出字符串第一个字符
print(str[0])
# 输出字符串第3个到第6个字符
print(str[2:5])
# 输出第三个字符以后的字符
print(str[2:])
# 输出第第二个到第六个个字符之间的字符，步长为2
print(str[1:5:2])
# 输出字符串两次
print(str * 2)
# 输出字符串拼接
print(str + "2")

print('------------------------------')

# 输出是否转译的区别
print("hello\n run")
print(r"hello\n run")

# 输出时是否换行
print(str, end="")
print(str)

import sys

print('================Python import mode==========================')
print('命令行参数为:')
for i in sys.argv:
    print(i)
print('\n python 路径为', sys.path)

from sys import argv, path  # 导入特定的成员

print('================python from import===================================')
# 因为已经导入path成员，所以此处引用时不需要加sys.path
print('path:', path)