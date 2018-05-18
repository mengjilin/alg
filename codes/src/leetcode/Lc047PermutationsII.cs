using System;
using System.Collections.Generic;
using System.Linq;
using alg;

/*
 * tags: permutation
 */
namespace leetcode
{
    public class Lc047PermutationsII
    {
        public IList<IList<int>> PermuteUnique(int[] nums)
        {
            //return PermuteNext(nums);
            return PermuteBt(nums);
        }

        public IList<IList<int>> PermuteNext(int[] nums)
        {
            var ret = new List<IList<int>>();
            if (nums.Length == 0) return ret;
            var first = nums.ToList();
            ret.Add(first);

            while (true)
            {
                NextPermutation(nums);
                var next = nums.ToList();
                if (next.SequenceEqual(first)) return ret;
                ret.Add(next);
            }
        }

        void NextPermutation(int[] nums)
        {
            int pi = nums.Length - 2;
            while (pi >= 0 && nums[pi] >= nums[pi + 1]) pi--;

            if (pi >= 0)
            {
                int hi = nums.Length - 1;
                while (nums[hi] <= nums[pi]) hi--;
                swap(ref nums[pi], ref nums[hi]);
            }

            Array.Reverse(nums, pi + 1, nums.Length - pi - 1);
        }

        public IList<IList<int>> PermuteBt(int[] nums)
        {
            Array.Sort(nums);
            var ret = new List<IList<int>>();
            PermuteBtRc(nums, 0, ret);
            return ret;
        }

        public void PermuteBtRc(int[] nums, int start, IList<IList<int>> result)
        {
            if (start >= nums.Length) result.Add(nums.ToList());

            for (int i = start; i < nums.Length; i++)
            {
                if (i > start && nums[i - 1] == nums[i]) continue;
                swap(ref nums[start], ref nums[i]);
                PermuteBtRc(nums, start + 1, result);
                swap(ref nums[start], ref nums[i]); // backtracking
            }
        }

        void swap(ref int a, ref int b)
        {
            int t = a;
            a = b;
            b = t;
        }

        public void Test()
        {
            var res = PermuteUnique(new int[] { 1, 1, 2 });
            var exp = new List<IList<int>> {
                new List<int>{1,1,2},
                new List<int>{1,2,1},
                new List<int>{2,1,1} };
            (res as List<IList<int>>).Sort((a, b) => a.Compare(b));
            Console.WriteLine(0 == exp.Compare(res, Comparer<IList<int>>.Create((a, b) => a.Compare(b))));
        }
    }
}

