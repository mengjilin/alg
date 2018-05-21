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
            var heap = new Heap(t);

            int minIdx = -1;
            int minLen = int.MaxValue;
            for (int i = 0; i < s.Length; i++)
            {
                if (heap.Contains(s[i]))
                {
                    heap.Update(s[i], i);
                    if (heap.IsFoundAll() && minLen > i - heap.Lowest() + 1)
                    {
                        minIdx = heap.Lowest();
                        minLen = i - heap.Lowest() + 1;
                    }
                }
            }

            return minIdx == -1 ? "" : s.Substring(minIdx, minLen);
        }

        class Heap
        {
            public Heap(string t)
            {
                _t = t;
                _indexInS = new int[t.Length];
                Array.Fill(_indexInS, -1);
            }

            public bool Contains(char c)
            {
                return _t.IndexOf(c) >= 0;
            }

            public bool IsFoundAll()
            {
                return _indexInS.All(i => i >= 0);
            }

            public void Update(char c, int index)
            {
                int minIdx = -1;
                for (int i = 0; i < _t.Length; i++)
                {
                    if (_t[i] == c && (minIdx == -1 || _indexInS[minIdx] > _indexInS[i]))
                        minIdx = i;
                }
                if (minIdx >= 0) _indexInS[minIdx] = index;
            }

            public int Lowest()
            {
                return _indexInS.Min();
            }

            private string _t;
            private int[] _indexInS;
        }

        public void Test()
        {
            Console.WriteLine(MinWindow("ADOBECODEBANC", "ABC") == "BANC");
        }
    }
}

