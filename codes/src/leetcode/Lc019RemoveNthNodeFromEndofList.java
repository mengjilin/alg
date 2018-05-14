package leetcode;

/*
 * tags: two pointers, linked list
 * Time(n), Space(1)
 */
public class Lc019RemoveNthNodeFromEndofList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head, pn = head;
        for (int i = 0; i < n; i++) p = p.next;
        if (p == null) return head.next;
        for (; p.next != null; p = p.next) pn = pn.next;
        pn.next = pn.next.next;
        return head;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        int[] exp = new int[]{1, 2, 3, 5};
        System.out.println(removeNthFromEnd(new ListNode(a), 2).equals(exp));
    }

}

