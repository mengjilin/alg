using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: dp
 * Time(m^2*n^2), Space(m^2*n^2)
 * dp[i,j,s,t] = nums[s,t] && dp[i,j,s-1,t] && dp[i,j,s,t-1]
 */
namespace leetcode
{
    public class Lc085MaximalRectangle
    {
        public int MaximalRectangle(char[,] matrix)
        {
            //return MaximalRectangleHistogram(matrix);
            return MaximalRectangleDp(matrix);
        }

        public int MaximalRectangleHistogram(char[,] matrix)
        {
            int ret = 0;
            int m = matrix.GetLength(0);
            int n = matrix.GetLength(1);

            for (int s = 0; s < m; s++)
            {
                var hist = new int[n];
                for (int j = 0; j < n; j++)
                {
                    for (int i = s; i < m && matrix[i, j] == '1'; i++) hist[j]++;
                }
                ret = Math.Max(ret, LargestRectangleinHistogram(hist));
            }

            return ret;
        }

        int LargestRectangleinHistogram(int[] heights)
        {
            var ret = 0;
            var stack = new Stack<int>(); // index of nums
            for (int i = 0; i <= heights.Length; i++)
            {
                while (stack.Count > 0 && (i == heights.Length || heights[stack.Peek()] >= heights[i]))
                {
                    var j = stack.Pop();
                    var jPrev = stack.Count == 0 ? -1 : stack.Peek();
                    ret = Math.Max(ret, heights[j] * (i - jPrev - 1));
                }

                stack.Push(i);
            }

            return ret;
        }

        public int MaximalRectangleDp(char[,] matrix)
        {
            int ret = 0;
            int m = matrix.GetLength(0);
            int n = matrix.GetLength(1);

            for (int i = 0; i < m; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (matrix[i, j] == '0') continue;
                    int tlen = n;
                    for (int s = i; s < m; s++)
                    {
                        int t = j;
                        while (t < tlen && matrix[s, t] == '1') t++;
                        tlen = t;
                        ret = Math.Max(ret, (s - i + 1) * (t - j));
                    }
                }

            }

            return ret;
        }

        public void Test()
        {
            var matrix = new char[,] {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}};
            Console.WriteLine(MaximalRectangle(matrix) == 6);

            matrix = new char[,] {
                {'0','1'},
                {'1','0'}};
            Console.WriteLine(MaximalRectangle(matrix) == 1);
        }
    }
}

