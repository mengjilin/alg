package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * tags: dp
 * Time(nw), Space(nw)
 * dp[n][w] = Sum(a[i] + dp[i][w-a[i]], i=0..n-1)
 */
public class Lc039CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<List<List<Integer>>>> dp = new ArrayList<>(candidates.length);
        Arrays.sort(candidates);
        for (int i = 0; i < candidates.length; i++) {
            ArrayList<List<List<Integer>>> li = new ArrayList<>(target + 1);
            dp.add(li);
            for (int w = 0; w <= target; w++)
                li.add(new LinkedList<>());
        }

        for (int i = 0; i < candidates.length; i++) {
        for (int w = 0; w <= target; w++) {
            if (w >= candidates[i]) {
                List<Integer> li = new LinkedList<>(dp.get(i).get(w - candidates[i]));
                li.add(candidates[i]);
                dp.get(i).add(li);
            }
            }
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
                Arrays.asList(2, 2, 2),
                Arrays.asList(2, 3, 3),
                Arrays.asList(3, 5));
        System.out.println(exp.equals(combinationSum(a, 8)));
    }
}
