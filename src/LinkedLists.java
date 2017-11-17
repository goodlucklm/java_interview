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

    public static void main(String[] args) {
        LinkedLists ll = new LinkedLists();
        ListNode head = new ListNode(0);
        ListNode p = new ListNode(1);
        head.next = p;
        System.out.println(ll.hasCycle(head));
    }
}
