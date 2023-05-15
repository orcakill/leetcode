from typing import List


class Solution:
    @staticmethod
    def two_sum(nums: List[int], target: int) -> List[int]:
        for i in range(len(nums)-1):
            for j in range(i+1,len(nums)):
                if nums[i]+nums[j]==target:
                    return [i,j]





if __name__ == '__main__':
    num=[3,2,4]
    targets=6
    print(Solution.two_sum(num, targets))


