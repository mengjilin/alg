using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: backtracking
 */
namespace leetcode
{
    public class Lc077Combinations
    {
        public IList<IList<int>> Combine(int n, int k)
        {
            var ret = new List<IList<int>>();
            CombineBt(n, k, 1, ret, new HashSet<int>());
            return ret;
        }

        void CombineBt(int n, int k, int next, IList<IList<int>> result, ISet<int> selected)
        {
            if (selected.Count == k)
            {
                result.Add(selected.ToList());
                return;
            }

            for (int i = next; i <= n; i++)
            {
                selected.Add(i);
                CombineBt(n, k, i + 1, result, selected);
                selected.Remove(i); // backtracking
            }
        }

        public void Test()
        {
            var exp = new List<IList<int>> {
                    new List<int>{1,2},
                    new List<int>{1,3},
                    new List<int>{1,4},
                    new List<int>{2,3},
                    new List<int>{2,4},
                    new List<int>{3,4},};
            Console.WriteLine(0 == exp.Compare(Combine(4, 2), Comparer<IList<int>>.Create((a, b) => a.Compare(b))));
        }
    }
}

