using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: geometry(slope, orientation, on line, on segment)
 */
namespace leetcode
{
    public class Lc149MaxPointsonaLine
    {
        public int MaxPoints(Point[] points)
        {
            int n = points.Length;
            Array.Sort(points, (a, b) => a.x != b.x ? a.x - b.x : a.y - b.y);
            int max = 0;
            for (int i = 0; i < n; i++)
            {
                int samePoints = 1, maxSameSlope = 0;
                var slopes = new Dictionary<long, int>();
                for (int j = i + 1; j < n; j++)
                {
                    int deltaX = points[j].x - points[i].x;
                    int deltaY = points[j].y - points[i].y;

                    if (deltaX == 0 && deltaY == 0)
                    {
                        samePoints++;
                        continue;
                    }

                    int gcd = GreatestCommonDenominator(deltaX, deltaY);
                    deltaX /= gcd;
                    deltaY /= gcd;

                    var key = (((long)deltaX) << 32) + deltaY;
                    if (!slopes.ContainsKey(key))
                        slopes.Add(key, 1);
                    else
                        slopes[key]++;

                    maxSameSlope = Math.Max(maxSameSlope, slopes[key]);
                }
                max = Math.Max(max, samePoints + maxSameSlope);
            }

            return max;
        }

        int GreatestCommonDenominator(int a, int b)
        {
            while (b != 0)
            {
                int t = a % b;
                a = b;
                b = t;
            }
            return a;
        }

        public int MaxPoints2(Point[] points)
        {
            int n = points.Length;
            Array.Sort(points, (a, b) => a.x != b.x ? a.x - b.x : a.y - b.y);
            int max = n > 0 && IsSamePoint(points[0], points[n - 1]) ? n : 0;
            for (int i = 0; i < n; i++)
            {
                if (i < n - 1 && IsSamePoint(points[i], points[i + 1])) continue;
                for (int j = i + 1; j < n; j++)
                {
                    int cur = 0;
                    for (int k = 0; k < n; k++)
                    {
                        if (IsOnLine(points[i], points[j], points[k])) cur++;
                    }
                    max = Math.Max(max, cur);

                }
            }

            return max;
        }

        bool IsSamePoint(Point a, Point b)
        {
            return a.x == b.x && a.y == b.y;
        }

        // return true if c is on line (a, b)
        bool IsOnLine(Point a, Point b, Point c)
        {
            return ((long)b.y - a.y) * (c.x - b.x) == ((long)b.x - a.x) * (c.y - b.y);
        }


        public void Test()
        {
            var points = new Point[] {
                new Point(1,1),
                new Point(2,2),
                new Point(3,3),};
            Console.WriteLine(MaxPoints(points) == 3);
            Console.WriteLine(MaxPoints2(points) == 3);

            points = new Point[] {
                new Point(1,1),
                new Point(3,2),
                new Point(5,3),
                new Point(4,1),
                new Point(2,3),
                new Point(1,4),};
            Console.WriteLine(MaxPoints(points) == 4);
            Console.WriteLine(MaxPoints2(points) == 4);

            points = new Point[] {
                new Point(0,-12),
                new Point(5,2),
                new Point(2,5),
                new Point(0,-5),
                new Point(1,5),
                new Point(2,-2),
                new Point(5,-4),
                new Point(3,4),
                new Point(-2,4),
                new Point(-1,4),
                new Point(0,-5),
                new Point(0,-8),
                new Point(-2,-1),
                new Point(0,-11),
                new Point(0,-9),};
            Console.WriteLine(MaxPoints(points) == 6);
            Console.WriteLine(MaxPoints2(points) == 6);
        }
    }
}

