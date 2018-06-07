using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: dp, dfs, backtracking
 */
namespace leetcode
{
    public class Lc149MaxPointsonaLine
    {
        public int MaxPoints(Point[] points)
        {
            int n = points.Length;
            if (n < 2) return 0;
            var slopes = new int[n * n];
            for (int i = 0; i < slopes.Length; i++) slopes[i] = i;
            Array.Sort(slopes, (i, j) => Comp(points, i, j));

            int max = 1, cur = 1;
            for (int i = 1; i < n * (n - 1) / 2; i++)
            {
                if (Comp(points, slopes[i - 1], slopes[i]) == 0) cur++;
                else cur = 1;
                max = Math.Max(max, cur);
            }
            for (int i = 1; i <= n; i++)
                if (i * i - i == max) return i;
            return 0;
        }

        int Comp(Point[] points, int i, int j)
        {
            int n = points.Length;
            if (i / n <= i % n) return 1;
            if (j / n <= j % n) return -1;
            var a1 = points[i / n];
            var a2 = points[i % n];
            var b1 = points[j / n];
            var b2 = points[j % n];
            return (a2.y - a1.y) * (b2.x - b1.x) - (a2.x - a1.x) * (b2.y - b1.y);
        }

        public void Test()
        {
            var points = new Point[] {
                new Point(1,1),
                new Point(2,2),
                new Point(3,3),};
            Console.WriteLine(MaxPoints(points) == 3);

            points = new Point[] {
                new Point(1,1),
                new Point(3,2),
                new Point(5,3),
                new Point(4,1),
                new Point(2,3),
                new Point(1,4),};
            Console.WriteLine(MaxPoints(points) == 4);
        }
    }
}

