using System;
using System.Collections.Generic;
using System.Text;

/*
 * tags: dp, recursive
 * DP: Time(n^2), Space(n)
 * dp[i] = min(dp[j] + 1, if j+a[j]>=i, j=0..i-1)
 */
namespace leetcode
{
    public class Lc045JumpGameII
    {
        public int Jump(int[] nums)
        {
            int[] dp = new int[nums.Length];

            for (int i = 1; i < nums.Length; i++)
            {
                dp[i] = int.MaxValue;
                for (int j = 0; j < i; j++)
                    if (j + nums[j] >= i) dp[i] = Math.Min(dp[i], dp[j] + 1);
            }

            return dp[nums.Length - 1];
        }

        // udpate all accessible positions from current
        public int JumpTrick(int[] nums)
        {
            int[] dp = new int[nums.Length];
            Array.Fill(dp, int.MaxValue);
            dp[0] = 0;

            for (int i = 0; i < nums.Length; i++)
            {
                for (int j = i + 1; j < nums.Length && j <= i + nums[i]; j++)
                    dp[j] = Math.Min(dp[j], dp[i] + 1);
            }

            return dp[nums.Length - 1];
        }

        public void Test()
        {
            Console.WriteLine(JumpTrick(new int[] { 2, 3, 1, 1, 4 }) == 2);
        }
    }
}
