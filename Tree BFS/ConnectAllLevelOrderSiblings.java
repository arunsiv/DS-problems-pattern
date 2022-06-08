import java.util.LinkedList;
import java.util.Queue;

class TreeNodes {
    int val;
    TreeNodes left;
    TreeNodes right;
    TreeNodes next;

    TreeNodes(int x) {
        val = x;
        left = null;
        right = null;
        next = null;
    }

    // level order traversal using 'next' pointer
    public void printTree() {
        TreeNodes current = this;
        while (current != null) {
            System.out.print(current.val + "->");
            current = current.next;
        }
        System.out.print("null");
        System.out.println();
    }
}

// LC#
class ConnectAllLevelOrderSiblings {
    // O(N) time | O(N) space
    // N is the number of nodes in the tree
    // space: O(N) for result & O(N) for queue => O(N)
    public static void connect(TreeNodes root) {
        // base checks
        if (root == null) {
            return;
        }

        Queue<TreeNodes> nodes = new LinkedList<>();
        nodes.offer(root);

        TreeNodes currentNode = null, previousNode = null;
        while (!nodes.isEmpty()) {
            currentNode = nodes.poll();

            if (previousNode != null) {
                previousNode.next = currentNode;
            }

            previousNode = currentNode;

            if (currentNode.left != null) {
                nodes.offer(currentNode.left);
            }

            if (currentNode.right != null) {
                nodes.offer(currentNode.right);
            }
        }
    }

    // O(N) time | O(1) space
    // N is the number of nodes in the tree
    public static void connectII(TreeNodes root) {
        TreeNodes prevNode = root;
        TreeNodes currentNode = root;

        while (currentNode != null) {
            if (currentNode.left != null) {
                prevNode.next = currentNode.left;
                prevNode = currentNode.left;
            }

            if (currentNode.right != null) {
                prevNode.next = currentNode.right;
                prevNode = currentNode.right;
            }

            prevNode.next = null;
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {
        TreeNodes root = new TreeNodes(12);
        root.left = new TreeNodes(7);
        root.right = new TreeNodes(1);
        root.left.left = new TreeNodes(9);
        root.right.left = new TreeNodes(10);
        root.right.right = new TreeNodes(5);
        ConnectAllLevelOrderSiblings.connect(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printTree();

        root = new TreeNodes(1);
        root.left = new TreeNodes(2);
        root.right = new TreeNodes(3);
        root.left.left = new TreeNodes(4);
        root.left.right = new TreeNodes(5);
        root.right.left = new TreeNodes(6);
        root.right.right = new TreeNodes(7);
        ConnectAllLevelOrderSiblings.connect(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printTree();

        root = new TreeNodes(12);
        root.left = new TreeNodes(7);
        root.right = new TreeNodes(1);
        root.left.left = new TreeNodes(9);
        root.right.left = new TreeNodes(10);
        root.right.right = new TreeNodes(5);
        ConnectAllLevelOrderSiblings.connectII(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printTree();

        root = new TreeNodes(1);
        root.left = new TreeNodes(2);
        root.right = new TreeNodes(3);
        root.left.left = new TreeNodes(4);
        root.left.right = new TreeNodes(5);
        root.right.left = new TreeNodes(6);
        root.right.right = new TreeNodes(7);
        ConnectAllLevelOrderSiblings.connectII(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printTree();
    }
}