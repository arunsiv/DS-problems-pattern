import java.util.HashMap;

public class PairTargetSum {
    // O(N) time | O(1) space
    // where N is the length of the input array
    public static int[] searchWithTwoPointers(int[] nums, int sum) {
        // base checks
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }

        int[] result = new int[2];
        int leftPointer = 0, rightPointer = nums.length - 1, targetSum = 0;

        while (leftPointer < rightPointer) {
            targetSum = nums[leftPointer] + nums[rightPointer];

            if (targetSum == sum) {
                result[0] = leftPointer;
                result[1] = rightPointer;
                break;
            } else if (targetSum > sum) {
                rightPointer--;
            } else {
                leftPointer++;
            }
        }

        return result;
    }

    // O(N) time | O(N) space
    // where N is the length of the input array
    public static int[] searchWithHashMap(int[] nums, int sum) {
        // base checks
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }

        HashMap<Integer, Integer> arrayMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++ ) {
            if (arrayMap.containsKey(sum - nums[i])) {
                return new int[] {arrayMap.get(sum-nums[i]), i};
            } else {
                arrayMap.put(nums[i], i);
            }
        }


        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int[] result = PairTargetSum.searchWithTwoPointers(new int[] { 1, 2, 3, 4, 6 }, 6);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        result = PairTargetSum.searchWithTwoPointers(new int[] { 2, 5, 9, 11 }, 11);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        result = PairTargetSum.searchWithTwoPointers(new int[] { -2, -3, 5, 9, 11 }, -5);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");

        result = PairTargetSum.searchWithHashMap(new int[] { 1, 2, 3, 4, 6 }, 6);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        result = PairTargetSum.searchWithHashMap(new int[] { 2, 5, 9, 11 }, 11);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        result = PairTargetSum.searchWithHashMap(new int[] { -2, -3, 5, 9, 11 }, -5);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
    }
}