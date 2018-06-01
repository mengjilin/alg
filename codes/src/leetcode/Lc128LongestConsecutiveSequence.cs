using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: union find?
 */
namespace leetcode
{
    public class Lc128LongestConsecutiveSequence
    {
        public int LongestConsecutive(int[] nums)
        {
            var ret = 0;
            var numset = new HashSet<int>(nums);

            foreach (var i in numset)
            {
                if (numset.Contains(i - 1)) continue;
                int slen = 1;
                while (numset.Contains(i + slen)) slen++;
                ret = Math.Max(ret, slen);
            }

            return ret;
        }

        public void Test()
        {
            var nums = new int[] { 100, 4, 200, 1, 3, 2 };
            Console.WriteLine(LongestConsecutive(nums) == 4);
        }
    }
}

