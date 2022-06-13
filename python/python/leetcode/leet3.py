def lengthOfLongestSubstring_1(s: str) -> int:
    """
    给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度-穷举法
    """
    if len(s) == 0:
        return 0
    list = []
    for i in range(len(s)):
        for j in range(i + 1, len(s) + 1):
            s1 = s[i:j]
            s2 = set(s1)
            s3 = "".join(sorted(s2, key=s1.index))
            if s1 == s3:
                list.append(len(s3))
    return max(list)


def lengthOfLongestSubstring_2(s: str) -> int:
    """
    给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度-滑动窗口，字符串不带重复
    """
    # 哈希集合，记录每个字符是否出现过
    occ = set()
    n = len(s)
    # 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
    rk, ans = -1, 0
    for i in range(n):
        if i != 0:
            # 左指针向右移动一格，移除一个字符
            occ.remove(s[i - 1])
        while rk + 1 < n and s[rk + 1] not in occ:
            # 不断地移动右指针
            occ.add(s[rk + 1])
            rk += 1
        # 第 i 到 rk 个字符是一个极长的无重复字符子串
        ans = max(ans, rk - i + 1)
    return ans




if __name__ == '__main__':
    print(lengthOfLongestSubstring_1("abcabcbb"))
    print(lengthOfLongestSubstring_1("bbbbb"))
    print(lengthOfLongestSubstring_1("pwwkew"))

    print(lengthOfLongestSubstring_2("abcabcbb"))
    print(lengthOfLongestSubstring_2("bbbbb"))
    print(lengthOfLongestSubstring_2("pwwkew"))
