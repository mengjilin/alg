using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: permutation
 */
namespace leetcode
{
    public class Lc060PermutationSequence
    {
        public string GetPermutation(int n, int k)
        {
            var ret = new char[n];
            int fact = 1;
            for (int i = 1; i <= n; i++)
            {
                ret[i - 1] = (char)('0' + i);
                fact *= i;
            }

            k--; // start from 0
            for (int i = 0; i < n; i++)
            {
                fact /= n - i; // factorial of all numbers after i
                int j = i + k / fact;
                // ret[i..n-1] is in order, swap[i, j] then reorder
                char tmp = ret[j];
                for (; j > i; j--) ret[j] = ret[j - 1];
                ret[i] = tmp;
                k %= fact;
            }

            return new string(ret);
        }

        public void Test()
        {
            Console.WriteLine(GetPermutation(3, 3) == "213");
            Console.WriteLine(GetPermutation(4, 9) == "2314");
        }
    }
}
