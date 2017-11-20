import java.util.List;

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

    private void insertToPool(ListNode n, ListNode[] pool) {
        for (int i = 0; i < pool.length; i++) {
            if (pool[i] == null) {
                pool[i] = n;
                break;
            } else if (n.val >= pool[i].val) {
                for (int j = pool.length-1; j > i; j--) {
                    pool[j] = pool[j-1];
                }
                pool[i] = n;
                break;
            }
        }
    }

    public ListNode mergeKSortedLists(ListNode[] lists) {
        int k = lists.length, len = lists.length, high = len/2;
        if (k == 0) return null;
        if (k == 1) return lists[0];

        while (high > 0) {
            for (int i = 0; i < high; i++) {
                lists[i] = mergeTwoSortedLists(lists[i], lists[len-1-i]);
                lists[len-1-i] = null;
            }
            len -= high;
            high = len/2;
        }
        return lists[0];
    }

    public static void main(String[] args) {
        LinkedLists ll = new LinkedLists();
        ListNode one = new ListNode(1);
        one.next = new ListNode(2);
        one.next.next = new ListNode(3);
        ListNode two = new ListNode(4);
        two.next = new ListNode(5);
        two.next.next = new ListNode(6);
        two.next.next.next = new ListNode(7);
        ListNode[] dog = new ListNode[]{one, two};
        System.out.println(ll.mergeKSortedLists(dog));
    }
}
