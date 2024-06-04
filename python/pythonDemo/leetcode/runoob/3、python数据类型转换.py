# 隐式类型转换

num_int = 123
num_flo = 1.23

num_new = num_int + num_flo

print("datatype of num_int:", type(num_int))
print("datatype of num_flo:", type(num_flo))

print("Value of num_new:", num_new)
print("datatype of num_new:", type(num_new))

# 实例中我们对两个不同数据类型的变量 num_int 和 num_flo 进行相加运算，并存储在变量 num_new 中。
# 然后查看三个变量的数据类型。
# 在输出结果中，我们看到 num_int 是 整型（integer） ， num_flo 是 浮点型（float）。
# 同样，新的变量 num_new 是 浮点型（float），这是因为 Python 会将较小的数据类型转换为较大的数据类型，以避免数据丢失。

# 显式类型转换
# int()强制转换为整型
x = int(1)  # x 输出结果为 1
y = int(2.8)  # y 输出结果为 2
z = int("3")  # z 输出结果为 3

# str()强制转换为字符型
x1 = str("s1")  # x1 输出结果为 's1'
y1 = str(2)  # y1 输出结果为 '2'
z1 = str(3.0)  # z1 输出结果为 '3.0'

# 整型和字符串类型进行运算，就可以用强制类型转换来完成：
num_int = 123
num_str = "456"

print("num_int 数据类型为:",type(num_int))
print("类型转换前，num_str 数据类型为:",type(num_str))

num_str = int(num_str)    # 强制转换为整型
print("类型转换后，num_str 数据类型为:",type(num_str))

num_sum = num_int + num_str

print("num_int 与 num_str 相加结果为:",num_sum)
print("sum 数据类型为:",type(num_sum))

# int(x [,base])	将x转换为一个整数
# float(x)	将x转换到一个浮点数
# complex(real [,imag])	创建一个复数
# str(x)	将对象 x 转换为字符串
# repr(x)	将对象 x 转换为表达式字符串
# eval(str)	用来计算在字符串中的有效Python表达式,并返回一个对象
# tuple(s)	将序列 s 转换为一个元组
# list(s)	将序列 s 转换为一个列表
# set(s)	转换为可变集合
# dict(d)	创建一个字典。d 必须是一个 (key, value)元组序列。
# frozenset(s)	转换为不可变集合
# chr(x)	将一个整数转换为一个字符
# ord(x)	将一个字符转换为它的整数值
# hex(x)	将一个整数转换为一个十六进制字符串
# oct(x)	将一个整数转换为一个八进制字符串
