//singly linked list
class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class LinkedlistCycle {
    //O(N) time | O(1) space
    //where N is the number of nodes in the linked list
    public static boolean hasCycle(ListNode head) {
        // base checks
        if (head == null) {
            return false;
        }

        //set the pointers to the head of the linked list
        ListNode fastPointer = head;
        ListNode slowPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            //increment fast pointer by 2
            fastPointer = fastPointer.next.next;

            //increment slow pointer by 1
            slowPointer = slowPointer.next;

            //both pointers have met, foudn the cycle
            if (fastPointer == slowPointer) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        System.out.println("LinkedList has cycle: " + LinkedlistCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + LinkedlistCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + LinkedlistCycle.hasCycle(head));
    }
}