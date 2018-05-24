using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: binary search
 * Time(n), Space(1)
 * if (nums[lo] <= nums[m]) then left side is sorted
 * need handle duplicate: nums[lo] == nums[m]
 */
namespace leetcode
{
    public class Lc081SearchinRotatedSortedArrayII
    {
        public static bool Search(int[] nums, int target)
        {
            for (int lo = 0, hi = nums.Length - 1; lo <= hi;)
            {
                int m = (lo + hi) / 2;
                if (nums[m] == target) return true;

                if (nums[lo] < nums[m]) // left side is sorted
                { 
                    if (nums[lo] <= target && target <= nums[m]) hi = m - 1;
                    else lo = m + 1;
                }
                else if (nums[lo] > nums[m]) // right side is sorted
                {
                    if (nums[m] <= target && target <= nums[hi]) lo = m + 1;
                    else hi = m - 1;
                }
                else // nums[lo] == nums[m], duplicate
                {
                    lo++;
                }
            }

            return false;
        }

        public void Test()
        {
            var nums = new int[] { 2, 5, 6, 0, 0, 1, 2 };
            Console.WriteLine(Search(nums, 0) == true);
            Console.WriteLine(Search(nums, 3) == false);

            nums = new int[] { 1, 3, 1, 1, 1 };
            Console.WriteLine(Search(nums, 0) == false);
            Console.WriteLine(Search(nums, 3) == true);

            nums = new int[] { 1, 1, 3, 1 };
            Console.WriteLine(Search(nums, 3) == true);

            nums = new int[] { 1, 3, 5 };
            Console.WriteLine(Search(nums, 5) == true);
        }
    }
}

