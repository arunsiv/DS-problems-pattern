import java.util.Arrays;

public class AvgSubArrays {

    //O(N) Time | O(K?) space
    //where N is the length of the input string
    public static double[] AverageOfSubarrayOfSizeK(int K, int[] nums) {
        double[] result = new double[nums.length - K + 1];
        double windowSum = 0;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            windowSum += nums[windowEnd];

            if (windowEnd >= K - 1) {
                result[windowStart] = windowSum / K;
                windowSum -= nums[windowStart];
                windowStart++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        double[] result = AvgSubArrays.AverageOfSubarrayOfSizeK(5, new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 });
        System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
    }
}