package leetcode;

import java.util.Arrays;

/*
 * tags: binary search
 * Time(logn), Space(1)
 * if (nums[lo] <= nums[m]) then left side is sorted
 */
public class Lc033SearchinRotatedSortedArray {
    public static int search(int[] nums, int target) {
        for (int lo = 0, hi = nums.length - 1; lo <= hi; ) {
            int m = (lo+hi)/2;
            if (nums[m] == target) return m;

            if (nums[lo] <= nums[m]) { // left side is sorted
                if (nums[lo] <= target && target <= nums[m]) hi = m - 1;
                else lo = m + 1;
            } else { // right side is sorted
                if (nums[m] <= target && target <= nums[hi]) lo = m + 1;
                else hi = m - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(search(nums, 0) == 4);

        nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(search(nums, 3) == -1);
    }
}
