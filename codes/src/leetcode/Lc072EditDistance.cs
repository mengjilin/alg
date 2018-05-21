using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: dp
 * Time(nm), Space(nm)
 * dp[i,j] = dp[i-1,j-1] if a[i]==b[j]
 *           Min(dp[i,j-1],   // insert b[j]
 *               dp[i-1,j],   // delete a[i]
 *               dp[i-1,j-1], // replace a[i] with b[j]
 *               ) + 1;
 */
namespace leetcode
{
    public class Lc072EditDistance
    {
        public int MinDistance(string word1, string word2)
        {
            var dp = new int[word1.Length + 1, word2.Length + 1];
            for (int i = 1; i <= word1.Length; i++) dp[i, 0] = i;
            for (int j = 1; j <= word2.Length; j++) dp[0, j] = j;

            for (int i = 1; i <= word1.Length; i++)
            {
                for (int j = 1; j <= word2.Length; j++)
                {
                    if (word1[i - 1] == word2[j - 1]) dp[i, j] = dp[i - 1, j - 1];
                    else dp[i, j] = Math.Min(dp[i, j - 1], Math.Min(dp[i - 1, j], dp[i - 1, j - 1])) + 1;
                }
            }
            return dp[word1.Length, word2.Length];
        }

        public int MinDistanceDpReduceSpace(string word1, string word2)
        {
            var dp = new int[word2.Length + 1];
            for (int j = 1; j <= word2.Length; j++) dp[j] = j;

            for (int i = 1; i <= word1.Length; i++)
            {
                int leftUp = dp[0];
                dp[0] = i;
                for (int j = 1; j <= word2.Length; j++)
                {
                    int luNext = dp[j];
                    if (word1[i - 1] == word2[j - 1]) dp[j] = leftUp;
                    else dp[j] = Math.Min(dp[j - 1], Math.Min(dp[j], leftUp)) + 1;
                    leftUp = luNext;
                }
            }
            return dp[word2.Length];
        }

        public void Test()
        {
            Console.WriteLine(MinDistanceDpReduceSpace("horse", "ros") == 3);
            Console.WriteLine(MinDistanceDpReduceSpace("intention", "execution") == 5);
        }
    }
}


