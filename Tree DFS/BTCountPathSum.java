import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

public class BTCountPathSum {
    // O(n^2) time | O(n) space
    // where n is the total number of nodes in the tree
    // time: O(n) for recursive stack & O(n) for the root-to-leaf path
    // space: O(n) for all paths & O(n) for call stack => O(n)
    public static int countPaths(TreeNode root, int targetSum) {
        List<Integer> currentPath = new LinkedList<>();
        return countPathsRecursive(root, targetSum, currentPath);
    }

    private static int countPathsRecursive(TreeNode currentNode, int targetSum, List<Integer> currentPath) {
        // base checks
        if (currentNode == null) {
            return 0;
        }

        int pathSum = 0, pathCount = 0;

        // add the current node to the current path
        currentPath.add(currentNode.val);

        // Iterate through each node in the current path
        ListIterator<Integer> currenPathIterator = currentPath.listIterator(currentPath.size());
        while (currenPathIterator.hasPrevious()) {
            pathSum += currenPathIterator.previous();

            // increase the count if the pathsum is equal to the target sum
            if (pathSum == targetSum) {
                pathCount++;
            }
        }

        // traverse the left sub-tree
        pathCount += countPathsRecursive(currentNode.left, targetSum, currentPath);

        // traverse the right sub-tree
        pathCount += countPathsRecursive(currentNode.right, targetSum, currentPath);

        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);

        // return the count
        return pathCount;
    }

    public static int countPathsMemoization(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0,1);
        return countPathsMemoizationRecursive(root, 0, sum, preSum);
    }
    
    private static int countPathsMemoizationRecursive(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        
        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        
        res += countPathsMemoizationRecursive(root.left, currSum, target, preSum) + countPathsMemoizationRecursive(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        System.out.println("Tree has path: " + BTCountPathSum.countPaths(root, 11));
        System.out.println("Tree has path: " + BTCountPathSum.countPathsMemoization(root, 11));
    }
}