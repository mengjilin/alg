package leetcode;

import java.util.*;

/*
 * tags: unbounded-knapsack, dp, backtracking
 * Time(nw), Space(nw)
 * dp[w] = [dp[w-a[i]],a[i]] (i=0..n-1)
 * backtracking: Time(n!), Space(n!)
 * candidates are unbounded
 */
public class Lc039CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        return combinationSumDp(candidates, target);
        //return combinationSumBacktracking(candidates, target);
    }

    static List<List<Integer>> combinationSumDp(int[] candidates, int target) {
        Vector<List<List<Integer>>> dp = new Vector<>(target + 1);
        for (int w = 0; w <= target; w++) dp.add(new ArrayList<>());
        Arrays.sort(candidates);

        for (int i = 0; i < candidates.length; i++) {
            for (int w = 0; w <= target; w++) {
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

        return dp.get(target);
    }

    static Vector<List<List<Integer>>> copy(Vector<List<List<Integer>>> from) {
        Vector<List<List<Integer>>> to = new Vector<>(from.size());
        for (List<List<Integer>> lli : from) {
            List<List<Integer>> pli = new ArrayList<>();
            for (List<Integer> li : lli) pli.add(li);
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

    static void combinationSumBacktrackingRc(int[] candidates, int remain, int start, List<List<Integer>> result, List<Integer> selected) {
        if (remain < 0) return;
        if (remain == 0) result.add(new ArrayList(selected));

        for (int i = start; i < candidates.length; i++) {
            selected.add(candidates[i]);
            combinationSumBacktrackingRc(candidates, remain - candidates[i], i, result, selected);
            selected.remove(selected.size() - 1); // backtracking
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 6, 7};
        List<List<Integer>> exp = Arrays.asList(
                Arrays.asList(2, 2, 3),
                Arrays.asList(7));
        System.out.println(exp.equals(combinationSum(a, 7)));

        a = new int[]{2, 3, 5};
        exp = Arrays.asList(
                Arrays.asList(2, 2, 2, 2),
                Arrays.asList(2, 3, 3),
                Arrays.asList(3, 5));
        System.out.println(exp.equals(combinationSum(a, 8)));
    }
}
