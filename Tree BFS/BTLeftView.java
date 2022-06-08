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

public class BTLeftView {
    // O(N) time | O(N) space
    // N is the number of nodes in the tree
    // space: O(N) for result & O(N) for queue => O(N)
    public static List<TreeNode> traverse(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();

        // base checks
        if (root == null) {
            return result;
        }

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);

        int levelSize = 0;

        while (!nodes.isEmpty()) {
            levelSize = nodes.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = nodes.poll();

                // if it is the last node of this level, add it to the result
                if (i == 0) {
                    result.add(currentNode);
                }

                if (currentNode.left != null) {
                    nodes.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    nodes.offer(currentNode.right);
                }
            }
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
        root.left.left.left = new TreeNode(3);
        List<TreeNode> result = BTLeftView.traverse(root);
        for (TreeNode node : result) {
            System.out.print(node.val + " ");
        }
        System.out.println("\n");
    }
}
