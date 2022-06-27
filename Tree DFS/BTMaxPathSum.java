class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

//LC#124
public class BTMaxPathSum {
    private static int globalMaxPathSum;

    //
    public static int findMaximumPathSum(TreeNode root) {
        globalMaxPathSum = Integer.MIN_VALUE;
        findMaximumPathSumRecursive(root);

        return globalMaxPathSum;
    }

    public static int findMaximumPathSumRecursive(TreeNode currentNode) {
        //base checks
        if (currentNode == null) {
            return 0;
        }

        // get the max path for left subtree
        int maxPathSumFromLeftTree = findMaximumPathSumRecursive(currentNode.left);

        // get the max path for right subtree
        int maxPathSumFromRightTree = findMaximumPathSumRecursive(currentNode.right);

        // ignore paths with negative sums, since we need to find the maximum sum we should
        // ignore any path which has an overall negative sum.
        maxPathSumFromLeftTree = Math.max(maxPathSumFromLeftTree, 0);
        maxPathSumFromRightTree = Math.max(maxPathSumFromRightTree, 0);

        // maximum path sum at the current node will be equal to the sum from the left 
        // subtree + the sum from right subtree + val of current node
        int localMaxPathSum = maxPathSumFromLeftTree + maxPathSumFromRightTree + currentNode.val;

        // update the global maximum sum
        globalMaxPathSum = Math.max(globalMaxPathSum, localMaxPathSum);

        // maximum sum of any path from the current node will be equal to the maximum of 
        // the sums from left or right subtrees plus the value of the current node
        return Math.max(maxPathSumFromLeftTree, maxPathSumFromRightTree) + currentNode.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println("Maximum Path Sum: " + BTMaxPathSum.findMaximumPathSum(root));

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        System.out.println("Maximum Path Sum: " + BTMaxPathSum.findMaximumPathSum(root));

        root = new TreeNode(-1);
        root.left = new TreeNode(-3);
        System.out.println("Maximum Path Sum: " + BTMaxPathSum.findMaximumPathSum(root));
    }
}