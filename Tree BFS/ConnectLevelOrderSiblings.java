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
                System.out.print(current.val + " ");
                if (nextLevelRoot == null) {
                    if (current.left != null) {
                        nextLevelRoot = current.left;
                    } else if (current.right != null) {
                        nextLevelRoot = current.right;
                    }
                }
                current = current.next;
            }
            System.out.println();
        }
    }
}

//LC# 116 & 117
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
    }
}