public class LongestSubarraywithOnes {
    // O(N) time | O(1) space
    // where N is the length of the array
    public static int findLength(int[] nums, int k) {
        // base checks
        if (nums.length == 0 || nums.length < k) {
            throw new IllegalArgumentException();
        }

        int windowStart = 0, maxLength = 0, maxRepeatedOnesCount = 0;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            if (nums[windowEnd] == 1) {
                maxRepeatedOnesCount++;
            }

            if (windowEnd - windowStart + 1 - maxRepeatedOnesCount > k) {
                if (nums[windowStart] == 1) {
                    maxRepeatedOnesCount--;
                }

                windowStart++;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(
                LongestSubarraywithOnes.findLength(new int[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 }, 2));
        System.out.println(
                LongestSubarraywithOnes.findLength(new int[] { 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1 }, 3));
        System.out.println(
            LongestSubarraywithOnes.findLength(new int[] { 0, 0, 0, 0, 1 }, 3));
    }
}