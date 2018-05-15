package leetcode;

import java.util.Arrays;

/*
 * tags: binary search
 * Time(logn), Space(1)
 */
public class Lc034SearchforaRange {
    public static int[] searchRange(int[] nums, int target) {
        for (int lo = 0, hi = nums.length - 1; lo <= hi; ) {
            int m = (lo + hi) / 2;
            if (nums[m] < target) lo = m + 1;
            else if (nums[m] > target) hi = m - 1;
            else { // found target
                int t = m;
                while (lo < t) {
                    int mi = (lo + t) / 2;
                    if (nums[mi] != target) lo = mi + 1;
                    else t = mi;
                }

                t = m;
                while (t < hi) {
                    int mi = (t + hi + 1) / 2;
                    if (nums[mi] != target) hi = mi - 1;
                    else t = mi;
                }

                return new int[]{lo, hi};
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int[] exp = new int[]{3, 4};
        System.out.println(Arrays.equals(exp, searchRange(nums, 8)));

        nums = new int[]{5, 7, 7, 8, 8, 10};
        exp = new int[]{-1, -1};
        System.out.println(Arrays.equals(exp, searchRange(nums, 6)));
        System.out.println(Arrays.equals(new int[]{0, 0}, searchRange(nums, 5)));
        System.out.println(Arrays.equals(new int[]{1, 2}, searchRange(nums, 7)));
        System.out.println(Arrays.equals(new int[]{5, 5}, searchRange(nums, 10)));
    }
}

