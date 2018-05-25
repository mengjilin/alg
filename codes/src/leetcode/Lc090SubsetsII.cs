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
    public class Lc090SubsetsII
    {
        public IList<IList<int>> SubsetsWithDup(int[] nums)
        {
            var ret = new List<IList<int>>();
            Array.Sort(nums);
            SubsetsWithDupRc(nums, 0, ret, new List<int>());
            return ret;
        }

        public IList<IList<int>> SubsetsWithDupDirect(int[] nums)
        {
            var ret = new List<IList<int>>();
            Array.Sort(nums);
            ret.Add(new List<int>());
            int countAddedByPrev = 1;
            for (int i = 0; i < nums.Length; i++)
            {
                int cnt = (i > 0 && nums[i] == nums[i - 1]) ? countAddedByPrev : ret.Count;
                for (int b = ret.Count - cnt, j = 0; j < cnt; j++)
                {
                    var li = ret[b + j].ToList();
                    li.Add(nums[i]);
                    ret.Add(li);
                }
                countAddedByPrev = cnt;
            }
            return ret;
        }

        public void SubsetsWithDupRc(int[] nums, int next, IList<IList<int>> result, IList<int> selected)
        {
            result.Add(selected.ToList());
            for (int i = next; i < nums.Length; i++)
            {
                if (i > next && nums[i] == nums[i - 1]) continue; // dup
                selected.Add(nums[i]);
                SubsetsWithDupRc(nums, i + 1, result, selected);
                selected.RemoveAt(selected.Count - 1); // backtracking
            }
        }

        public void Test()
        {
            var nums = new int[] { 1, 2, 2 };
            var exp = new List<IList<int>> {
                new List<int>{2},
                new List<int>{1},
                new List<int>{1,2,2},
                new List<int>{2,2},
                new List<int>{1,2},
                new List<int>{}};
            Console.WriteLine(exp.SameSet(SubsetsWithDupDirect(nums)));
        }
    }
}

