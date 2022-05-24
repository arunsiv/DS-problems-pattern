class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

public class RotateLinkedList {
    // O(N) time | O(1) space
    // where N is the number of nodes in the linked list
    public static ListNode rotate(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }

        // 1. find the lengh of the linked list
        ListNode lastNode = head;
        int lengthOfLinkedList = 1;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
            lengthOfLinkedList++;
        }

        System.out.println(lastNode.value);

        // 2. point the last node to the head to creaate a circular list for rotation
        lastNode.next = head;

        // 3. find out how many rotations to do
        int rotationsTodo = lengthOfLinkedList - k % lengthOfLinkedList;
        ListNode lastNodeOfRotatedList = head;
        while (rotationsTodo > 1) {
            lastNodeOfRotatedList = lastNodeOfRotatedList.next;
            rotationsTodo--;
        }

        System.out.println(rotationsTodo + ":" + lastNodeOfRotatedList.value);

        // 4. set the head
        head = lastNodeOfRotatedList.next;

        // 5. point last node to null to break the circular list
        lastNodeOfRotatedList.next = null;

        // 6. return the head
        return head;
    }

    // O(N) time | O(1) space
    // where N is the number of nodes in the linked list
    public static ListNode rotateII(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        ListNode copyHead = head;

        int len = 1;
        while (copyHead.next != null) {
            copyHead = copyHead.next;
            len++;
        }

        System.out.println("CH: " + copyHead.value + ";" + (len - k % len));

        copyHead.next = head;

        for (int i = len - k % len; i > 1; i--) {
            head = head.next;
        }

        System.out.println("head: " + head.value);

        copyHead = head.next;
        head.next = null;

        return copyHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);
        // head.next.next.next.next.next = new ListNode(6);

        ListNode result = RotateLinkedList.rotate(head, 5);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + "->");
            result = result.next;
        }
        System.out.println("\n");
    }
}