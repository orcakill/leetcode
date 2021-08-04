package leetcode;

public class leet11  {
    public static void main(String[] args) {
        int[] a1 =  {1,8,6,2,5,4,8,3,7};
          int[] b1={1,1};
          int[] c1={4,3,2,1,4};
        int[] d1={1,2,1};
        System.out.println(maxArea(a1));
        System.out.println(maxArea(b1));
        System.out.println(maxArea(c1));
        System.out.println(maxArea(d1));
    }

    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }
}
