using System;
using System.Collections.Generic;
using System.Linq;
using alg;

/*
 * tags: permutation
 */
namespace leetcode
{
    public class Lc046Permutations
    {
        public IList<IList<int>> Permute(int[] nums)
        {
            //return PermuteNext(nums);
            return PermuteBt(nums);
        }

        public IList<IList<int>> PermuteNext(int[] nums)
        {
            var ret = new List<IList<int>>();
            if (nums.Length == 0) return ret;

            int total = 1;
            for (int i = 1; i <= nums.Length; i++) total *= i;

            for (int i = 0; i < total; i++)
            {
                ret.Add(nums.ToList());
                NextPermutation(nums);
            }

            return ret;
        }

        void NextPermutation(int[] nums)
        {
            int pi = nums.Length - 2;
            while (pi >= 0 && nums[pi] >= nums[pi + 1]) pi--;

            if (pi >= 0)
            {
                int lo = pi + 1;
                for (int hi = nums.Length - 1; lo < hi;)
                {
                    int m = (lo + hi + 1) / 2;
                    if (nums[m] < nums[pi]) hi = m - 1;
                    else lo = m;
                }
                int temp = nums[pi];
                nums[pi] = nums[lo];
                nums[lo] = temp;
            }

            Array.Reverse(nums, pi + 1, nums.Length - pi - 1);
        }

        public IList<IList<int>> PermuteBt(int[] nums)
        {
            var ret = new List<IList<int>>();
            PermuteBtRc(nums, 0, ret);
            return ret;
        }

        public void PermuteBtRc(int[] nums, int start, IList<IList<int>> result)
        {
            if (start >= nums.Length) result.Add(nums.ToList());

            for (int i = start; i < nums.Length; i++)
            {
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
            var res = Permute(new int[] { 1, 2, 3 });
            var exp = new List<IList<int>> {
                new List<int>{1,2,3},
                new List<int>{1,3,2},
                new List<int>{2,1,3},
                new List<int>{2,3,1},
                new List<int>{3,1,2},
                new List<int>{3,2,1} };
            (res as List<IList<int>>).Sort((a, b) => a.Compare(b));
            Console.WriteLine(0 == exp.Compare(res, Comparer<IList<int>>.Create((a, b) => a.Compare(b))));
        }
    }
}
