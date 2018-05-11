package leetcode;

/*
 * tags: calculation
 * Time(n), Space(1)
 */
public class Lc006Zipzap {
    public static String convert(String s, int numRows) {
        if (s == null || numRows <= 1 || numRows >= s.length()) return  s;

        char[] r = new char[s.length()];
        int ri = 0;
        for (int row = 0; row < numRows; row++) {
            int i = row;
            while (i < s.length()) {
                r[ri++] = s.charAt(i);

                i += (numRows - row - 1) * 2;
                if (0 < row && row < numRows - 1 && i < s.length()) {
                    r[ri++] = s.charAt(i);
                }
                i += row * 2;
            }
        }

        return new String(r);
    }

    public static void main(String[] args) {
        String s = "0123456789";
        System.out.println("0481357926".equals(convert(s, 3)));

        s = "PAYPALISHIRING";
        System.out.println("PAHNAPLSIIGYIR".equals(convert(s, 3)));

        s = "PAYPALISHIRING";
        System.out.println("PINALSIGYAHRPI".equals(convert(s, 4)));
    }
}
