using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: dp
 * Time(n^3), Space(n^3)
 */
namespace leetcode
{
    public class Lc087ScrambleString
    {
        public bool IsScramble(string s1, string s2)
        {
            if (s1.Length != s2.Length) return false;
            if (s1.Length == 0) return true;

            int n = s1.Length;
            var dp = new bool[n + 1, n + 1, n + 1];
            for (int i = 0; i <= n; i++) for (int j = 0; j <= n; j++) dp[i, j, 0] = true;

            for (int len = 1; len <= n; len++)
            {
                for (int i = 0; i <= n - len; i++)
                {
                    for (int j = 0; j <= n - len; j++)
                    {
                        for (int k = 1; k < len; k++)
                        {
                            dp[i, j, len] |= dp[i, j, k]
                        }
                        dp[i, j, len] =
                            (s1[i] == s2[j] && dp[i + 1, j + 1, len - 1]) ||
                            (s1[i] == s2[j + len - 1] && dp[i + 1, j, len - 1]) ||
                            (s1[i + len - 1] == s2[j] && dp[i, j + 1, len - 1] ||
                            (s1[i + len - 1] == s2[j + len - 1] && dp[i, j, len - 1]));
                    }
                }
            }

            bool ret = false;
            for (int i = 0; i < n; i++)
                ret |= dp[0, 0, i] && dp[i, i, n - i] || dp[0, n - i, i] && dp[i, 0, n - i];

            return ret;
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

