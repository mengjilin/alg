using System;
using System.Collections.Generic;
using System.Text;

/*
 * tags: dp, recursive
 * DP: Time(nm), Space(nm)
 * dp[i, j] = dp[i-1, j-1] if s[i]==p[j] (not '*') or p[j]=='?', 
 *            dp[i, j-1] || dp[i-1, j] || dp[i-1, j-1] if p[j]=='*'
 */
namespace leetcode
{
    public class Lc044WildcardMatching
    {
        public bool IsMatch(string s, string p)
        {
            bool[,] dp = new bool[s.Length + 1, p.Length + 1];
            dp[0, 0] = true;
            for (int j = 0; j < p.Length && p[j] == '*'; j++) dp[0, j + 1] = true;

            for (int i = 0; i < s.Length; i++)
            {
                for (int j = 0; j < p.Length; j++)
                {
                    if (p[j] == '*') dp[i + 1, j + 1] = dp[i + 1, j] || dp[i, j + 1] || dp[i, j];
                    else if (p[j] == '?' || s[i] == p[j]) dp[i + 1, j + 1] = dp[i, j];
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
