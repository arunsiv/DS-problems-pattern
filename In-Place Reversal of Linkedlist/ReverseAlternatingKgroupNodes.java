class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

public class ReverseAlternatingKgroupNodes {
    // O(n) time | O(1) space
    // where n is the number of nodes in the linked list
    // *** this solution reverses the group less than k ***
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode current = head;
        ListNode prev = dummyHead;

        // to store first and last node of k group
        ListNode firstNodeofGroup = null, lastNodeofGroup = null;

        while (current != null) {
            // after reversing the group, this node will be the last node
            lastNodeofGroup = current;
            int count = k;

            // 1. get to the kth node
            while (count > 0 && current != null) {
                current = current.next;
                count--;
            }

            // 2. check if the group size is less than k
            if (count != 0) {
                // reverse the remaining nodes i.e (k - count) nodes
                firstNodeofGroup = reverseKNodes(lastNodeofGroup, (k - count));

                // point prev to the first node of the reversed (k - count) nodes
                prev.next = firstNodeofGroup;
                break;
            }

            // 3. reverse the k group nodes
            firstNodeofGroup = reverseKNodes(lastNodeofGroup, k);

            // 4. point prev to the first node of the reversed k group
            prev.next = firstNodeofGroup;

            // 5. move prev to the last node of the reversed k group
            prev = lastNodeofGroup;

            // 6. skip 'k' nodes
            prev.next = current;
            for (int i = 0; current != null && i < k; ++i) {
                prev = current;
                current = current.next;
            }
        }

        return dummyHead.next;
    }

    // O(n) time | O(1) space
    // where n is the number of nodes in the linked list
    // *** this solution DOES NOT reverse the group less than k ***
    public static ListNode reverseKGroupII(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode current = head;
        ListNode prev = dummyHead;

        // to store first and last node of k group
        ListNode firstNodeofGroup = null, lastNodeofGroup = null;

        while (current != null) {
            // after reversing the group, this node will be the last node
            lastNodeofGroup = current;
            int count = k;

            // 1. get to the kth node
            while (count > 0 && current != null) {
                current = current.next;
                count--;
            }

            // 2. check if the group size is less than k
            if (count != 0) {
                // don't reverse the remaining nodes
                // point prev to the first of the remaining nodes
                prev.next = lastNodeofGroup;
                break;
            }

            // 3. reverse the k group nodes
            firstNodeofGroup = reverseKNodes(lastNodeofGroup, k);

            // 4. point prev to the first node of the reversed k group
            prev.next = firstNodeofGroup;

            // 5. move prev to the last node of the reversed k group
            prev = lastNodeofGroup;

            // 6. skip 'k' nodes
            prev.next = current;
            for (int i = 0; current != null && i < k; ++i) {
                prev = current;
                current = current.next;
            }
        }

        return dummyHead.next;
    }

    private static ListNode reverseKNodes(ListNode current, int k) {
        ListNode prev = null, next = null;
        while (current != null && k > 0) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            k--;
        }

        return prev;
    }

    public static ListNode reverse(ListNode head, int k) {
        if (k <= 1 || head == null)
            return head;

        ListNode current = head, previous = null;
        while (current != null) { // break if we've reached the end of the list
            ListNode lastNodeOfPreviousPart = previous;
            // after reversing the LList 'current' will become the last node of the sub-list
            ListNode lastNodeOfSubList = current;
            ListNode next = null; // will be used to temporarily store the next node
            // reverse 'k' nodes
            for (int i = 0; current != null && i < k; i++) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            // connect with the previous part
            if (lastNodeOfPreviousPart != null)
                // 'previous' is now the first node of the sub-list
                lastNodeOfPreviousPart.next = previous;
            else // this means we are changing the first node (head) of the LinkedList
                head = previous;

            // connect with the next part
            lastNodeOfSubList.next = current;

            // skip 'k' nodes
            for (int i = 0; current != null && i < k; ++i) {
                previous = current;
                current = current.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        ListNode result = ReverseAlternatingKgroupNodes.reverse(head, 5);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + "->");
            result = result.next;
        }
        System.out.println("");

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        result = ReverseAlternatingKgroupNodes.reverseKGroup(head, 5);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + "->");
            result = result.next;
        }
        System.out.println("");

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        result = ReverseAlternatingKgroupNodes.reverseKGroupII(head, 5);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + "->");
            result = result.next;
        }
        System.out.println("");
    }
}