using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: dp
 * Time(n), Space(kn), can compact to Space(k)
 * at most k transactions: 
 * dp[k, n] = max(dp[k, n-1], prices[n] - prices[i] + dp[k-1, i-1]), i=[0..n-1]
 *          = max(dp[k, n-1], prices[n] - min(prices[i] - dp[k-1, i-1]))
 */
namespace leetcode
{
    public class Lc123BestTimetoBuyandSellStockIII
    {
        public int MaxProfitDp(int[] prices)
        {
            if (prices.Length == 0) return 0;

            var dp = new int[3, prices.Length];
            for (int k = 1; k <= 2; k++)
            {
                int min = prices[0];
                for (int i = 1; i < prices.Length; i++)
                {
                    min = Math.Min(min, prices[i - 1] - (i == 1 ? 0 : dp[k - 1, i - 2]));
                    dp[k, i] = Math.Max(dp[k, i - 1], prices[i] - min);
                }
            }

            return dp[2, prices.Length - 1];
        }

        public int MaxProfitDpCompact(int[] prices)
        {
            int buy1 = int.MaxValue, buy2 = int.MaxValue;
            int sell1 = 0, sell2 = 0;

            for (int i = 0; i < prices.Length; i++)
            {
                buy1 = Math.Min(buy1, prices[i]);
                sell1 = Math.Max(sell1, prices[i] - buy1);
                buy2 = Math.Min(buy2, prices[i] - sell1);
                sell2 = Math.Max(sell2, prices[i] - buy2);
            }

            return sell2;
        }

        public int MaxProfitTwoPass(int[] prices)
        {
            if (prices.Length == 0) return 0;
            var dp = new int[prices.Length];
            for (int highest = prices[prices.Length - 1], i = prices.Length - 2; i >= 0; i--)
            {
                dp[i] = Math.Max(dp[i + 1], highest - prices[i]);
                highest = Math.Max(highest, prices[i]);
            }

            int ret = dp[0];
            int firstProfit = 0;
            for (int lowest = prices[0], i = 1; i < prices.Length - 1; i++)
            {
                firstProfit = Math.Max(firstProfit, prices[i] - lowest);
                ret = Math.Max(ret, firstProfit + dp[i + 1]);
                lowest = Math.Min(lowest, prices[i]);
            }

            return ret;
        }


        public void Test()
        {
            var prices = new int[] { 3, 3, 5, 0, 0, 3, 1, 4 };
            Console.WriteLine(MaxProfitDp(prices) == 6);
            Console.WriteLine(MaxProfitTwoPass(prices) == 6);
            Console.WriteLine(MaxProfitDpCompact(prices) == 6);

            prices = new int[] { 1, 2, 3, 4, 5 };
            Console.WriteLine(MaxProfitDp(prices) == 4);
            Console.WriteLine(MaxProfitTwoPass(prices) == 4);
            Console.WriteLine(MaxProfitDpCompact(prices) == 4);

            prices = new int[] { 7, 6, 4, 3, 1 };
            Console.WriteLine(MaxProfitDp(prices) == 0);
            Console.WriteLine(MaxProfitTwoPass(prices) == 0);
            Console.WriteLine(MaxProfitDpCompact(prices) == 0);

            prices = new int[] { 4, 1, 2 };
            Console.WriteLine(MaxProfitDp(prices) == 1);
            Console.WriteLine(MaxProfitTwoPass(prices) == 1);
            Console.WriteLine(MaxProfitDpCompact(prices) == 1);

            prices = new int[] { 2, 1, 2, 0, 1 };
            Console.WriteLine(MaxProfitDp(prices) == 2);
            Console.WriteLine(MaxProfitTwoPass(prices) == 2);
            Console.WriteLine(MaxProfitDpCompact(prices) == 2);
        }
    }
}

