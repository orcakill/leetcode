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
        c = (n + t - 1) // t * (r - 1)
        mat = [[''] * c for _ in range(r)]
        x, y = 0, 0
        for i, ch in enumerate(s):
            mat[x][y] = ch
            if i % t < r - 1:
                x += 1
            else:
                x -= 1
                y += 1
        return ''.join(ch for row in mat for ch in row if ch)


if __name__ == '__main__':
    print(Solution.convert("PAYPALISHIRING", 3))

