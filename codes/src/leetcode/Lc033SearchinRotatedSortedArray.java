package leetcode;

import java.util.Arrays;

/*
 * tags: binary search
 * Time(logn), Space(1)
 * if middle is less than target, and left side is sorted or (right is sorted and ) target is less than the biggest on right,
 * then it's at right side
 */
public class Lc033SearchinRotatedSortedArray {
    public static int search(int[] nums, int target) {
        for (int lo = 0, hi = nums.length - 1; lo <= hi; ) {
            int m = (lo+hi)/2;
            if (nums[m] == target) return m;
            else if (nums[m] < target) {
                // left is sorted, or it's at right side
                if (nums[lo] <= nums[m] || target <= nums[hi]) lo = m + 1;
                else hi = m - 1;
            } else { // target < nums[m]
                // right is sorted, or it's at left side
                if (nums[m] <= nums[hi] || nums[lo] <= target) hi = m - 1;
                else lo = m + 1;
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
