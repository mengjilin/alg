package leetcode;

import alg.Test;

import java.util.*;

/*
 * tags: 0/1-knapsack, dp, backtracking
 * Time(nw), Space(nw)
 * dp[i][w] = [dp[i-1][w-a[i]],a[i]] (i=0..n-1)
 * backtracking: Time(n!), Space(n!)
 * candidates are not unique, can not be used multiple times
 */
public class Lc040CombinationSumII {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        //return combinationSumDp(candidates, target);
        return combinationSumBacktracking(candidates, target);
    }

    static List<List<Integer>> combinationSumDp(int[] candidates, int target) {
        ArrayList<HashSet<List<Integer>>> dp = new ArrayList<>(target + 1);
        for (int w = 0; w <= target; w++) dp.add(new HashSet<>());
        Arrays.sort(candidates);

        for (int i = 0; i < candidates.length; i++) {
            for (int w = target; w >= 0; w--) { // reverse iteration to avoid item reused.
                if (w == candidates[i]) {
                    dp.get(w).add(new ArrayList<>(Arrays.asList(candidates[i])));
                } else if (w > candidates[i]) {
                    for (List<Integer> pl : dp.get(w - candidates[i])) {
                        List<Integer> cl = new ArrayList<>(pl);
                        cl.add(candidates[i]);
                        dp.get(w).add(cl);
                    }
                }
            }
        }

        return new ArrayList<>(dp.get(target));
    }

    static List<List<Integer>> combinationSumBacktracking(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ret = new ArrayList<>();
        combinationSumBacktrackingRc(candidates, target, 0, ret, new ArrayList<>());
        return ret;
    }

    static void combinationSumBacktrackingRc(int[] nums, int remain, int start, List<List<Integer>> result, List<Integer> selected) {
        if (remain < 0) return;
        if (remain == 0) result.add(new ArrayList(selected));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
            selected.add(nums[i]);
            combinationSumBacktrackingRc(nums, remain - nums[i], i + 1, result, selected);
            selected.remove(selected.size() - 1); // backtracking
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> res = combinationSum(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        res.sort(Test.LISTLIST_COMP);
        List<List<Integer>> exp = Arrays.asList(
                Arrays.asList(1, 1, 6),
                Arrays.asList(1, 2, 5),
                Arrays.asList(1, 7),
                Arrays.asList(2, 6));
        System.out.println(exp.equals(res));

        res = combinationSum(new int[]{2, 5, 2, 1, 2}, 5);
        res.sort(Test.LISTLIST_COMP);
        exp = Arrays.asList(
                Arrays.asList(1, 2, 2),
                Arrays.asList(5));
        System.out.println(exp.equals(res));
    }
}
