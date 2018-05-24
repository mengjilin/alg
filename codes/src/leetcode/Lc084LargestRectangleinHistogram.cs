using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: stack
 * Time(n), Space(n)
 * the max length of the top bar is from the index of 2nd item in the stack
 */
namespace leetcode
{
    public class Lc084LargestRectangleinHistogram
    {
        public int LargestRectangleArea(int[] heights)
        {
            int ret = 0;
            var stack = new Stack<int>();
            for (int i = 0; i <= heights.Length; i++)
            {
                // an extra final loop (i == heights.Length) to avoid pop the stack after loop
                while (stack.Count > 0 && (i == heights.Length || heights[stack.Peek()] >= heights[i]))
                {
                    int j = stack.Pop();
                    int jPrev = stack.Count == 0 ? -1 : stack.Peek();
                    ret = Math.Max(ret, heights[j] * (i - jPrev - 1));
                }

                stack.Push(i);
            }

            return ret;
        }

        public void Test()
        {
            var nums = new int[] { 2, 1, 5, 6, 2, 3 };
            Console.WriteLine(LargestRectangleArea(nums) == 10);

            nums = new int[] { 2, 1, 2 };
            Console.WriteLine(LargestRectangleArea(nums) == 3);
        }
    }
}

