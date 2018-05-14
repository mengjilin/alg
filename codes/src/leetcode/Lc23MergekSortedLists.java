package leetcode;

/*
 * tags: two pointers, linked list, merge
 * Time(n), Space(1)
 */
public class Lc23MergekSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ListNode p = dummy, p1 = dummy.next, p2 = lists[i];
            while (p1 != null && p2 != null) {
                if (p1.val < p2.val) {
                    p.next = p1;
                    p1 = p1.next;
                } else {
                    p.next = p2;
                    p2 = p2.next;
                }
                p = p.next;
            }
            p.next = p1 != null ? p1 : p2;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode[] a = new ListNode[]{
                new ListNode(new int[]{1, 4, 5}),
                new ListNode(new int[]{1, 3, 4}),
                new ListNode(new int[]{2, 6})};
        int[] exp = new int[]{1, 1, 2, 3, 4, 4, 5, 6};
        System.out.println(mergeKLists(a).equals(exp));
    }
}
