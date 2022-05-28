import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
};

public class LevelOrderSuccessor {
    // O(N) time | O(N) space
    // N is the number of nodes in the tree
    // space: O(N) for result & O(N) for queue => O(N)    
    public static TreeNode findSuccessor(TreeNode root, int key) {
        //base checks
        if (root == null) {
            return null;
        }

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);

        while (!nodes.isEmpty()) {
            TreeNode currentNode = nodes.poll();

            // insert the children of current node in the queue
            if (currentNode.left != null) {
                nodes.offer(currentNode.left);
            }

            if (currentNode.right != null) {
                nodes.offer(currentNode.right);
            }

            // break if we have found the key
            if (currentNode.val == key) {
                break;
            }
        }

        //return the next node
        return nodes.peek();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        TreeNode result = LevelOrderSuccessor.findSuccessor(root, 3);
        if (result != null)
          System.out.println(result.val + " ");
    
        root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
    
        result = LevelOrderSuccessor.findSuccessor(root, 9);
        if (result != null)
          System.out.println(result.val + " ");
    
        result = LevelOrderSuccessor.findSuccessor(root, 12);
        if (result != null)
          System.out.println(result.val + " ");
      }
}