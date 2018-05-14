package leetcode;

public class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int[] a) {
        if (a != null && a.length > 0) val = a[0];
        ListNode p = this;
        for (int i = 1; i < a.length; i++) {
            p.next = new ListNode(a[i]);
            p = p.next;
        }
    }

    public boolean equals(int[] a) {
        ListNode p = this;
        for (int i = 0; i < a.length && p != null; i++, p = p.next) {
            if (a[i] != p.val) return false;
            if (i == a.length - 1 && p.next == null) return true;
        }
        return false;
    }
}
