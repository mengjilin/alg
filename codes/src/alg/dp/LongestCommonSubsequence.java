package alg.dp;

/*
 * Time(mn), Space(mn)
 * L(m, n) = max(L(m-1, n), L(m, n-1)) or L(m-1, n-1) + 1 if s1[m]==s2[n]
 * This can be optimized for character set, see diff.exe
 */
public class LongestCommonSubsequence {

	static int lcs(String s1, String s2) {
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				if (s1.charAt(i) == s2.charAt(j)) dp[i+1][j+1] = dp[i][j] + 1;
				else dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
			}
		}
		return dp[s1.length()][s2.length()];
	}

	static String lcsWithLogs(String s1, String s2) {
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				if (s1.charAt(i) == s2.charAt(j)) dp[i+1][j+1] = dp[i][j] + 1;
				else dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
			}
		}
		
		char[] cs = new char[dp[s1.length()][s2.length()]];
		for (int k = cs.length - 1, i = s1.length() - 1, j = s2.length() - 1; k >= 0;) {
			if (s1.charAt(i) == s2.charAt(j)) {
				cs[k] = s1.charAt(i);
				i--; j--; k--;
			} else if (dp[i+1][j] > dp[i][j+1]) {
				j--;
			} else {
				i--;
			}
		}		
		return new String(cs);
	}
	
	public static void main(String[] args) {
		System.out.println(4 == lcs("AGGTAB", "GXTXAYB"));
		System.out.println("GTAB".equals(lcsWithLogs("AGGTAB", "GXTXAYB")));
	}

}
