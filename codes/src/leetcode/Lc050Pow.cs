using System;
using System.Collections.Generic;
using System.Linq;
using alg;

/*
 * tags: permutation
 */
namespace leetcode
{
    public class Lc050Pow
    {
        public double MyPow(double x, int n)
        {
            double ret = 1;
            bool positive = x >= 0 || (n % 2 == 0);
            double ax = Math.Abs(x);
            long an = Math.Abs((long)n);

            var nums = new double[32];
            var exps = new long[32];
            nums[0] = ax;
            exps[0] = 1;
            for (int i = 1; i < nums.Length; i++)
            {
                nums[i] = nums[i - 1] * nums[i - 1];
                exps[i] = exps[i - 1] + exps[i - 1];
            }

            for (int i = nums.Length - 1; i >= 0; i--)
            {
                while (an >= exps[i])
                {
                    ret *= nums[i];
                    an -= exps[i];
                }
            }

            return positive ? n >= 0 ? ret : 1/ret : n >= 0 ? -ret : -1/ret;
        }

        public void Test()
        {
            Console.WriteLine(MyPow(2.0, 10) == 1024.0);
            Console.WriteLine(MyPow(2.1, 3) == 9.261);
            Console.WriteLine(MyPow(2.0, -2) == 0.25);
        }

    }
}


