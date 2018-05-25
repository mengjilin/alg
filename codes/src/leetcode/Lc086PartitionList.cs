using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: list
 */
namespace leetcode
{
    public class Lc086PartitionList
    {
        public ListNode Partition(ListNode head, int x)
        {
            var smDummy = new ListNode(0);
            var bigDummy = new ListNode(0);
            ListNode sm = smDummy;
            ListNode big = bigDummy;
            for (ListNode p = head; p != null; p = p.next)
            {
                if (p.val < x) sm = sm.next = p;
                else big = big.next = p;
            }
            sm.next = bigDummy.next;
            big.next = null;
            return smDummy.next;
        }

        public void Test()
        {
            var nums = new List<int> { 1, 4, 3, 2, 5, 2 };
            var exp = new List<int> { 1, 2, 2, 4, 3, 5 };
            Console.WriteLine(exp.SequenceEqual(Partition(new ListNode(nums), 3).ToList()));
        }
    }
}

