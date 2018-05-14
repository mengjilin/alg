package leetcode;

/*
 * tags: two pointers, linked list
 * Time(n), Space(1)
 */
public class Lc025ReverseNodesinkGroup {
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for (ListNode p = dummy; p.next != null && p.next.next != null; ) {
            int left = 0;
            for (ListNode np = p.next; left < k && np != null; left++) np = np.next;
            if (left < k) break;

            ListNode a = p.next;
            ListNode b = a.next;
            for (int i = 0; i < k - 1; i++) {
                ListNode c = b.next;
                b.next = a;
                a = b;
                b = c;
            }

            ListNode tmp = p.next;
            p.next = a;
            tmp.next = b;
            p = tmp;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(new int[]{1, 2, 3, 4, 5});
        int[] exp = new int[]{2, 1, 4, 3, 5};
        System.out.println(reverseKGroup(a, 2).equals(exp));

        a = new ListNode(new int[]{1, 2, 3, 4, 5});
        exp = new int[]{3, 2, 1, 4, 5};
        System.out.println(reverseKGroup(a, 3).equals(exp));
    }
}
