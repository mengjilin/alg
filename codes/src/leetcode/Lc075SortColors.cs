using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;

/*
 * tags: two pointers
 */
namespace leetcode
{
    public class Lc075SortColors
    {
        public void SortColors(int[] nums)
        {
            int zero = 0, two = nums.Length - 1;
            for (int i = 0; i < nums.Length; i++)
            {
                for (; i < two && nums[i] == 2; two--) Swap(nums, i, two);
                for (; zero < i && nums[i] == 0; zero++) Swap(nums, i, zero);
            }
        }

        void Swap(int[] nums, int i, int j)
        {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        public void Test()
        {
            var res = new int[] { 2, 0, 2, 1, 1, 0 };
            var exp = new int[] { 0, 0, 1, 1, 2, 2 };
            SortColors(res);
            Console.WriteLine(exp.SequenceEqual(res));

            res = new int[] { 1, 2, 0 };
            exp = new int[] { 0, 1, 2 };
            SortColors(res);
            Console.WriteLine(exp.SequenceEqual(res));

            res = new int[] { 0, 1, 2, 0, 1, 2, 0, 2, 1, 2 };
            exp = new int[] { 0, 0, 0, 1, 1, 1, 2, 2, 2, 2 };
            SortColors(res);
            Console.WriteLine(exp.SequenceEqual(res));
        }
    }
}

