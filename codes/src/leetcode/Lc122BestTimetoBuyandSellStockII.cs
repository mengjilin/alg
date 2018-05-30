using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: two pointers, dp
 * can do as many transactions as you like: any profit within two days can be counted
 * dp[n] = dp[n-1] + max(0, prices[n] - prices[n-1])
 */
namespace leetcode
{
    public class Lc122BestTimetoBuyandSellStockII
    {
        public int MaxProfit(int[] prices)
        {
            var ret = 0;
            for (int i = 1; i < prices.Length; i++)
            {
                ret += Math.Max(0, prices[i] - prices[i - 1]);
            }

            return ret;
        }


        public void Test()
        {
            var prices = new int[] { 7, 1, 5, 3, 6, 4 };
            Console.WriteLine(MaxProfit(prices) == 7);

            prices = new int[] { 1, 2, 3, 4, 5 };
            Console.WriteLine(MaxProfit(prices) == 4);

            prices = new int[] { 7, 6, 4, 3, 1 };
            Console.WriteLine(MaxProfit(prices) == 0);
        }
    }
}

