package alg.string;

public class LongestPalindromicSubstring_Manacher {
    public static String lps(String s) {
        int n = s.length() * 2 + 1;
        int[] L = new int[n];
        L[1] = 1;
        int c = 1; // center position of current palindrome
        int r = 2; // c + L[c]
        int maxi = 1;

        for (int i = 2; i < n; i++) {
            L[i] = i > r ? 0 : Math.min(L[2*c-i], r - i);
            while ((i + L[i] + 1) % 2 == 0 ||
                    (i + L[i] + 1 < n && i - L[i] - 1 >= 0 &&
                    s.charAt((i + L[i] + 1) / 2) == s.charAt((i - L[i] - 1) / 2))) {
                L[i]++;
            }
            if (L[i] > L[maxi]) maxi = i;
            if (i > r) {
                c = i;
                r = c + L[c];
            }
        }

        return s.substring((maxi - L[maxi]) / 2, (maxi + L[maxi]) / 2);
    }

    public static void main(String[] args) {
        String s = "babcbabcbaccba";
        System.out.println(lps(s).equals("abcbabcba"));

        s = "abaaba";
        System.out.println(lps(s).equals("abaaba"));

        s = "abababa";
        System.out.println(lps(s).equals("abababa"));

        s = "abcbabcbabcba";
        System.out.println(lps(s).equals("abcbabcbabcba"));

        s = "forgeeksskeegfor";
        System.out.println(lps(s).equals("geeksskeeg"));

        s = "caba";
        System.out.println(lps(s).equals("aba"));

        s = "abacdfgdcaba";
        System.out.println(lps(s).equals("aba"));

        s = "abacdfgdcabba";
        System.out.println(lps(s).equals("abba"));

        s = "abacdedcaba";
        System.out.println(lps(s).equals("abacdedcaba"));

        s = "babad";
        System.out.println(lps(s).equals("bab"));
    }
}
