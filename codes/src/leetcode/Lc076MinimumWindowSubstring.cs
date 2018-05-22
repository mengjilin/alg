using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: two pointers, sliding windows, hash
 */
namespace leetcode
{
    public class Lc076MinimumWindowSubstring
    {
        public string MinWindow(string s, string t)
        {
            var map = new int[256];
            foreach (var c in t) map[c]++;

            int minIdx = 0;
            int minLen = int.MaxValue;
            for (int begin = 0, end = 0, cnt = 0; end < s.Length; end++)
            {
                if (map[s[end]]-- > 0) cnt++; // found a valid char
                for (; cnt == t.Length; begin++) // found a valid substring[i..j]
                {
                    if (minLen > end - begin + 1) minLen = end - (minIdx = begin) + 1;
                    if (map[s[begin]]++ == 0) cnt--;
                }
            }

            return minLen == int.MaxValue ? "" : s.Substring(minIdx, minLen);
        }

        public void Test()
        {
            Console.WriteLine(MinWindow("ADOBECODEBANC", "ABC") == "BANC");
            Console.WriteLine(MinWindow("ask_not_what_your_country_can_do_for_you_ask_what_you_can_do_for_your_country", "ask_country") == "sk_not_what_your_c");
        }
    }
}

