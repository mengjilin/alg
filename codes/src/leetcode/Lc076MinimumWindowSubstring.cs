using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: sliding windows
 */
namespace leetcode
{
    public class Lc076MinimumWindowSubstring
    {
        public string MinWindow(string s, string t)
        {
            var dict = new Dictionary<char, int[]>(); // value: count in t, and current count in s
            foreach (var c in t)
            {
                if (!dict.ContainsKey(c)) dict.Add(c, new int[] { 1, 0 });
                else dict[c][0]++;
            }

            int cnt = t.Length;
            int minIdx = 0;
            int minLen = int.MaxValue;
            for (int notFoundCnt = t.Length, i = 0; i < s.Length; i++)
            {

            }

            return "";
        }

        public void Test()
        {
            Console.WriteLine(MinWindow("ADOBECODEBANC", "ABC") == "BANC");
        }
    }
}

