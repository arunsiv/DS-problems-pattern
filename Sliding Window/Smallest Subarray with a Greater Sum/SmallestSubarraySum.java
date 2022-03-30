public class SmallestSubarraySum {
    //O(N) | O(1) space
    //Time: O(N+N) => O(N)
    public static int findMinSubArray(int S, int[] nums) {
        int windowStart = 0, windowSum = 0, minLength = Integer.MAX_VALUE;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            windowSum += nums[windowEnd];

            while (windowSum >= S) {
                minLength = Math.min(minLength, (windowEnd - windowStart + 1));
                windowSum -= nums[windowStart];
                windowStart++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int result = SmallestSubarraySum.findMinSubArray(7, new int[] { 2, 1, 5, 2, 3, 2 });
        System.out.println("Smallest subarray length: " + result);
        result = SmallestSubarraySum.findMinSubArray(7, new int[] { 2, 1, 5, 2, 8 });
        System.out.println("Smallest subarray length: " + result);
        result = SmallestSubarraySum.findMinSubArray(8, new int[] { 3, 4, 1, 1, 6 });
        System.out.println("Smallest subarray length: " + result);
        result = SmallestSubarraySum.findMinSubArray(100, new int[] { 3, 4, 1, 1, 6 });
        System.out.println("Smallest subarray length: " + result);
    }
}