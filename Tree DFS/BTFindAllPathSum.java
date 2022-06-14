import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

// LC#113
public class BTFindAllPathSum {
    // O(n^2) time | O(nlogn) space
    // where n is the total number of nodes in the tree
    // time: O(n) for recursive stack & O(n) for the root-to-leaf path
    // space: O(nlogn) for all paths
    public static List<List<Integer>> findAllSumPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findAllSumPathsIterative(root, sum, currentPath, allPaths);

        return allPaths;
    }

    private static void findAllSumPathsIterative(TreeNode currentNode, int sum, List<Integer> currentPath,
            List<List<Integer>> allPaths) {
        // base checks
        if (currentNode == null) {
            return;
        }

        // add the current node to the path
        currentPath.add(currentNode.val);

        // if the current node is a leaf and its value is equal to sum, save the current
        // path
        if (currentNode.left == null && currentNode.right == null && currentNode.val == sum) {
            allPaths.add(new ArrayList<Integer>(currentPath));
        } else {
            // traverse the left sub tree
            findAllSumPathsIterative(currentNode.left, sum - currentNode.val, currentPath, allPaths);

            // traverse the right sub tree
            findAllSumPathsIterative(currentNode.right, sum - currentNode.val, currentPath, allPaths);
        }

        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call
        // stack.
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = BTFindAllPathSum.findAllSumPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
    }
}