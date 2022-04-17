//singly linked list
class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class LinkedlistCycleLength {
    //O(N) time | O(1) space
    //where N is the number of nodes in the linked list
    public static int findCycleLength(ListNode head) {
        // base checks
        if (head == null) {
            return 0;
        }

        ListNode fastPointer = head;
        ListNode slowPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;

            if (fastPointer == slowPointer) {
                System.out.println(slowPointer.value);
                return calculateLinkedlistCyclelength(slowPointer);
            }
        }

        return 0;
    }

    private static int calculateLinkedlistCyclelength(ListNode slowPointer) {
        ListNode currentPointer = slowPointer;
        int cycleLength = 0;

        do {
            currentPointer = currentPointer.next;
            cycleLength++;
        } while (currentPointer != slowPointer);

        return cycleLength;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle length: "
                + LinkedlistCycleLength.findCycleLength(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle length: "
                + LinkedlistCycleLength.findCycleLength(head));
    }
}