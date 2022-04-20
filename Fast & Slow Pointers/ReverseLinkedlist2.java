//singly linked list
class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class ReverseLinkedlist2 {
    // O(N) time | O(1) space
    // where N is the number of nodes in the linked list        
    public static ListNode reverse(ListNode head, int left, int right) {
        // base checks
        if (head == null || left == right) {
            return head;
        }

        // 1. reach node at position left
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode current = head;
        ListNode leftPrev = dummy;
        for (int i = 0; i < left - 1; i++) {
            leftPrev = current;
            current = current.next;
        }

        // 2. reverse the nodes between positions left and right
        ListNode prev = null;
        for (int i = 0; i < right - left + 1; i++) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // 3. update pointers
        leftPrev.next.next = current;
        leftPrev.next = prev;

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode result1 = ReverseLinkedlist2.reverse(head, 2, 4);
        while (result1 != null) {
            System.out.print(result1.value + "->");
            result1 = result1.next;
        }

        System.out.println("");

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);

        ListNode result2 = ReverseLinkedlist2.reverse(head2, 1, 1);
        while (result2 != null) {
            System.out.print(result2.value + "->");
            result2 = result2.next;
        }

        System.out.println("");
    }
}