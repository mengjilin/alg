package leetcode;

/*
 * tags: math
 */
public class Lc012IntegertoRoman {
    public static String intToRoman(int num) {
        String[] symbols = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        String r = "";
        for (int i = 0; num > 0; ) {
            if (num >= values[i]) {
                r += symbols[i];
                num -= values[i];
            } else {
                i++;
            }
        }

        return r;
    }

    public static int romanToInt(String s) {
        String[] symbols = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        int num = 0;
        for (int i = 0, si = 0; si < s.length(); ) {
            if (symbols[i].charAt(0) == s.charAt(si) && (symbols[i].length() == 1 ||
                    (si < s.length() - 1 && symbols[i].charAt(1) == s.charAt(si+1)))) {
                si += symbols[i].length();
                num += values[i];
            } else {
                i++;
            }
        }

        return num;
    }

    public static void main(String[] args) {
        System.out.println("III".equals(intToRoman(3)));
        System.out.println("IV".equals(intToRoman(4)));
        System.out.println("IX".equals(intToRoman(9)));
        System.out.println("LVIII".equals(intToRoman(58)));
        System.out.println("MCMXCIV".equals(intToRoman(1994)));

        System.out.println(romanToInt("III") == 3);
        System.out.println(romanToInt("IV") == 4);
        System.out.println(romanToInt("IX") == 9);
        System.out.println(romanToInt("LVIII") == 58);
        System.out.println(romanToInt("MCMXCIV") == 1994);
    }
}
