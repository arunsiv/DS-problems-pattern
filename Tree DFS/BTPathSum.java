import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

// LC#112
public class BTPathSum {
    // O(n) time | O(n) space
    // where n is the total number of nodes in the tree
    // space: O(n) for recurssive stack
    public static boolean hasPathSumDFSRecursive(TreeNode root, int sum) {
        // base check
        if (root == null) {
            return false;
        }

        // if the current node is leaf and its value is equal to the sum, we've found a
        // path
        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }

        return hasPathSumDFSRecursive(root.left, sum - root.val) || hasPathSumDFSRecursive(root.right, sum - root.val);
    }

    // O(n) time | O(n) space
    // where n is the total number of nodes in the tree
    // space: O(N) for result & O(N) for stack => O(N)
    public static boolean hasPathSumDFS(TreeNode root, int sum) {
        // base check
        if (root == null) {
            return false;
        }

        Stack<TreeNode> nodes = new Stack<>();
        Stack<Integer> targetSum = new Stack<>();
        nodes.add(root);
        targetSum.add(sum - root.val);

        while (!nodes.isEmpty()) {
            TreeNode currentNode = nodes.pop();
            int currentSum = targetSum.pop();

            if (currentNode.left == null && currentNode.right == null && currentSum == 0) {
                return true;
            }

            if (currentNode.left != null) {
                nodes.add(currentNode.left);
                targetSum.add(currentSum - currentNode.left.val);
            }

            if (currentNode.right != null) {
                nodes.add(currentNode.right);
                targetSum.add(currentSum - currentNode.right.val);
            }
        }

        return false;
    }

    // O(n) time | O(n) space
    // where n is the total number of nodes in the tree
    // space: O(N) for result & O(N) for queue => O(N)
    public static boolean hasPathSumBFS(TreeNode root, int sum) {
        // base check
        if (root == null) {
            return false;
        }

        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> targetSum = new LinkedList<>();
        nodes.offer(root);
        targetSum.offer(sum - root.val);

        while (!nodes.isEmpty()) {
            TreeNode currentNode = nodes.poll();
            int currentSum = targetSum.poll();

            if (currentNode.left == null && currentNode.right == null && currentSum == 0) {
                return true;
            }

            if (currentNode.left != null) {
                nodes.offer(currentNode.left);
                targetSum.offer(currentSum - currentNode.left.val);
            }

            if (currentNode.right != null) {
                nodes.offer(currentNode.right);
                targetSum.offer(currentSum - currentNode.right.val);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        System.out.println("Tree has path: " + BTPathSum.hasPathSumDFSRecursive(root, 23));
        System.out.println("Tree has path: " + BTPathSum.hasPathSumDFSRecursive(root, 16));

        System.out.println("Tree has path: " + BTPathSum.hasPathSumDFS(root, 23));
        System.out.println("Tree has path: " + BTPathSum.hasPathSumDFS(root, 16));

        System.out.println("Tree has path: " + BTPathSum.hasPathSumBFS(root, 23));
        System.out.println("Tree has path: " + BTPathSum.hasPathSumBFS(root, 16));
    }
}