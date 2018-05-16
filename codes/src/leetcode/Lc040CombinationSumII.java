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
        return combinationSumDp(candidates, target);
        //return combinationSumBacktracking(candidates, target);
    }

    static List<List<Integer>> combinationSumDp(int[] candidates, int target) {
        ArrayList<HashMap<List<Integer>, Boolean>> dp = new ArrayList<>(target + 1);
        for (int w = 0; w <= target; w++) dp.add(new HashMap<>());
        Arrays.sort(candidates);

        for (int i = 0; i < candidates.length; i++) {
            ArrayList<HashMap<List<Integer>, Boolean>> prev = deepCopy(dp);

            for (int w = 0; w <= target; w++) {
                if (w == candidates[i]) {
                    List<Integer> cl = new ArrayList<>(Arrays.asList(candidates[i]));
                    if (!dp.get(w).containsKey(cl)) dp.get(w).put(cl, true);
                } else if (w > candidates[i]) {
                    for (List<Integer> pl : prev.get(w - candidates[i]).keySet()) {
                        List<Integer> cl = new ArrayList<>(pl);
                        cl.add(candidates[i]);
                        if (!dp.get(w).containsKey(cl)) dp.get(w).put(cl, true);
                    }
                }
            }
        }

        return new ArrayList<>(dp.get(target).keySet());
    }

    static ArrayList<HashMap<List<Integer>, Boolean>> deepCopy(ArrayList<HashMap<List<Integer>, Boolean>> from) {
        ArrayList<HashMap<List<Integer>, Boolean>> to = new ArrayList<>(from.size());
        for (HashMap<List<Integer>, Boolean> lli : from) {
            HashMap<List<Integer>, Boolean> pli = new HashMap<>();
            for (List<Integer> li : lli.keySet()) pli.put(li, true);
            to.add(pli);
        }

        return to;
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
