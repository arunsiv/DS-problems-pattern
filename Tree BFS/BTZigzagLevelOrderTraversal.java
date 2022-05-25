import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

// LC#103
public class BTZigzagLevelOrderTraversal {
    // O(N) time | O(N) space
    // N is the number of nodes in the tree
    // space: O(N) for result & O(N) for queue => O(N)
    public static List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        // base checks
        if (root == null) {
            return result;
        }

        // boolean for zigzag traversal
        boolean leftToRight = true;

        // queue to store the nodes
        Queue<TreeNode> queue = new LinkedList<>();

        // insert the root node in the queue
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // add the node to the current level
                if (leftToRight) {
                    // left to right
                    currentLevel.add(currentNode.val);
                } else {
                    // right to left
                    currentLevel.add(0, currentNode.val);
                }

                // insert the children of current node in the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // add current level to the result
            result.add(currentLevel);

            // change the direction
            leftToRight = !leftToRight;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);
        List<List<Integer>> result = BTZigzagLevelOrderTraversal.traverse(root);
        System.out.println("BT Level order traversal: " + result);
    }
}