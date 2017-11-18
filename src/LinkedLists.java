public class LinkedLists {
    public boolean hasCycle(ListNode head) {
        ListNode fast, slow;
        if (null == head || null == head.next) return false;
        fast = head.next.next;
        slow = head.next;
        while (null != fast && null != fast.next && fast != slow) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return fast == slow;
    }

    public ListNode detectCycle(ListNode head) {
        if (null == head || null == head.next) return null;
        ListNode fast, slow;
        fast = head;
        slow = head;
        boolean hasLoop = false;
        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasLoop = true;
                break;
            }
        }

        if (!hasLoop) return null;

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;

    }

    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode p = result;
        while (null != l1 && null != l2) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null)
            p.next = l1;
        else
            p.next = l2;
        return result.next;
    }

    public static void main(String[] args) {
        LinkedLists ll = new LinkedLists();
        ListNode head = new ListNode(0);
        ListNode p = new ListNode(1);
        head.next = p;
        ListNode head2 = new ListNode(8);
        p = new ListNode(9);
        head2.next = p;
        System.out.println(ll.mergeTwoSortedLists(head, head2));
    }
}
