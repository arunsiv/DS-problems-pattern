import java.util.Arrays;

public class ThreeSumCloseToTarget {
    //O(N^2) time | O(N) space
    //where N is the length of the input array
    public static int searchTriplet(int[] nums, int target) {
        // base checks
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException();
        }

        //sort the array
        Arrays.sort(nums);

        int n = nums.length;
        int resultSum = nums[0] + nums[1] + nums[n - 1];

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            while (left < right) {
                int targetSum = nums[i] + nums[left] + nums[right];

                if (Math.abs(targetSum - target) < Math.abs(resultSum - target)) {
                    resultSum = targetSum;
                }

                if (targetSum == target) {
                    return targetSum;
                } else if (targetSum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return resultSum;
    }

    public static void main(String[] args) {
        System.out.println(
                ThreeSumCloseToTarget.searchTriplet(new int[] { -2, 0, 1, 2 }, 2));
        System.out.println(
                ThreeSumCloseToTarget.searchTriplet(new int[] { -3, -1, 1, 2 }, 1));
        System.out.println(
                ThreeSumCloseToTarget.searchTriplet(new int[] { 1, 0, 1, 1 }, 100));
        System.out.println(
                ThreeSumCloseToTarget.searchTriplet(new int[] { 100, 0, 100, 200 }, 100));
    }
}