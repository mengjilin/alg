package leetcode;

public class Lc008atoi {
    public static int atoi(String s) {
        long r = 0;
        int i = 0;

        // leading spaces
        while (i < s.length() && s.charAt(i) == ' ') i++;

        // sign
        boolean isPositive = i < s.length() && s.charAt(i) != '-';
        if (i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) i++;

        for (; i < s.length() && '0' <= s.charAt(i) && s.charAt(i) <= '9'; i++) {
            r = r * 10L + (s.charAt(i) - '0');
            if (isPositive && r >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (!isPositive && r - 1 >= Integer.MAX_VALUE) return Integer.MIN_VALUE;
        }

        return isPositive ? (int)r : -(int)r;
    }

    public static void main(String[] args) {
        System.out.println(atoi("123") == 123);
        System.out.println(atoi("    -42") == -42);
        System.out.println(atoi("234 abc") == 234);
        System.out.println(atoi("fda 234") == 0);
        System.out.println(atoi("-91283472332") == -2147483648);
    }
}
