using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using alg;


/*
 * tags: two pointers 
 * s is the number of nodes before the ring,
 * r is the number of nodes in the ring,
 * m is the number of nodes that slow pointer has moved in the ring when two pointers meet at first time.
 * 2(s+m) - (s+m) = r, so s=r-m
 */
namespace leetcode
{
    public class Lc142LinkedListCycleII
    {
        public ListNode DetectCycle(ListNode head)
        {
            ListNode slow = head, fast = head;
            while (fast != null)
            {
                slow = slow.next;
                fast = fast.next?.next;
                if (slow == fast) break;
            }

            if (fast == null) return null; // no cycle

            fast = head;
            while (fast != slow)
            {
                slow = slow.next;
                fast = fast.next;
            }

            return slow;
        }

        public void Test()
        {
        }
    }
}
