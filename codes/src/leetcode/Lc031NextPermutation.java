package leetcode;

import java.util.*;

/*
 * tags: math, permutation
 * Time(n), Space(1)
 * 1. find the first inversion point p (where a[p] < a[p+1]) from the end
 * 2. find the first num greater than a[p] from the end, then swap it with p
 * 3. inverse a[p+1..n-1]
 */
public class Lc031NextPermutation {
    public static void nextPermutation(int[] nums) {
        int p = nums.length - 2;
        while (p >= 0 && nums[p] >= nums[p + 1])
            p--;

        if (p >= 0) for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[p]) {
                int tmp = nums[i];
                nums[i] = nums[p];
                nums[p] = tmp;
                break;
            }
        }

        for (int i = p + 1, j = nums.length - 1; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int[] exp = new int[]{1, 3, 2};
        nextPermutation(nums);
        System.out.println(Arrays.equals(exp, nums));

        nums = new int[]{1, 3, 2};
        exp = new int[]{2, 1, 3};
        nextPermutation(nums);
        System.out.println(Arrays.equals(exp, nums));

        nums = new int[]{5, 1, 1};
        exp = new int[]{1, 1, 5};
        nextPermutation(nums);
        System.out.println(Arrays.equals(exp, nums));
    }
}
