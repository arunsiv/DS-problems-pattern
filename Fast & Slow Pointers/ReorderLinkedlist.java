//singly linked list
class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class ReorderLinkedlist {
    // O(N) time | O(1) space
    // where N is the number of nodes in the linked list        
    public static void reorder(ListNode head) {
        // base checks
        if (head == null || head.next == null) {
            return;
        }

        //1. find middle of the linked list
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        //2. reverse the 2nd half of the linked list
        ListNode headOfSecondHalf = reverseLinkedist(slowPointer);
        ListNode headOfFirstHalf = head;

        //3. reorder the linked list by iterating through the first and second half
        while (headOfFirstHalf != null && headOfSecondHalf != null) {
            ListNode temp = headOfFirstHalf.next;
            headOfFirstHalf.next = headOfSecondHalf;
            headOfFirstHalf = temp;

            temp = headOfSecondHalf.next;
            headOfSecondHalf.next = headOfFirstHalf;
            headOfSecondHalf = temp;
        }

        //4. set next of last node to null (only for linkedlist with even number of nodes)
        if (headOfFirstHalf != null) {
            headOfFirstHalf.next = null;
        }
    }

    private static ListNode reverseLinkedist(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        ReorderLinkedlist.reorder(head);
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }

        System.out.println("");
    }
}