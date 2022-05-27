import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

public class BTMinDepth {
    // O(N) time | O(N) space
    // N is the number of nodes in the tree
    // space: O(N) for result & O(N) for queue => O(N)
    public static int findDepth(TreeNode root) {
        int minDepth = 0;

        // base checks
        if (root == null) {
            return minDepth;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int levelSize = 0;

        while (!queue.isEmpty()) {
            // increment the min depth
            minDepth++;

            levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // check if this is a leaf node
                if (currentNode.left == null && currentNode.right == null) {
                    return minDepth;
                }

                // insert the children of current node in the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }

        return minDepth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree Minimum Depth: " + BTMinDepth.findDepth(root));
        root.left.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        System.out.println("Tree Minimum Depth: " + BTMinDepth.findDepth(root));
    }
}