//singly linked list
class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class ReverseLinkedlist {
    // O(N) time | O(1) space
    // where N is the number of nodes in the linked list    
    public static ListNode reverse(ListNode head) {
        //base checks
        if (head == null || head.next == null) {
            throw new IllegalArgumentException();
        }

        ListNode current = head, prev = null;

        while(current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    // O(N) time | O(N) space (for call stack)
    // where N is the number of nodes in the linked list
    public static ListNode reverseIterative(ListNode head) {
        //base checks
        if (head == null || head.next == null) {
            throw new IllegalArgumentException();
        }

        return reverseIterative(head, null);
    }

    public static ListNode reverseIterative(ListNode head, ListNode prev) {
        //base checks
        if (head == null) {
            return prev;
        }

        ListNode next = head.next;
        head.next = prev;

        return reverseIterative(next, head);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);

        ListNode result1 = ReverseLinkedlist.reverse(head);
        while (result1 != null) {
            System.out.print(result1.value + "->");
            result1 = result1.next;
        }

        System.out.println("");

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(5);
        head2.next.next.next = new ListNode(7);
        head2.next.next.next.next = new ListNode(9);

        ListNode result2 = ReverseLinkedlist.reverseIterative(head2);
        while (result2 != null) {
            System.out.print(result2.value + "->");
            result2 = result2.next;
        }

        System.out.println("");
    }   
}