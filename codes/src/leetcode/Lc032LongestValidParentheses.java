package leetcode;

import java.util.Stack;

/*
 * tags: stack
 * Time(n), Space(n)
 */
public class Lc032LongestValidParentheses {
    public static int longestValidParentheses(String s) {
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

    public static void main(String[] args) {
        String s = "(()";
        System.out.println(longestValidParentheses(s) == 2);

        s = ")()())";
        System.out.println(longestValidParentheses(s) == 4);

        s = "()(())";
        System.out.println(longestValidParentheses(s) == 6);
    }
}
