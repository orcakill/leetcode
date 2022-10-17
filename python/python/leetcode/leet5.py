from typing import List


class Solution:
    # 基础方案，遍历所有字符，判断字符是否是回文,查出最大
    def longestPalindrome(self, s: str) -> str:
        ss = ""
        max_length = 0
        for i in range(len(s)):
            for j in range(i + 1, len(s) + 1):
                s1 = s[i:j]
                s2 = reversed(list(s1))
                s1_length = len(s1)
                if list(s1) == list(s2):
                    if max_length < s1_length:
                        max_length = s1_length
                        ss = s1
        return ss

    # 动态规划方案
    def longestPalindrome1(self, s: str) -> str:
        # 获取字符串长度  babad 字符串长度为5
        n = len(s)
        # 如果长度小于2，则直接返回该字符，单个字符必定为回文
        if n < 2:
            return s
        # 截取长度
        max_len = 1
        # 开始截取字符
        begin = 0
        # dp[i][j] 表示 s[i..j] 是否是回文串  dp=[[False, False, False, False, False], [False, False, False, False, False], [False, False, False, False, False], [False, False, False, False, False], [False, False, False, False, False]]
        dp = [[False]*n  for _ in range(n)]
        for i in range(n):
            dp[i][i] = True

        # 递推开始
        # 先枚举子串长度
        for L in range(2, n + 1):
            # 枚举左边界，左边界的上限设置可以宽松一些
            for i in range(n):
                # 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                j = L + i - 1
                # 如果右边界越界，就可以退出当前循环
                if j >= n:
                    break

                if s[i] != s[j]:
                    dp[i][j] = False
                else:
                    if j - i < 3:
                        dp[i][j] = True
                    else:
                        dp[i][j] = dp[i + 1][j - 1]

                # 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if dp[i][j] and j - i + 1 > max_len:
                    max_len = j - i + 1
                    begin = i
        return s[begin:begin + max_len]


if __name__ == '__main__':
    print(Solution.longestPalindrome(0, 'babad'))
    print(Solution.longestPalindrome1(0, 'babad'))
