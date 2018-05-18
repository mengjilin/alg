using System;
using System.Collections.Generic;
using System.Text;

/*
 * tags: greedy, stack, dp
 */
namespace leetcode
{
    public class Lc045JumpGameII
    {
        public int Jump(int[] nums)
        {
            return JumpGreedy(nums);
        }

        // udpate the farest as far as it can
        public int JumpGreedy(int[] nums)
        {
            int step = 0, far = 0, farest = 0;

            for (int i = 0; i < nums.Length - 1; i++)
            {
                far = Math.Max(far, i + nums[i]);
                if (i == farest)
                {
                    farest = far;
                    step++;
                }
            }

            return step;
        }

        /*
         * dp[i] = min(dp[j] + 1, if j+a[j]>=i, j=0..i-1)
         */
        public int JumpDp(int[] nums)
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

        /*
         * from the end to the beginning, pop the stack until 
         * the current one is not able to jump to the 2nd one in the stack, then push the current one.
         */
        public int JumpStack(int[] nums)
        {
            if (nums.Length < 2) return 0;
            var stack = new int[nums.Length][]; // array: index, jump
            int slen = 0;

            for (int i = nums.Length - 1; i >= 0; i--)
            {
                while (slen >= 2 && i + nums[i] >= stack[slen - 2][0]) slen--;
                stack[slen++] = new int[] { i, nums[i] };
            }

            return slen - 1;
        }

        public void Test()
        {
            Console.WriteLine(Jump(new int[] { 2, 3, 1, 1, 4 }) == 2);
            Console.WriteLine(Jump(new int[] { 1, 2, 1, 1, 1 }) == 3);
        }
    }
}
