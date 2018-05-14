package leetcode;

/*
 * tags: two pointers, linked list
 * Time(n), Space(1)
 */
public class Lc024SwapNodesinPairs {
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for (ListNode p = dummy; p != null && p.next != null && p.next.next != null; ) {
            ListNode a = p.next;
            ListNode b = a.next;
            ListNode c = b.next;
            p.next = b;
            b.next = a;
            a.next = c;
            p = a;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(new int[]{1, 2, 4, 5});
        int[] exp = new int[]{2, 1, 5, 4};
        System.out.println(swapPairs(a).equals(exp));

        a = new ListNode(new int[]{1});
        exp = new int[]{1};
        System.out.println(swapPairs(a).equals(exp));

        a = new ListNode(new int[]{1, 2, 3});
        exp = new int[]{2, 1, 3};
        System.out.println(swapPairs(a).equals(exp));
    }
}

