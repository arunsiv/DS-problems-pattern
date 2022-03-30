public class MaxSum {
    //O(N*K) time | O(1) space
    public static int findMaxSumSubArrayBruteForce(int k, int[] arr) {
        int maxSum = 0, windowSum;
        for (int i = 0; i <= arr.length - k; i++) {
            windowSum = 0;
            for (int j = i; j < i + k; j++) {
                windowSum += arr[j];
            }
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    // O(N) time | O(1) space
    public static int findMaxSumSubArray(int K, int[] nums) {
        int maxSum = 0, windowSum = 0, windowStart = 0;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            windowSum += nums[windowEnd];

            if (windowEnd >= K - 1) {
                maxSum = Math.max(windowSum, maxSum);
                windowSum -= nums[windowStart];
                windowStart++;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println("Maximum sum of a subarray of size K: "
                + MaxSum.findMaxSumSubArrayBruteForce(3, new int[] { 2, 1, 5, 1, 3, 2 }));
        System.out.println("Maximum sum of a subarray of size K: "
                + MaxSum.findMaxSumSubArrayBruteForce(2, new int[] { 2, 3, 4, 1, 5 }));

        System.out.println("Maximum sum of a subarray of size K: "
                + MaxSum.findMaxSumSubArray(3, new int[] { 2, 1, 5, 1, 3, 2 }));
        System.out.println("Maximum sum of a subarray of size K: "
                + MaxSum.findMaxSumSubArray(2, new int[] { 2, 3, 4, 1, 5 }));
    }
}