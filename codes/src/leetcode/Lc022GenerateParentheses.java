package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * tags: stack, backtracking
 * Time(2^n), Space(2^n)
 */
public class Lc022GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        List<String> ret = new LinkedList<>();
        if (n == 0) return ret;
        rc(ret, n*2, 0, new char[2*n], 0);
        return ret;
    }

    private static void rc(List<String> result, int n, int ci, char[] cs, int balance) {
        if (ci >= n) {
            if (balance == 0) result.add(new String(cs));
            return;
        }

        if (balance < n - ci) {
            cs[ci] = '(';
            rc(result, n, ci + 1, cs, balance + 1);
        }
        if (balance > 0) {
            cs[ci] = ')';
            rc(result, n, ci + 1, cs, balance - 1);
        }
    }

    public static void main(String[] args) {
        List<String> exp = Arrays.asList(
                "((()))",
                "(()())",
                "(())()",
                "()(())",
                "()()()");
        System.out.println(exp.equals(generateParenthesis(3)));
    }
}

