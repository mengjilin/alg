package leetcode;

/*
 * tags: calculation
 * Time(n), Space(1)
 */
public class Lc007ReverseInteger {
    public static int reverse(int x) {
        long r = 0;
        boolean neg = x < 0;
        while (x != 0) {
            r  = r * 10 + (x % 10);
            x /= 10;
        }

        if ((!neg && r > Integer.MAX_VALUE) || (neg && r < Integer.MIN_VALUE))
            return 0;

        return (int)r;
    }

    public static void main(String[] args) {
        System.out.println(321 == reverse(123));
        System.out.println(-321 == reverse(-123));
        System.out.println(21 == reverse(120));
    }
}
