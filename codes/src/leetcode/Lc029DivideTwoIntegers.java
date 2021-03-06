package leetcode;

/*
 * tags: bit
 * Time(n), Space(1)
 */
public class Lc029DivideTwoIntegers {
    public static int divide(int dividend, int divisor) {
        //return divideBit(dividend, divisor);
        return divideDc(dividend, divisor);
    }

    public static int divideBit(int dividend, int divisor) {
        if (divisor == 0 || (divisor == -1 && dividend == Integer.MIN_VALUE)) return Integer.MAX_VALUE;

        int ret = 0;
        boolean isPositive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        long dvd = Math.abs((long)dividend);
        long dvs = Math.abs((long)divisor);

        long exp = dvs;
        long quo = 1;
        while (exp < dvd) {
            exp <<= 1;
            quo <<= 1;
        }

        for (; exp >= dvs; exp >>= 1, quo >>= 1) {
            while (exp <= dvd) {
                dvd -= exp;
                ret += quo;
            }
        }

        return isPositive ? ret : -ret;
    }

    public static int divideDc(int dividend, int divisor) {
        if (divisor == 0 || (divisor == -1 && dividend == Integer.MIN_VALUE)) return Integer.MAX_VALUE;

        int ret = 0;
        boolean isPositive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        long dvd = Math.abs((long)dividend);
        long dvs = Math.abs((long)divisor);

        if (dvd < dvs) return 0;
        int q2 = divideDc(dividend, divisor + divisor);
        ret = q2 + q2 + (dvs < dvd && dvd < dvs + dvs ? isPositive ? 1 : -1 : 0);

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(divide(10, 3) == 10/3);
        System.out.println(divide(2147483647, 2) == 2147483647/2);
    }
}
