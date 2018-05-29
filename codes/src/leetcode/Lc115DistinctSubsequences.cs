using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: dp
 * Time(mn), Space(mn)
 * dp[m,n] = dp[m-1,n] if s[m] != t[n]
 *           dp[m-1,n] + dp[m-1,n-1] if s[m] == t[n]
 */
namespace leetcode
{
    public class Lc115DistinctSubsequences
    {
        public int NumDistinct(string s, string t)
        {
            var dp = new int[s.Length + 1, t.Length + 1];
            for (int i = 0; i <= s.Length; i++) dp[i, 0] = 1;
            for (int i = 0; i < s.Length; i++)
            {
                for (int j = 0; j < t.Length; j++)
                {
                    dp[i + 1, j + 1] = dp[i, j + 1] + (s[i] == t[j] ? dp[i, j] : 0);
                }
            }

            return dp[s.Length, t.Length];
        }

        public int NumDistinctCompact(string s, string t)
        {
            var dp = new int[t.Length];
            for (int i = 0; i < s.Length; i++)
            {
                int leftUp = 1;
                for (int j = 0; j < t.Length; j++)
                {
                    var tmp = dp[j];
                    dp[j] += (s[i] == t[j] ? leftUp : 0);
                    leftUp = tmp;
                }
            }

            return dp[t.Length - 1];
        }


        public void Test()
        {
            Console.WriteLine(NumDistinct("rabbbit", "rabbit") == 3);
            Console.WriteLine(NumDistinct("babgbag", "bag") == 5);
            Console.WriteLine(NumDistinctCompact("rabbbit", "rabbit") == 3);
            Console.WriteLine(NumDistinctCompact("babgbag", "bag") == 5);
        }
    }
}

