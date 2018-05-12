package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * tags: two pointers
 * Time(n^2), Space(1)
 */
public class Lc016ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        int ret = 0, minGap = Integer.MAX_VALUE;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                int gap = Math.abs(nums[i] + nums[j] + nums[k] - target);
                if (minGap > gap) {
                    minGap = gap;
                    ret = nums[i] + nums[j] + nums[k];
                }

                if (nums[i] + nums[j] + nums[k] < target) j++;
                else k--;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] a = new int[]{-1, 2, 1, -4};
        System.out.println(threeSumClosest(a, 1) == 2);
    }
}
