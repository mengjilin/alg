using System;
using System.Collections.Generic;
using System.Linq;

namespace alg
{
    class Solution
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Lc132PalindromePartitioningII:");
            new leetcode.Lc132PalindromePartitioningII().Test();
            //Test();

            Console.WriteLine("Press any key to exit ...");
            Console.ReadKey();
        }

        static void Test()
        {
            var li = new List<int>[3];
            var map = new Dictionary<int, int>[10];
        }
    }
}
