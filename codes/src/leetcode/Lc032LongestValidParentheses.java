package leetcode;

import java.util.Stack;

/*
 * tags: stack, dp, two pointers
 * Time(n), Space(n)
 */
public class Lc032LongestValidParentheses {
    public static int longestValidParentheses(String s) {
        //return longestValidParenthesesWithDp(s);
        //return longestValidParenthesesStack(s);
        return longestValidParenthesesWithTwoPointers(s);
    }

    /*
     * dp[i] is the longest valid parentheses ending with i,
     * dp[i] = 0  when s[i] == '(', else
     * dp[i] = dp[i-2] + 2 if s[i-1] == '(', else
     * dp[i] = dp[i-1] + 2 + dp[i-dp[i-2]-2] if s[i-1] == ')' and s[i-dp[i-2]-1] == '('
     */
    static int longestValidParenthesesWithDp(String s) {
        int ret = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(')
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                else if (i - dp[i - 1] > 0 && s.charAt(i - 1) == ')' && s.charAt(i - dp[i - 1] - 1) == '(')
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0);
                ret = Math.max(ret, dp[i]);
            }
        }

        return ret;
    }

    static int longestValidParenthesesWithStack(String s) {
        int ret = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) stack.push(i);
                else ret = Math.max(ret, i - stack.peek());
            }
        }

        return ret;
    }

    /*
     * scan from left to right, track two counters (left/right),
     * and then scan from right to left to do the same thing
     */
    static int longestValidParenthesesWithTwoPointers(String s) {
        int ret = 0;
		
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++; else right++;
            if (left == right) ret = Math.max(ret, 2 * left);
            else if (right > left) left = right = 0;
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++; else right++;
            if (left == right) ret = Math.max(ret, 2 * left);
            else if (right < left) left = right = 0;
        }

        return ret;
    }

    public static void main(String[] args) {
        String s = "(()";
        System.out.println(longestValidParentheses(s) == 2);

        s = ")()())";
        System.out.println(longestValidParentheses(s) == 4);

        s = "()(())";
        System.out.println(longestValidParentheses(s) == 6);
    }
}
