using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: dp
 * dp[i] = U{dp[j-1] + s[j..i], if s[j..i] is palindrome, j=[0..i]}
 */
namespace leetcode
{
    public class Lc132PalindromePartitioningII
    {
        public int MinCut(string s)
        {
            if (string.IsNullOrEmpty(s)) return 0;
            int n = s.Length;
            var dp = new int[n + 1];
            for (int i = 0; i <= n; i++) dp[i] = i - 1;

            for (int i = 0; i < n; i++)
            {
                // palindrome centered at s[i]
                for (int j = 0; j <= i && i + j < n && s[i - j] == s[i + j]; j++)
                    dp[i + j + 1] = Math.Min(dp[i + j + 1], 1 + dp[i - j]);

                // palindrome centered at middle between s[i] and s[i+1]
                for (int j = 0; j <= i && i + j + 1 < n && s[i - j] == s[i + j + 1]; j++)
                    dp[i + j + 2] = Math.Min(dp[i + j + 2], 1 + dp[i - j]);
            }

            return dp[n];
        }

        public int MinCut2(string s)
        {
            if (string.IsNullOrEmpty(s)) return 0;
            var dp = new int[s.Length];
            Array.Fill(dp, s.Length - 1);
            var isPalindrome = new bool[s.Length, s.Length];

            for (int i = 0; i < s.Length; i++)
            {
                for (int j = 0; j <= i; j++)
                {
                    if (s[i] == s[j] && (i - j <= 1 || isPalindrome[j + 1, i - 1]))
                    {
                        isPalindrome[j, i] = true;
                        dp[i] = j == 0 ? 0 : Math.Min(dp[i], 1 + dp[j - 1]);
                    }
                }
            }

            return dp[dp.Length - 1];
        }

        public void Test()
        {
            Console.WriteLine(MinCut("aab") == 1);
            Console.WriteLine(MinCut2("aab") == 1);
            Console.WriteLine(MinCut("cdd") == 1);
            Console.WriteLine(MinCut2("cdd") == 1);
        }
    }
}
