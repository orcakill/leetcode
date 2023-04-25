class Solution:
    # 方法一  利用二维矩阵模拟
    @staticmethod
    def convert(s: str, numRows: int) -> str:
        # 字符长度n  待转行数 numRows
        n, r = len(s), numRows
        # 如果字符长度为1或者转换的字符长度大于待转的行数，直接返回字符s
        if r == 1 or r >= n:
            return s
        t = r * 2 - 2
        # // 表示整数除法，它可以返回商的整数部分（向下取整）
        c = (n + t - 1) // t * (r - 1)
        # 列表生成器写法，类似循环添加空值到list中
        mat = [[''] * c for _ in range(r)]
        x, y = 0, 0
        # enumerate函数，枚举集合的序号和值
        for i, ch in enumerate(s):
            mat[x][y] = ch
            if i % t < r - 1:
                x += 1
            else:
                x -= 1
                y += 1
        #   join连接任意数量的字符串（包括要连接的元素字符串、元组、列表、字典），用新的目标分隔符连接，返回新的字符串。
        return ''.join(ch for row in mat for ch in row if ch)


if __name__ == '__main__':
    print(Solution.convert("PAYPALISHIRING", 3))

