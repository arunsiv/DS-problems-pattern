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
    public void printLevelOrder() {
        TreeNodes nextLevelRoot = this;
        while (nextLevelRoot != null) {
            TreeNodes current = nextLevelRoot;
            nextLevelRoot = null;
            while (current != null) {
                System.out.print(current.val + "->");
                if (nextLevelRoot == null) {
                    if (current.left != null) {
                        nextLevelRoot = current.left;
                    } else if (current.right != null) {
                        nextLevelRoot = current.right;
                    }
                }
                current = current.next;
            }
            System.out.print("null");
            System.out.println();
        }
    }
}

// LC# 116 & 117
class ConnectLevelOrderSiblings {
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
        int levelSize = 0;

        while (!nodes.isEmpty()) {
            TreeNodes previousNode = null;
            levelSize = nodes.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNodes currentNode = nodes.poll();

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
    }

    // O(N) time | O(1) space
    // N is the number of nodes in the tree
    public static void connectII(TreeNodes root) {
        while (root != null) {
            TreeNodes tempChild = new TreeNodes(0);
            TreeNodes currentChild = tempChild;

            while (root != null) {
                if (root.left != null) {
                    currentChild.next = root.left;
                    currentChild = currentChild.next;
                }
                if (root.right != null) {
                    currentChild.next = root.right;
                    currentChild = currentChild.next;
                }
                root = root.next;
            }

            root = tempChild.next;
        }
    }

    // LC #116 - only works for perfect binary tree
    // O(N) time | O(1) space
    // N is the number of nodes in the tree
    public static void connectIII(TreeNodes root) {
        TreeNodes current = root;
        TreeNodes next = (root.left != null) ? root : null;

        while (current != null && next != null) {
            current.left.next = current.right;

            if (current.next != null && current.right != null) {
                current.right.next = current.next.left;
            }

            current = current.next;

            if (current == null) {
                current = next;
                next = current.left;
            }
        }
    }

    public static void main(String[] args) {
        TreeNodes root = new TreeNodes(12);
        root.left = new TreeNodes(7);
        root.right = new TreeNodes(1);
        root.left.left = new TreeNodes(9);
        root.right.left = new TreeNodes(10);
        root.right.right = new TreeNodes(5);
        ConnectLevelOrderSiblings.connect(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();

        root = new TreeNodes(1);
        root.left = new TreeNodes(2);
        root.right = new TreeNodes(3);
        root.left.left = new TreeNodes(4);
        root.left.right = new TreeNodes(5);
        root.right.left = new TreeNodes(6);
        root.right.right = new TreeNodes(7);
        ConnectLevelOrderSiblings.connect(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();

        root = new TreeNodes(12);
        root.left = new TreeNodes(7);
        root.right = new TreeNodes(1);
        root.left.left = new TreeNodes(9);
        root.right.left = new TreeNodes(10);
        root.right.right = new TreeNodes(5);
        ConnectLevelOrderSiblings.connectII(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();

        root = new TreeNodes(1);
        root.left = new TreeNodes(2);
        root.right = new TreeNodes(3);
        root.left.left = new TreeNodes(4);
        root.left.right = new TreeNodes(5);
        root.right.left = new TreeNodes(6);
        root.right.right = new TreeNodes(7);
        ConnectLevelOrderSiblings.connectII(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();

        root = new TreeNodes(12);
        root.left = new TreeNodes(7);
        root.right = new TreeNodes(1);
        root.left.left = new TreeNodes(9);
        root.right.left = new TreeNodes(10);
        root.right.right = new TreeNodes(5);
        ConnectLevelOrderSiblings.connectIII(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();

        root = new TreeNodes(1);
        root.left = new TreeNodes(2);
        root.right = new TreeNodes(3);
        root.left.left = new TreeNodes(4);
        root.left.right = new TreeNodes(5);
        root.right.left = new TreeNodes(6);
        root.right.right = new TreeNodes(7);
        ConnectLevelOrderSiblings.connectIII(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();
    }
}