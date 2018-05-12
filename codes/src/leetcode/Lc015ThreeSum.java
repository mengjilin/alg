package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * tags: two pointers
 * Time(n^2), Space(1)
 */
public class Lc015ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // Never let i refer to the same value twice to avoid duplicates.
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                if (nums[i] + nums[j] + nums[k] < 0) j++;
                else if (nums[i] + nums[j] + nums[k] > 0) k--;
                else {
                    ret.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    // Never let j refer to the same value twice (in an output) to avoid duplicates
                    while (j < k && nums[j] == nums[j-1]) ++j;
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] a = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> exp = Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1));
        System.out.println(exp.equals(threeSum(a)));
    }
}
