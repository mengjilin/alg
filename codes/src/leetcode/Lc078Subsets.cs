using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: backtracking, bit manipulation
 */
namespace leetcode
{
    public class Lc078Subsets
    {
        public IList<IList<int>> Subsets(int[] nums)
        {
            //return SubsetsBt(nums);
            //return SubsetsBit(nums);
            return SubsetsDirect(nums);
        }

        public IList<IList<int>> SubsetsBit(int[] nums)
        {
            var ret = new List<IList<int>>();
            int subsetNum = (int)Math.Pow(2, nums.Length);
            for (int j = 0; j < subsetNum; j++) ret.Add(new List<int>());
            for (int i = 0; i < nums.Length; i++)
            {
                for (int j = 0; j < subsetNum; j++)
                {
                    if (((j >> i) & 1) != 0) ret[j].Add(nums[i]);
                }
            }
            return ret;
        }

        public IList<IList<int>> SubsetsDirect(int[] nums)
        {
            var ret = new List<IList<int>>();
            ret.Add(new List<int>());
            for (int i = 0; i < nums.Length; i++)
            {
                var cnt = ret.Count;
                for (int j = 0; j < cnt; j++)
                {
                    ret.Add(ret[j].ToList());
                    ret[j].Add(nums[i]);
                }
            }
            return ret;
        }

        public IList<IList<int>> SubsetsBt(int[] nums)
        {
            var ret = new List<IList<int>>();
            SubsetsBtRc(nums, 0, ret, new List<int>());
            return ret;
        }

        void SubsetsBtRc(int[] nums, int next, IList<IList<int>> result, IList<int> selected)
        {
            result.Add(selected.ToList());
            for (int i = next; i < nums.Length; i++)
            {
                selected.Add(nums[i]);
                SubsetsBtRc(nums, i + 1, result, selected);
                selected.RemoveAt(selected.Count - 1); // backtracking
            }
        }

        public void Test()
        {
            var res = Subsets(new int[] { 1, 2, 3 });
            var exp = new List<IList<int>> {
                    new List<int>{3},
                    new List<int>{1},
                    new List<int>{2},
                    new List<int>{1,2,3},
                    new List<int>{1,3},
                    new List<int>{2,3},
                    new List<int>{1,2},
                    new List<int>{}};
            Console.WriteLine(exp.SameSet(res));
        }
    }
}

