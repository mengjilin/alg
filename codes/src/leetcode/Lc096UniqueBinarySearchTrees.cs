using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: dp
 * Time(n), Space(n)
 * dp[n] = sum(dp[i] * dp[n-i-1]), i=[0..n-1]
 * 
 * select nums[i] as root
 */
namespace leetcode
{
    public class Lc096UniqueBinarySearchTrees
    {
        public int NumTrees(int n)
        {
            var dp = new int[n + 1];
            dp[0] = dp[1] = 1;
            for (int len = 2; len <= n; len++)
            {
                for (int i = 0; i < len; i++)
                {
                    dp[len] += dp[i] * dp[len - i - 1];
                }
            }
            return dp[n];
        }

        public void Test()
        {
            Console.WriteLine(NumTrees(3) == 5);
        }
    }
}

