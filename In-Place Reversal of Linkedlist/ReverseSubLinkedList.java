class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

public class ReverseSubLinkedList {
    // O(N) time | O(1) space
    // where N is the number of nodes in the linked list
    public static ListNode reverse(ListNode head, int left, int right) {
        // base checks
        if (left == right || head == null) {
            return head;
        }
        // we are interested in three parts of the LinkedList, part before index 'left',
        // part between 'left' and 'right', and the part after index 'right'

        // 1. get to the node at index 'left'
        ListNode previous = null, current = head;
        for (int i = 0; current != null && i < left - 1; i++) {
            previous = current;
            current = current.next;
        }

        // 2. reverse nodes between 'left' and 'right'

        // points to the node at index 'left - 1'
        ListNode lastNodeOfFirstPart = previous;

        //after reversing the LinkedList 'current' will become the last node of the sub-list
        ListNode lastNodeOfSubList = current;

        ListNode next = null;
        for (int i = 0; current != null && i < (right - left) + 1; i++) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        // 3. update the pointers

        //connect with the first part
        if (lastNodeOfFirstPart != null) {
            // 'previous' is now the first node of the sub-list
            lastNodeOfFirstPart.next = previous;
        } else {
            // this means left == 1 i.e., we are changing the first node (head) of the LinkedList
            head = previous;
        }

        // System.out.println(head.value + "::" + head.next);
        // System.out.println(head.next.value + "::" + head.next.next);
        // System.out.println(head.next.next.value + "::" + head.next.next.next);

        // System.out.println(previous.value + "::" + previous.next);
        // System.out.println(previous.next.value + "::" + previous.next.next);
        // System.out.println(previous.next.next.value + "::" + previous.next.next.next);
        // System.out.println(previous.next.next.next.value + "::" + previous.next.next.next.next);
        // System.out.println(previous.next.next.next.next.value + "::" + previous.next.next.next.next.next);
        // System.out.println(previous.next.next.next.next.next.value + "::" + previous.next.next.next.next.next);

        // connect with the last part
        lastNodeOfSubList.next = current;

        return head;
    }

    // O(N) time | O(1) space
    // where N is the number of nodes in the linked list
    public static ListNode reverseII(ListNode head, int left, int right) {
        // base checks
        if (head == null || left == right) {
            return head;
        }

        // 1. reach node at position left
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode current = head;
        ListNode leftPrev = dummy;
        for (int i = 0; i < left - 1; ++i) {
            leftPrev = current;
            current = current.next;
        }

        // 2. reverse the nodes between positions left and right
        ListNode prev = null;
        for (int i = 0; current != null && i < right - left + 1; i++) {
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

        ListNode result = ReverseSubLinkedList.reverse(head, 2, 4);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + "->");
            result = result.next;
        }
        System.out.println("");

        // head = new ListNode(1);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);

        // ListNode result1 = ReverseSubLinkedList.reverse(head, 0, 2);
        // while (result1 != null) {
        //     System.out.print(result1.value + "->");
        //     result1 = result1.next;
        // }
        // System.out.println("");

        // ListNode head2 = new ListNode(1);
        // head2.next = new ListNode(2);
        // head2.next.next = new ListNode(3);
        // head2.next.next.next = new ListNode(4);
        // head2.next.next.next.next = new ListNode(5);

        // ListNode result2 = ReverseSubLinkedList.reverse(head2, 4, 7);
        // while (result2 != null) {
        //     System.out.print(result2.value + "->");
        //     result2 = result2.next;
        // }
        // System.out.println("");

        // head = new ListNode(1);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);

        // result = ReverseSubLinkedList.reverseII(head, 2, 4);
        // System.out.print("Nodes of the reversed LinkedList are: ");
        // while (result != null) {
        //     System.out.print(result.value + "->");
        //     result = result.next;
        // }
        // System.out.println("");

        // head = new ListNode(1);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);

        // result1 = ReverseSubLinkedList.reverseII(head, 0, 2);
        // while (result1 != null) {
        //     System.out.print(result1.value + "->");
        //     result1 = result1.next;
        // }
        // System.out.println("");

        // head2 = new ListNode(1);
        // head2.next = new ListNode(2);
        // head2.next.next = new ListNode(3);
        // head2.next.next.next = new ListNode(4);
        // head2.next.next.next.next = new ListNode(5);

        // result2 = ReverseSubLinkedList.reverseII(head2, 4, 7);
        // while (result2 != null) {
        //     System.out.print(result2.value + "->");
        //     result2 = result2.next;
        // }
        // System.out.println("");
    }
}