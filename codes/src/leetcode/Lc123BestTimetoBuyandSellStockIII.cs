using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: dp
 * at most k transactions: 
 * dp: insert/replace the last profit (prices[n] - lowest) into profits[0..k-1], where lowest is the lowest price since last buy
 * dp = sum(profits[i]), i=[0..k-1]
 */
namespace leetcode
{
    public class Lc123BestTimetoBuyandSellStockIII
    {
        public int MaxProfit(int[] prices)
        {
            var buys = new int[3];
            var sells = new int[3];
            int trans = 0;
            for (int i = 1; i < prices.Length; i++)
            {
                if (trans > 0 && buys[trans] == 0)
                {
                    if (prices[i] >= prices[sells[trans - 1]]) sells[trans - 1] = i;
                    else buys[trans] = i;
                }
                else
                {
                    if (prices[i] > prices[buys[trans]])
                    {
                        sells[trans] = i;
                        if (trans == buys.Length - 1) Merge(prices, buys, sells);
                        if (trans < buys.Length - 1) trans++;
                    }
                    else buys[trans] = i;
                }
            }

            return (trans <= 0 ? 0 : prices[sells[0]] - prices[buys[0]])
                + (trans <= 1 ? 0 : prices[sells[1]] - prices[buys[1]]);
        }

        void Merge(int[] prices, int[] buys, int[] sells)
        {
            int minSell = 0;
            for (int i = 1; i < buys.Length - 1; i++)
            {
                if (prices[sells[minSell]] - prices[buys[minSell + 1]] > prices[sells[i]] - prices[buys[i + 1]])
                    minSell = i;
            }

            int minBuy = 0;
            for (int i = 1; i < buys.Length; i++)
            {
                if (prices[sells[minBuy]] - prices[buys[minBuy]] > prices[sells[i]] - prices[buys[i]])
                    minBuy = i;
            }

            if (prices[sells[minSell]] - prices[buys[minSell + 1]] < prices[sells[minBuy]] - prices[buys[minBuy]])
            {
                sells[minSell] = sells[minSell + 1];
                minBuy = minSell + 1;
            }
            for (int i = minBuy; i < buys.Length - 1; i++)
            {
                buys[i] = buys[i + 1];
                sells[i] = sells[i + 1];
            }
            buys[buys.Length - 1] = 0;
        }


        public void Test()
        {
            var prices = new int[] { 3, 3, 5, 0, 0, 3, 1, 4 };
            Console.WriteLine(MaxProfit(prices) == 6);

            prices = new int[] { 1, 2, 3, 4, 5 };
            Console.WriteLine(MaxProfit(prices) == 4);

            prices = new int[] { 7, 6, 4, 3, 1 };
            Console.WriteLine(MaxProfit(prices) == 0);

            prices = new int[] { 4, 1, 2 };
            Console.WriteLine(MaxProfit(prices) == 1);

            prices = new int[] { 2, 1, 2, 0, 1 };
            Console.WriteLine(MaxProfit(prices) == 2);
        }
    }
}

