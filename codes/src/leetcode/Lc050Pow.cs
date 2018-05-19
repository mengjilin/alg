using System;
using System.Collections.Generic;
using System.Linq;
using alg;

/*
 * tags: dc, bit
 */
namespace leetcode
{
    public class Lc050Pow
    {
        public double MyPow(double x, int n)
        {
            //return MyPowDc(x, n);
            return MyPowBit(x, n);
        }

        public double MyPowDc(double x, int n)
        {
            if (n == 0) return 1;

            var ret = n % 2 == 0 ? MyPowDc(x * x, Math.Abs(n / 2)) : x * MyPowDc(x * x, Math.Abs(n / 2));
            return n > 0 ? ret : 1 / ret;
        }

        public double MyPowBit(double x, int n)
        {
            double ret = 1;
            long an = Math.Abs((long)n);
            while (an > 0)
            {
                if (an % 2 == 1) ret *= x;
                an >>= 1;
                x *= x;
            }

            return n > 0 ? ret : 1 / ret;
        }

        public void Test()
        {
            Console.WriteLine(MyPow(2.0, 10) == 1024.0);
            Console.WriteLine(MyPow(-2.1, 3) == -9.261);
            Console.WriteLine(MyPow(2.0, -2) == 0.25);
            Console.WriteLine(MyPow(34.00515, -3) == 3e-05);
        }

    }
}


