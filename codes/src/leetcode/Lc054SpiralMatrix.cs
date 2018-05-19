using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: backtracking
 */
namespace leetcode
{
    public class Lc054SpiralMatrix
    {
        public IList<int> SpiralOrder(int[,] matrix)
        {
            var ret = new List<int>();
            int m = matrix.GetLength(0);
            int n = matrix.GetLength(1);
            for (int k = 0; k < (Math.Min(m, n) + 1) / 2; k++)
            {
                for (int i = k, j = k; j < n - k; j++) ret.Add(matrix[i, j]);
                for (int i = k + 1, j = n - k - 1; i < m - k; i++) ret.Add(matrix[i, j]);
                for (int i = m - k - 1, j = n - k - 2; i > k && j >= k; j--) ret.Add(matrix[i, j]);
                for (int i = m - k - 2, j = k; i >= k + 1 && j < n - k - 1; i--) ret.Add(matrix[i, j]);
            }
            return ret;
        }

        public void Test()
        {
            var res = SpiralOrder(new[,]{
                { 1, 2, 3},
                { 4, 5, 6},
                { 7, 8, 9 } });
            var exp = new int[] { 1, 2, 3, 6, 9, 8, 7, 4, 5 };
            Console.WriteLine(exp.SequenceEqual(res));

            res = SpiralOrder(new[,]{
                { 1, 2, 3, 4},
                { 5, 6, 7, 8},
                { 9, 10,11,12 } });
            exp = new int[] { 1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7 };
            Console.WriteLine(exp.SequenceEqual(res));
        }

    }
}

