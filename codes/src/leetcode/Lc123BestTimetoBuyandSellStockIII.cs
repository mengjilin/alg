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
 * dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j-1]), j=[0..i-1]
 */
namespace leetcode
{
    public class Lc123BestTimetoBuyandSellStockIII
    {
        // Time(kn^2), Space(kn)
        public int MaxProfitDp(int[] prices)
        {
            if (prices.Length == 0) return 0;

            var dp = new int[3, prices.Length];
            for (int k = 1; k <= 2; k++)
            {
                for (int i = 1; i < prices.Length; i++)
                {
                    int min = prices[0];
                    for (int j = 1; j <= i; j++)
                        min = Math.Min(min, prices[j] - dp[k - 1, j - 1]);
                    dp[k, i] = Math.Max(dp[k, i - 1], prices[i] - min);
                }
            }

            return dp[2, prices.Length - 1];
        }

        // Time(kn), Space(kn), eliminate the repeated calculation of min
        public int MaxProfitDpCompact1(int[] prices)
        {
            if (prices.Length == 0) return 0;

            var dp = new int[3, prices.Length];
            for (int k = 1; k <= 2; k++)
            {
                int min = prices[0];
                for (int i = 1; i < prices.Length; i++)
                {
                    min = Math.Min(min, prices[i] - dp[k - 1, i - 1]);
                    dp[k, i] = Math.Max(dp[k, i - 1], prices[i] - min);
                }
            }

            return dp[2, prices.Length - 1];
        }

        // Time(kn), Space(kn), swap the two 'for' loops
        public int MaxProfitDpCompact1T(int[] prices)
        {
            if (prices.Length == 0) return 0;

            var dp = new int[3, prices.Length];
            var min = new int[3];
            Array.Fill(min, prices[0]);
            for (int i = 1; i < prices.Length; i++)
            {
                for (int k = 1; k <= 2; k++)
                {
                    min[k] = Math.Min(min[k], prices[i] - dp[k - 1, i - 1]);
                    dp[k, i] = Math.Max(dp[k, i - 1], prices[i] - min[k]);
                }
            }

            return dp[2, prices.Length - 1];
        }

        // Time(kn), Space(k)
        public int MaxProfitDpCompact2(int[] prices)
        {
            if (prices.Length == 0) return 0;

            var dp = new int[3];
            var min = new int[3];
            Array.Fill(min, prices[0]);
            for (int i = 1; i < prices.Length; i++)
            {
                for (int k = 1; k <= 2; k++)
                {
                    min[k] = Math.Min(min[k], prices[i] - dp[k - 1]);
                    dp[k] = Math.Max(dp[k], prices[i] - min[k]);
                }
            }

            return dp[2];
        }

        // Time(kn), Space(k)
        public int MaxProfitDpCompactFinal(int[] prices)
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
            Console.WriteLine(MaxProfitDpCompact1(prices) == 6);
            Console.WriteLine(MaxProfitDpCompact1T(prices) == 6);
            Console.WriteLine(MaxProfitDpCompact2(prices) == 6);
            Console.WriteLine(MaxProfitDpCompactFinal(prices) == 6);
            Console.WriteLine(MaxProfitTwoPass(prices) == 6);

            prices = new int[] { 1, 2, 3, 4, 5 };
            Console.WriteLine(MaxProfitDp(prices) == 4);
            Console.WriteLine(MaxProfitDpCompact1(prices) == 4);
            Console.WriteLine(MaxProfitDpCompact1T(prices) == 4);
            Console.WriteLine(MaxProfitDpCompact2(prices) == 4);
            Console.WriteLine(MaxProfitDpCompactFinal(prices) == 4);
            Console.WriteLine(MaxProfitTwoPass(prices) == 4);

            prices = new int[] { 7, 6, 4, 3, 1 };
            Console.WriteLine(MaxProfitDp(prices) == 0);
            Console.WriteLine(MaxProfitDpCompact1(prices) == 0);
            Console.WriteLine(MaxProfitDpCompact1T(prices) == 0);
            Console.WriteLine(MaxProfitDpCompact2(prices) == 0);
            Console.WriteLine(MaxProfitDpCompactFinal(prices) == 0);
            Console.WriteLine(MaxProfitTwoPass(prices) == 0);

            prices = new int[] { 4, 1, 2 };
            Console.WriteLine(MaxProfitDp(prices) == 1);
            Console.WriteLine(MaxProfitDpCompact1(prices) == 1);
            Console.WriteLine(MaxProfitDpCompact1T(prices) == 1);
            Console.WriteLine(MaxProfitDpCompact2(prices) == 1);
            Console.WriteLine(MaxProfitDpCompactFinal(prices) == 1);
            Console.WriteLine(MaxProfitTwoPass(prices) == 1);

            prices = new int[] { 2, 1, 2, 0, 1 };
            Console.WriteLine(MaxProfitDp(prices) == 2);
            Console.WriteLine(MaxProfitDpCompact1(prices) == 2);
            Console.WriteLine(MaxProfitDpCompact1T(prices) == 2);
            Console.WriteLine(MaxProfitDpCompact2(prices) == 2);
            Console.WriteLine(MaxProfitDpCompactFinal(prices) == 2);
            Console.WriteLine(MaxProfitTwoPass(prices) == 2);
        }
    }
}

