class Solution:
    def num_different_integers(self: str) -> int:
        setList = set()
        s1 = ""
        for i in range(len(self)):
            s = self[i:i + 1:1]
            if is_number(s):
                s1 = s1 + s
            else:
                if is_number(s1):
                    setList.add(s1)
                    s1 = ""
        return len(setList)


def is_number(s: str) -> bool:
    """判断字符串是否为数字"""
    try:
        float(s)
        return True
    except ValueError:
        return False


if __name__ == '__main__':
    print(Solution.num_different_integers('a123bc34d8ef34'))

