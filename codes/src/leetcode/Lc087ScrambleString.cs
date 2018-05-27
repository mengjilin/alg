using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: dp
 * Time(n^4), Space(n^4)
 * dp[i,j,n] = any(dp[i,j,p] && dp[i+p,j+p,n-p] || dp[i,j+n-p,p] && dp[i+p,j,n-p]), p=[1..n-1]
 * 
 * in any position p(=[1..n-1]) partitioning the string into two parts, s1[0..p-1], and s1[p..n-1],
 * then s1 is a scramble s2 if and only if s1[0..p-1] is scramble s2[0..p-1] and s1[p..n-1] is scramble s2[p..n-1],
 * or s1[0..p-1] is scramble s2[n-p,n-1] and s1[p..n-1] is scramble s2[0..n-p-1], for any p.
 */
namespace leetcode
{
    public class Lc087ScrambleString
    {
        public bool IsScramble(string s1, string s2)
        {
            if (s1.Length != s2.Length) return false;

            int n = s1.Length;
            var dp = new bool[n + 1, n + 1, n + 1];
            for (int i = 0; i <= n; i++) for (int j = 0; j <= n; j++) dp[i, j, 0] = true;
            for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) dp[i, j, 1] = s1[i] == s2[j];

            for (int len = 2; len <= n; len++)
            {
                for (int i = 0; i <= n - len; i++)
                {
                    for (int j = 0; j <= n - len; j++)
                    {
                        for (int p = 1; p < len && !dp[i, j, len]; p++)
                        {
                            dp[i, j, len] = dp[i, j, p] && dp[i + p, j + p, len - p]
                                || dp[i, j + len - p, p] && dp[i + p, j, len - p];
                        }
                    }
                }
            }

            return dp[0, 0, n];
        }

        public void Test()
        {
            Console.WriteLine(IsScramble("great", "rgeat") == true);
            Console.WriteLine(IsScramble("great", "rgtae") == true);
            Console.WriteLine(IsScramble("great", "teagr") == true);
            Console.WriteLine(IsScramble("great", "trgae") == true);
            Console.WriteLine(IsScramble("abcde", "caebd") == false);
            Console.WriteLine(IsScramble("abcd", "cdab") == true);
        }
    }
}

