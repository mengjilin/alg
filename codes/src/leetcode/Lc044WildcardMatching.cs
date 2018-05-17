using System;
using System.Collections.Generic;
using System.Text;

/*
 * tags: dp, recursive
 * DP: Time(nm), Space(nm)
 * dp[i, j] = dp[i, j-1] || dp[i-1, j], if p[j]=='*'
 *            dp[i-1, j-1], if match 1, p[j]=='? || s[i]==p[j]
 *            false
 */
namespace leetcode
{
    public class Lc044WildcardMatching
    {
        public bool IsMatch(string s, string p)
        {
            bool[,] dp = new bool[s.Length + 1, p.Length + 1];
            dp[0, 0] = true;

            for (int i = 0; i <= s.Length; i++)
            {
                for (int j = 1; j <= p.Length; j++)
                {
                    dp[i, j] = p[j - 1] == '*' 
                        ? dp[i, j - 1] || (i > 0 && dp[i - 1, j])
                        : i > 0 && (p[j - 1] == '?' || s[i - 1] == p[j - 1]) && dp[i - 1, j - 1];
                }
            }

            return dp[s.Length, p.Length];
        }

        public void Test()
        {
            Console.WriteLine(IsMatch("aa", "a") == false);
            Console.WriteLine(IsMatch("aa", "*") == true);
            Console.WriteLine(IsMatch("cb", "?a") == false);
            Console.WriteLine(IsMatch("adceb", "*a*b") == true);
            Console.WriteLine(IsMatch("acdcb", "a*c?b") == false);
        }
    }
}
