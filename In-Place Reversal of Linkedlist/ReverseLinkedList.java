class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

public class ReverseLinkedList {
    // O(n) time | O(1) space
    // where n is the number of nodes in the linkedlist
    public static ListNode reverse(ListNode head) {
        // base checks
        if (head == null) {
            return null;
        }

        ListNode current = head; // current node that we will be processing
        ListNode prev = null; // previous node that we have processed
        ListNode next = null; // will be used to temporarily store the next node

        while (current != null) {
            // temporarily store the next node
            next = current.next;

            // reverse the current node
            current.next = prev;

            // point prev to the current node
            prev = current;

            // move on to the next node
            current = next;
        }

        // curren node will point to null and prev node will point to the new head
        // return the head
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);

        ListNode result = ReverseLinkedList.reverse(head);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + "->");
            result = result.next;
        }
        System.out.println("");

        head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(5);

        result = ReverseLinkedList.reverse(head);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + "->");
            result = result.next;
        }
        System.out.println("");
    }
}