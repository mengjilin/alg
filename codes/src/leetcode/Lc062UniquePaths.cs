using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: dp, permutation
 */
namespace leetcode
{
    public class Lc062UniquePaths
    {
        public int UniquePathsWithObstacles(int[,] obstacleGrid)
        {
            int m = obstacleGrid.GetLength(0);
            int n = obstacleGrid.GetLength(1);
            int[,] dp = new int[m + 1, n + 1];
            dp[0, 1] = 1;

            for (int i = 1; i <= m; i++)
            {
                for (int j = 1; j <= n; j++)
                    if (obstacleGrid[i - 1, j - 1] == 0)
                        dp[i, j] = dp[i - 1, j] + dp[i, j - 1];
            }

            return dp[m, n];
        }

        // reduce Space(n)
        public int UniquePathsWithObstaclesDp(int[,] obstacleGrid)
        {
            int m = obstacleGrid.GetLength(0);
            int n = obstacleGrid.GetLength(1);
            var dp = new int[m];
            for (int j = 0; j < n && obstacleGrid[0, j] == 0; j++) dp[j] = 1;

            for (int i = 1; i < m; i++)
            {
                for (int j = 0; j < n; j++)
                    dp[j] = obstacleGrid[i, j] == 1 ? 0 :
                        (j == 0 ? dp[j] : dp[j] + dp[j - 1]);
            }

            return dp[m - 1];
        }

        public void Test()
        {
            var grid = new int[,]
            {
                {0,0,0 },
                {0,1,0 },
                {0,0,0 },
            };
            Console.WriteLine(UniquePathsWithObstaclesDp(grid) == 2);
            Console.WriteLine(UniquePathsWithObstaclesDp(new int[,] { { 1 } }) == 0);
        }
    }
}

