//singly linked list
class ListNode {
    int value;
    ListNode next;

    ListNode(int val) {
        value = val;
        next = null;
    }
}

public class LinkedlistCycleStart {
    // O(N) time | O(1) space
    // where N is the number of nodes in the linked list
    public static ListNode findCycleStart(ListNode head) {
        // base checks
        if (head == null) {
            return null;
        }

        ListNode fastPointer = head;
        ListNode slowPointer = head;
        int cycleLength = 0;

        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;

            if (fastPointer == slowPointer) {
                cycleLength = calculateLinkedlistCyclelength(slowPointer);
                break;
            }
        }

        return (cycleLength == 0) ? null : findStart(head, cycleLength);
    }

    public static ListNode findCycleStartAlternative(ListNode head) {
        // base checks
        if (head == null) {
            return null;
        }

        ListNode fastPointer = head;
        ListNode slowPointer = head;
        ListNode entryPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;

            if (fastPointer == slowPointer) {
                while (slowPointer != entryPointer) {
                    slowPointer = slowPointer.next;
                    entryPointer = entryPointer.next;
                }

                return entryPointer;
            }
        }

        return null;
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

    private static ListNode findStart(ListNode head, int cycleLength) {
        ListNode pointer1 = head, pointer2 = head;

        // increment pointer2 to the start of the cycle
        while (cycleLength > 0) {
            pointer2 = pointer2.next;
            cycleLength--;
        }

        while (pointer1 != pointer2) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        return pointer1;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);

        System.out.println("LinkedList cycle start: " +
                LinkedlistCycleStart.findCycleStart(head).value);
        System.out.println("LinkedList cycle start: " +
                LinkedlistCycleStart.findCycleStartAlternative(head).value);

        head.next = new ListNode(2);
        head.next.next = head.next;

        System.out.println("LinkedList cycle start: " +
                LinkedlistCycleStart.findCycleStart(head).value);
        System.out.println("LinkedList cycle start: " +
                LinkedlistCycleStart.findCycleStartAlternative(head).value);

        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " +
                LinkedlistCycleStart.findCycleStart(head).value);
        System.out.println("LinkedList cycle start: " +
                LinkedlistCycleStart.findCycleStartAlternative(head).value);

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " +
                LinkedlistCycleStart.findCycleStart(head).value);
        System.out.println("LinkedList cycle start: " +
                LinkedlistCycleStart.findCycleStartAlternative(head).value);

        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " +
                LinkedlistCycleStart.findCycleStart(head).value);
        System.out.println("LinkedList cycle start: " +
                LinkedlistCycleStart.findCycleStartAlternative(head).value);
    }
}