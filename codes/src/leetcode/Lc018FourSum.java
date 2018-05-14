package leetcode;

import java.util.*;

/*
 * tags: two pointers, hash
 * Time(n^3), Space(1)
 */
public class Lc018FourSum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new LinkedList<>();
        Arrays.sort(nums);

        Set<List<Integer>> items = new HashSet<>();
        for (int s = 0; s < nums.length; s++) {
            for (int i = s + 1; i < nums.length; i++) {
                for (int j = i + 1, k = nums.length - 1; j < k; ) {
                    if (nums[s] + nums[i] + nums[j] + nums[k] < target) j++;
                    else if (nums[s] + nums[i] + nums[j] + nums[k] > target) k--;
                    else {
                        List<Integer> item = Arrays.asList(nums[s], nums[i], nums[j], nums[k]);
                        if (!items.contains(item)) {
                            items.add(item);
                            ret.add(item);
                        }
                        j++;
                    }
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 0, -1, 0, -2, 2};
        List<List<Integer>> exp = Arrays.asList(
                Arrays.asList(-2, -1, 1, 2),
                Arrays.asList(-2,  0, 0, 2),
                Arrays.asList(-1,  0, 0, 1));
        System.out.println(exp.equals(fourSum(a, 0)));

        a = new int[]{-3,-2,-1,0,0,1,2,3};
        exp = Arrays.asList(
                Arrays.asList(-3,-2,2,3),
                Arrays.asList(-3,-1,1,3),
                Arrays.asList(-3,0,0,3),
                Arrays.asList(-3,0,1,2),
                Arrays.asList(-2,-1,0,3),
                Arrays.asList(-2,-1,1,2),
                Arrays.asList(-2,0,0,2),
                Arrays.asList(-1,0,0,1));
        System.out.println(exp.equals(fourSum(a, 0)));

        a = new int[]{-5,-4,-3,-2,-1,0,0,1,2,3,4,5};
        exp = Arrays.asList(
                Arrays.asList(-5,-4,4,5),
                Arrays.asList(-5,-3,3,5),
                Arrays.asList(-5,-2,2,5),
                Arrays.asList(-5,-2,3,4),
                Arrays.asList(-5,-1,1,5),
                Arrays.asList(-5,-1,2,4),
                Arrays.asList(-5,0,0,5),
                Arrays.asList(-5,0,1,4),
                Arrays.asList(-5,0,2,3),
                Arrays.asList(-4,-3,2,5),
                Arrays.asList(-4,-3,3,4),
                Arrays.asList(-4,-2,1,5),
                Arrays.asList(-4,-2,2,4),
                Arrays.asList(-4,-1,0,5),
                Arrays.asList(-4,-1,1,4),
                Arrays.asList(-4,-1,2,3),
                Arrays.asList(-4,0,0,4),
                Arrays.asList(-4,0,1,3),
                Arrays.asList(-3,-2,0,5),
                Arrays.asList(-3,-2,1,4),
                Arrays.asList(-3,-2,2,3),
                Arrays.asList(-3,-1,0,4),
                Arrays.asList(-3,-1,1,3),
                Arrays.asList(-3,0,0,3),
                Arrays.asList(-3,0,1,2),
                Arrays.asList(-2,-1,0,3),
                Arrays.asList(-2,-1,1,2),
                Arrays.asList(-2,0,0,2),
                Arrays.asList(-1,0,0,1));
        System.out.println(exp.equals(fourSum(a, 0)));
    }
}
