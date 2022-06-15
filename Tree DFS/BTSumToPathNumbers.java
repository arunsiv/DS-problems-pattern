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

//LC#129
public class BTSumToPathNumbers {
    // O(N) time | O(N) space
    // where N is the total number of nodes in the tree
    // space: O(N) for recurssive stack
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

    // O(n) time | O(n) space
    // where n is the total number of nodes in the tree
    // space: O(N) for result & O(N) for stack => O(N)
    public static int findSumOfPathNumbersDFS(TreeNode root) {
        //base check
        if (root == null) {
            return 0;
        }

        Stack<TreeNode> nodes = new Stack<>();
        Stack<Integer> pathSum = new Stack<>();
        int totalSum = 0;
        int levelSize = 0;

        nodes.add(root);
        pathSum.add(0);

        while (!nodes.isEmpty()) {
            levelSize = nodes.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = nodes.pop();
                int sum = pathSum.pop();
    
                // calculate the path number of the current node
                sum = sum * 10 + currentNode.val;

                // if the current node is a leaf, add the current path sum
                if (currentNode.left == null && currentNode.right == null) {
                    totalSum += sum;
                }

                // traverse the left and the right sub-tree
                if (currentNode.left != null) {
                    nodes.add(currentNode.left);
                    pathSum.add(sum);
                }

                if (currentNode.right != null) {
                    nodes.add(currentNode.right);
                    pathSum.add(sum);
                }
            }
        }

        return totalSum;
    }

    // O(n) time | O(n) space
    // where n is the total number of nodes in the tree
    // space: O(N) for result & O(N) for queue => O(N)
    public static int findSumOfPathNumbersBFS(TreeNode root) {
        //base check
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> pathSum = new LinkedList<>();
        int totalSum = 0;
        int levelSize = 0;

        nodes.offer(root);
        pathSum.offer(0);

        while (!nodes.isEmpty()) {
            levelSize = nodes.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = nodes.poll();
                int sum = pathSum.poll();
    
                // calculate the path number of the current node
                sum = sum * 10 + currentNode.val;

                // if the current node is a leaf, add the current path sum
                if (currentNode.left == null && currentNode.right == null) {
                    totalSum += sum;
                }

                // traverse the left and the right sub-tree
                if (currentNode.left != null) {
                    nodes.offer(currentNode.left);
                    pathSum.offer(sum);
                }

                if (currentNode.right != null) {
                    nodes.offer(currentNode.right);
                    pathSum.offer(sum);
                }
            }
        }

        return totalSum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        System.out.println("Total Sum of Path Numbers: " + BTSumToPathNumbers.findSumOfPathNumbers(root));
        System.out.println("Total Sum of Path Numbers: " + BTSumToPathNumbers.findSumOfPathNumbersBFS(root));
        System.out.println("Total Sum of Path Numbers: " + BTSumToPathNumbers.findSumOfPathNumbersDFS(root));
    }
}