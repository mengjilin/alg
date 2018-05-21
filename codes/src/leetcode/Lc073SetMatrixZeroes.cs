using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: array
 */
namespace leetcode
{
    public class Lc073SetMatrixZeroes
    {
        // Space(1)
        public void SetZeroes(int[,] matrix)
        {
            bool firstRowZero = false, firstColZero = false;
            for (int i = 0; i < matrix.GetLength(0); i++)
                firstColZero |= matrix[i, 0] == 0;
            for (int j = 0; j < matrix.GetLength(1); j++)
                firstRowZero |= matrix[0, j] == 0;

            for (int i = 1; i < matrix.GetLength(0); i++)
            {
                for (int j = 1; j < matrix.GetLength(1); j++)
                    if (matrix[i, j] == 0) matrix[i, 0] = matrix[0, j] = 0;
            }

            for (int i = 1; i < matrix.GetLength(0); i++)
            {
                for (int j = 1; j < matrix.GetLength(1); j++)
                    if (matrix[i, 0] == 0 || matrix[0, j] == 0) matrix[i, j] = 0;
            }

            if (firstRowZero) for (int j = 0; j < matrix.GetLength(1); j++) matrix[0, j] = 0;
            if (firstColZero) for (int i = 0; i < matrix.GetLength(0); i++) matrix[i, 0] = 0;
        }

        public void Test()
        {
            var input = new int[,]
            {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
            };
            var exp = new int[,]
            {
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
            };
            SetZeroes(input);
            Console.WriteLine(exp.Compare(input) == 0);

            input = new int[,]
            {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
            };
            exp = new int[,]
            {
                {0, 0, 0, 0},
                {0, 4, 5, 0},
                {0, 3, 1, 0}
            };
            SetZeroes(input);
            Console.WriteLine(exp.Compare(input) == 0);
        }
    }
}

