import itertools as its
import string

def password_start():
    # words = string.printable #可选择的字符
    words = "1234567890"  # 可选择的字符
    r =its.product(words,repeat=8)  #组成8位字符串
    dic = open("d:\\pwd11.txt","a")      #存储为wifi密码字典
    #wifi密码完成换行，并写入txt文档
    for i in r:
        dic.write("".join(i))
        dic.write("".join("\n"))
    dic.close()


if __name__ == "__main__":
    password_start()