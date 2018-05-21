using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: bs
 */
namespace leetcode
{
    public class Lc074Searcha2DMatrix
    {
        public bool SearchMatrix(int[,] matrix, int target)
        {
            int m = matrix.GetLength(0);
            int n = matrix.GetLength(1);
            for (int lo = 0, hi = m * n - 1; lo <= hi;)
            {
                int mid = (lo + hi) / 2;
                if (matrix[mid / n, mid % n] == target) return true;
                if (matrix[mid / n, mid % n] < target) lo = mid + 1;
                else hi = mid - 1;
            }

            return false;
        }

        public void Test()
        {
            var input = new int[,]
            {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
            };
            Console.WriteLine(SearchMatrix(input, 3) == true);
            Console.WriteLine(SearchMatrix(input, 13) == false);
        }
    }
}

