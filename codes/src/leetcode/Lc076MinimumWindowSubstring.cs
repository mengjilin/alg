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
                _hash = new Node[256];
                _heap = new List<Node>();
                foreach (var c in t)
                {
                    int i = c % 256;
                    if (_hash[i] == null)
                    {
                        var node = new Node(_heap.Count, new LinkedList<int>(new int[] { -1 }));
                        _hash[i] = node;
                        _heap.Add(node);
                    }
                    else
                    {
                        _heap[_hash[i].Index].Data.AddLast(-1);
                    }
                }
            }

            public bool Contains(char c)
            {
                return _hash[c % 256] != null;
            }

            public bool IsFoundAll()
            {
                return _foundCount >= _t.Length;
            }

            public void Update(char c, int index)
            {
                var node = _hash[c % 256];
                if (node.Data.First.Value == -1) _foundCount++;
                node.Data.RemoveFirst();
                node.Data.AddLast(index);

                // maintain a min heap
                for (int i = node.Index; i < _heap.Count;)
                {
                    int left = 2 * i + 1;
                    int right = 2 * i + 2;
                    if (right < _heap.Count && _heap[right].Data.First.Value < _heap[left].Data.First.Value)
                    {
                        left = right;
                    }
                    if (left < _heap.Count && _heap[left].Data.First.Value < _heap[i].Data.First.Value)
                    {
                        Swap(_heap, i, left);
                        _heap[i].Index = i;
                        _heap[left].Index = left;
                        i = left;
                    }
                    else
                    {
                        break;
                    }
                }
            }

            public int Lowest()
            {
                return _heap[0].Data.First.Value;
            }

            private string _t;

            private int _foundCount = 0;
            private Node[] _hash;
            private List<Node> _heap;

            private void Swap(List<Node> list, int i, int j)
            {
                var tmp = list[i];
                list[i] = list[j];
                list[j] = tmp;
            }

            class Node
            {
                public int Index;
                public LinkedList<int> Data;
                public Node(int i, LinkedList<int> data)
                {
                    this.Index = i;
                    this.Data = data;
                }
            }
        }

        public void Test()
        {
            Console.WriteLine(MinWindow("ADOBECODEBANC", "ABC") == "BANC");
            Console.WriteLine(MinWindow("ask_not_what_your_country_can_do_for_you_ask_what_you_can_do_for_your_country", "ask_country") == "sk_not_what_your_c");
        }
    }
}

