package leetcode;

/*
 * tags: dp, recursive
 */
public class Lc010RegularExpressionMatching {
    public static boolean isMatch(String s, String p) {
        //return isMatchRecursive(s, p);
        return isMatchDp(s, p);
    }

    /*
     * Time((n+m)*2^(n+m/2)), Space((n+m)*2^(n+m/2))
     */
    public static boolean isMatchRecursive(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean m = !s.isEmpty() && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0));
        return (p.length() > 1 && p.charAt(1) == '*')
                ? isMatchRecursive(s, p.substring(2)) || m && isMatchRecursive(s.substring(1), p)
                : m && isMatchRecursive(s.substring(1), p.substring(1));
    }

    /*
     * Time(nm), Space(nm)
     */
    public static boolean isMatchDp(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean m = i < s.length() && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j));
                dp[i][j] = j < p.length() - 1 && p.charAt(j + 1) == '*'
                        ? dp[i][j+2] || m && dp[i+1][j]
                        : m && dp[i+1][j+1];
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a") == false);
        System.out.println(isMatch("aa", "a*") == true);
        System.out.println(isMatch("ab", ".*") == true);
        System.out.println(isMatch("aab", "c*a*b") == true);
        System.out.println(isMatch("a", "ab*") == true);
        System.out.println(isMatch("mississippi", "mis*is*p*.") == false);
    }
}
