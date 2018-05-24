using System;
using System.Collections.Generic;
using System.Linq;

namespace alg.src
{
    class Solution
    {
        static void Main(string[] args)
        {
            new leetcode.Lc085MaximalRectangle().Test();
            //Test();

            Console.WriteLine("Press any key to exit ...");
            Console.ReadKey();
        }

        static void Test()
        {
            var list = new List<int> { 1, 1, 3, 4, 6, 3, 7 };
            for (int i = 0; i < list.Count; i++)
            {
                if (i == 3 || list[i] == 3) list.RemoveAt(i);
            }
            Console.WriteLine(list);
        }
    }
}
