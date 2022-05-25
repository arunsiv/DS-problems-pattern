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

//LC#637
public class LevelAverage {
    // O(N) time | O(N) space
    // N is the number of nodes in the tree
    // space: O(N) for result & O(N) for queue => O(N)
    public static List<Double> findLevelAverages(TreeNode root) {
        List<Double> result = new ArrayList<>();

        // base checks
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        // insert the root node in the queue
        queue.offer(root);

        double levelSum = 0;
        int levelSize = 0;

        while (!queue.isEmpty()) {
            levelSize = queue.size();
            levelSum = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // add values of all nodes in the current level
                levelSum += currentNode.val;

                // insert the children of current node in the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // add current level to the result
            result.add(levelSum/levelSize);
        }

        return result;
    }

    // O(N) time | O(N) space
    // N is the number of nodes in the tree
    // space: O(N) for result & O(N) for queue => O(N)
    public static List<Integer> findLargestValue(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // base checks
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        // insert the root node in the queue
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int maxValue = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // find the largest value at each level
                maxValue = Math.max(maxValue, currentNode.val);

                // insert the children of current node in the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // add current level to the result
            result.add(maxValue);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<Double> result = LevelAverage.findLevelAverages(root);
        System.out.println("Level averages are: " + result);

        root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<Integer> result1 = LevelAverage.findLargestValue(root);
        System.out.println("Level largest values are: " + result1);
    }
}