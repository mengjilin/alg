using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: dp
 * Time(mn), Space(mn)
 * dp[i,j] = dp[i-1,j] if s1[i]==s3[k] or
 *           dp[i,j-1] if s2[j]==s3[k]
 * k=i+j, space can be compacted to O(n)
 */
namespace leetcode
{
    public class Lc097InterleavingString
    {
        public bool IsInterleave(string s1, string s2, string s3)
        {
            if (s1.Length + s2.Length != s3.Length) return false;
            var dp = new bool[s1.Length + 1, s2.Length + 1];
            for (int i = 0; i <= s1.Length; i++)
            {
                for (int j = 0; j <= s2.Length; j++)
                {
                    dp[i, j] = (i == 0 && j == 0)
                        || (i > 0 && s1[i - 1] == s3[i + j - 1] && dp[i - 1, j])
                        || (j > 0 && s2[j - 1] == s3[i + j - 1] && dp[i, j - 1]);
                }
            }

            return dp[s1.Length, s2.Length];
        }

        public bool IsInterleaveDpCompact(string s1, string s2, string s3)
        {
            if (s1.Length + s2.Length != s3.Length) return false;
            var dp = new bool[s2.Length + 1];
            for (int i = 0; i <= s1.Length; i++)
            {
                for (int j = 0; j <= s2.Length; j++)
                {
                    dp[j] = (i == 0 && j == 0)
                        || (i > 0 && s1[i - 1] == s3[i + j - 1] && dp[j])
                        || (j > 0 && s2[j - 1] == s3[i + j - 1] && dp[j - 1]);
                }
            }

            return dp[s2.Length];
        }

        public void Test()
        {
            Console.WriteLine(IsInterleaveDpCompact("aabcc", "dbbca", "aadbbcbcac") == true);
            Console.WriteLine(IsInterleaveDpCompact("aabcc", "dbbca", "aadbbbaccc") == false);
        }
    }
}

