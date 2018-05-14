package leetcode;

import java.util.Stack;

/*
 * tags: stack
 * Time(n), Space(n)
 */
public class Lc020ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()") == true);
        System.out.println(isValid("()[]{}") == true);
        System.out.println(isValid("(]") == false);
        System.out.println(isValid("([)]") == false);
        System.out.println(isValid("{[]}") == true);
    }
}
