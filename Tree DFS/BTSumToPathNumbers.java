class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

//LC#129
public class BTSumToPathNumbers {
    // O(n) time | O(n) space
    // where n is the total number of nodes in the tree
    // space: O(n) for recurssive stack
    public static int findSumOfPathNumbers(TreeNode root) {
        return findSumOfPathNumbersIterative(root, 0);
    }

    public static int findSumOfPathNumbersIterative(TreeNode currentNode, int pathSum) {
        // base checks
        if (currentNode == null) {
            return 0;
        }

        // calculate the path number of the current node
        pathSum = (10 * pathSum) + currentNode.val;

        // if the current node is a leaf, return the current path sum
        if (currentNode.left == null && currentNode.right == null) {
            return pathSum;
        }

        // traverse the left and the right sub-tree
        return findSumOfPathNumbersIterative(currentNode.left, pathSum)
                + findSumOfPathNumbersIterative(currentNode.right, pathSum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        System.out.println("Total Sum of Path Numbers: " + BTSumToPathNumbers.findSumOfPathNumbers(root));
    }
}