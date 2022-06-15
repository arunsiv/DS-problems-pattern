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

// LC #?
public class BTPathSequence {
    // O(N) time | O(N) space
    // where N is the total number of nodes in the tree
    // space: O(N) for recurssive stack
    public static boolean findPath(TreeNode root, int[] sequence) {
        return findPathRecursive(root, sequence, 0);
    }

    public static boolean findPathRecursive(TreeNode currentNode, int[] sequence, int sequenceIndex) {
        // base checks
        if (currentNode == null || sequence == null) {
            return false;
        }

        // check if the node value doesn't match the sequence, return false
        if (sequenceIndex >= sequence.length || currentNode.val != sequence[sequenceIndex]) {
            return false;
        }

        // if the current node is a leaf, add it is the end of the sequence, we have
        // found a path!
        if (currentNode.left == null && currentNode.right == null && sequenceIndex == sequence.length - 1) {
            return true;
        }

        // recursively call to traverse the left and right sub-tree
        // return true if any of the two recursive call return true
        return findPathRecursive(currentNode.left, sequence, (sequenceIndex + 1)) ||
                findPathRecursive(currentNode.right, sequence, (sequenceIndex + 1));
    }

    // O(n) time | O(n) space
    // where n is the total number of nodes in the tree
    // space: O(N) for result & O(N) for stack => O(N)
    public static boolean findPathDFS(TreeNode root, int[] sequence) {
        // base check
        if (root == null || sequence == null) {
            return false;
        }

        Stack<TreeNode> nodes = new Stack<>();
        int levelSize = 0;
        int sequenceIndex = 0;

        // add the root node
        nodes.add(root);

        while (!nodes.isEmpty()) {
            levelSize = nodes.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = nodes.pop();

                // check if the node value doesn't match the sequence
                if (sequenceIndex >= sequence.length || currentNode.val != sequence[sequenceIndex]) {
                    continue;
                }

                // if the current node is a leaf, add it is the end of the sequence, we have
                // found a path!
                if (currentNode.left == null && currentNode.right == null && sequenceIndex == sequence.length - 1) {
                    return true;
                }

                // traverse the left and the right sub-tree
                if (currentNode.left != null) {
                    nodes.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    nodes.add(currentNode.right);
                }
            }

            sequenceIndex++;
        }

        return false;
    }

    // O(n) time | O(n) space
    // where n is the total number of nodes in the tree
    // space: O(N) for result & O(N) for queue => O(N)
    public static boolean findPathBFS(TreeNode root, int[] sequence) {
        // base check
        if (root == null || sequence == null) {
            return false;
        }

        Queue<TreeNode> nodes = new LinkedList<>();
        int levelSize = 0;
        int sequenceIndex = 0;

        // add the root node
        nodes.offer(root);

        while (!nodes.isEmpty()) {
            levelSize = nodes.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = nodes.poll();

                // check if the node value doesn't match the sequence
                if (sequenceIndex >= sequence.length || currentNode.val != sequence[sequenceIndex]) {
                    continue;
                }

                // if the current node is a leaf, add it is the end of the sequence, we have
                // found a path!
                if (currentNode.left == null && currentNode.right == null && sequenceIndex == sequence.length - 1) {
                    return true;
                }

                // traverse the left and the right sub-tree
                if (currentNode.left != null) {
                    nodes.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    nodes.offer(currentNode.right);
                }
            }

            sequenceIndex++;
        }

        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        System.out.println("Path sequence: " + BTPathSequence.findPath(root, new int[] { 1, 0, 7 }));
        System.out.println("Path sequence: " + BTPathSequence.findPath(root, new int[] { 1, 1, 6 }));

        System.out.println("Path sequence: " + BTPathSequence.findPathDFS(root, new int[] { 1, 0, 7 }));
        System.out.println("Path sequence: " + BTPathSequence.findPathDFS(root, new int[] { 1, 1, 6 }));

        System.out.println("Path sequence: " + BTPathSequence.findPathBFS(root, new int[] { 1, 0, 7 }));
        System.out.println("Path sequence: " + BTPathSequence.findPathBFS(root, new int[] { 1, 1, 6 }));
    }
}