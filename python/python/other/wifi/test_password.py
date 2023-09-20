import itertools as its


def password_start():
    # words = string.printable #可选择的字符
    words = "1234567890"  # 可选择的字符
    r = its.product(words, repeat=8)  # 组成8位字符串
    dic = open("d:\\pwd8.txt", "a")  # 存储为wifi密码字典
    # wifi密码完成换行，并写入txt文档
    for i in r:
        dic.write("".join(i))
        dic.write("".join("\n"))
    dic.close()


def password_start_landline():
    # words = string.printable #可选择的字符
    words = "1234567890"  # 可选择的字符
    r = its.product(words, repeat=7)  # 组成7位字符串
    dic = open("d:\\pwdLandline.txt", "a")  # 存储为wifi密码字典
    # wifi密码完成换行，并写入txt文档
    for i in r:
        dic.write("".join("0546"))
        dic.write("".join(i))
        dic.write("".join("\n"))
    dic.close()


if __name__ == "__main__":
    password_start_landline()
