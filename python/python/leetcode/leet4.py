import math
from typing import List


def find_median_sorted_arrays(self, nums1: List[int], nums2: List[int]) -> float:
    nums1 = nums1 + nums2
    nums1.sort()
    x = math.ceil(len(nums1) / 2) - 1
    if len(nums1) % 2 == 0:
        return (nums1[x] + nums1[x + 1]) / 2
    else:
        return nums1[x]


# 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数
# 算法的时间复杂度应该为 O(log (m+n))
if __name__ == '__main__':
    nums1 = [1, 3]
    nums2 = [2]
    print(find_median_sorted_arrays(0, nums1, nums2))
