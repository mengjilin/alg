using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: two pointers, dp
 * at most 1 transaction: any lower price could be buying price
 * dp[n] = max(dp[n-1], prices[n] - lowest)
 */
namespace leetcode
{
    public class Lc121BestTimetoBuyandSellStock
    {
        public int MaxProfit(int[] prices)
        {
            var ret = 0;
            for (int lowest = int.MaxValue, i = 0; i < prices.Length; i++)
            {
                lowest = Math.Min(lowest, prices[i]);
                ret = Math.Max(ret, prices[i] - lowest);
            }

            return ret;
        }


        public void Test()
        {
            var prices = new int[] { 7, 1, 5, 3, 6, 4 };
            Console.WriteLine(MaxProfit(prices) == 5);

            prices = new int[] { 7, 6, 4, 3, 1 };
            Console.WriteLine(MaxProfit(prices) == 0);
        }
    }
}

