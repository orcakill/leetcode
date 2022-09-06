from typing import List


class Solution:
    # 基础方案，遍历所有字符，判断字符是否是回文,查出最大
    def longestPalindrome(self, s: str) -> str:
        ss=""
        max_length=0
        for i in range(len(s)):
            for j in range(i + 1, len(s) + 1):
                s1 = s[i:j]
                s2 = reversed(list(s1))
                s1_length=len(s1)
                if list(s1) == list(s2):
                    if max_length<s1_length:
                        max_length=s1_length
                        ss=s1
        return ss


if __name__ == '__main__':
    print(Solution.longestPalindrome(0,'babad'))
