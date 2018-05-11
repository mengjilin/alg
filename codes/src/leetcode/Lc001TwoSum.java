package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/*
 * tags: hash
 * Time(n), Space(n)
 * hash
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class Lc001TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer j = map.get(target - nums[i]);
            if (j != null) {
                return new int[]{j.intValue(), i};
            }
            map.put(nums[i], i);
        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] exp = {0, 1};
        System.out.println(Arrays.equals(twoSum(nums, 9), exp));
    }
}
