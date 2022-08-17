from typing import List


def find_median_sorted_arrays(self, nums1: List[int], nums2: List[int]) -> float:
    nums1=nums1+nums2;
    nums1.sort()
    x=len(nums1)/2


    return 1


if __name__ == '__main__':
    nums1 = [1, 3]
    nums2 = [2]
    print(find_median_sorted_arrays(nums1, nums2))
