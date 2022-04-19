//singly linked list
class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class PalindromeLinkedlist {
    // O(N) time | O(1) space
    // where N is the number of nodes in the linked list
    public static boolean isPalindrome(ListNode head) {
        // base checks
        if (head == null) {
            return false;
        }

        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        // Slow pointer points to the center of the linked list
        // Reverse the second half of the Linkedlist
        ListNode headofSecondHalf = reverseLinkedlist(slowPointer);
        ListNode copyHeadofSecondHalf = headofSecondHalf;

        // compare the first and the second half
        while (head != null && headofSecondHalf != null) {
            if (head.value != headofSecondHalf.value) {
                break;
            }

            head = head.next;
            headofSecondHalf = headofSecondHalf.next;
        }

        // Reverse Linkedlist to original state
        reverseLinkedlist(copyHeadofSecondHalf);

        // there is a match
        if (head == null || headofSecondHalf == null) {
            return true;
        }

        return false;
    }

    private static ListNode reverseLinkedlist(ListNode head) {
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
        System.out.println("Is palindrome: " + PalindromeLinkedlist.isPalindrome(head));

        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + PalindromeLinkedlist.isPalindrome(head));

        head.next.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + PalindromeLinkedlist.isPalindrome(head));
    }
}