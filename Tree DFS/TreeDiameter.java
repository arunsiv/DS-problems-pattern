class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

//LC# 543
public class TreeDiameter {
    private static int treeDiameter = 0;

    // O(n) time | O(n) space
    // where n is the total number of nodes in the tree
    // space: O(n) for recurssive stack
    public static int findDiameter(TreeNode root) {
        calculateDiameter(root);
        return treeDiameter;
    }

    private static int calculateDiameter(TreeNode currentNode) {
        // base checks
        if (currentNode == null) {
            return 0;
        }

        // get the height for left subtree
        int leftTreeHeight = calculateDiameter(currentNode.left);

        // get the height for right subtree
        int rightTreeHeight = calculateDiameter(currentNode.right);

        // if the current node doesn't have a left or right subtree, we can't have
        // a path passing through it, since we need a leaf node on each side
        if (leftTreeHeight != 0 && rightTreeHeight != 0) {
            // diameter at the current node will be equal to the height of left subtree +
            // the height of right sub-trees + '1' for the current node
            int diameter = leftTreeHeight + rightTreeHeight + 1;

            // update the global tree diameter
            treeDiameter = Math.max(treeDiameter, diameter);
        }

        // height of the current node will be equal to the maximum of the heights of
        // left or right subtrees plus '1' for the current node
        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
        root.left.left = null;
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);
        System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
    }
}