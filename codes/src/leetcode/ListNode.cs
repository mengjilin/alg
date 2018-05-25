using System;
using System.Collections.Generic;
using System.Text;

namespace leetcode
{
    public class ListNode
    {
        public int val;
        public ListNode next;

        public ListNode(int x)
        {
            val = x;
        }

        public ListNode(IEnumerable<int> nums)
        {
            using (var it = nums.GetEnumerator())
            {
                var p = this;
                if (it.MoveNext()) p.val = it.Current;
                while (it.MoveNext())
                {
                    p.next = new ListNode(it.Current);
                    p = p.next;
                }
            }
        }

        public List<int> ToList()
        {
            var ret = new List<int>();
            for (ListNode p = this; p != null; p = p.next)
            {
                ret.Add(p.val);
            }
            return ret;
        }
    }

}
