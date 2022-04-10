import java.util.Arrays;

public class ThreeSumWithSmallerSum {
    // O(N^2) time | O(N) space
    // where N is the length of the input array
    public static int searchTriplets(int[] nums, int sum) {
        // base checks
        if (nums == null || nums.length < 3) {
            return 0;
        }

        // sort the array
        Arrays.sort(nums);

        int n = nums.length, count = 0;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int target = nums[i] + nums[left] + nums[right];
                System.out.println(i + ":" + left + ":" + right);
                System.out.println(nums[i] + ":" + nums[left] + ":" + nums[right] + ":::" + target + "<" + sum);
                if (target >= sum) {
                    right--;
                } else {
                    //
                    count += (right - left);
                    System.out.println("----->" + count);
                    left++;
                }
            }
        }
        System.out.println("----------");
        return count;
    }

    public static void main(String[] args) {
        // System.out.println(
        // ThreeSumWithSmallerSum.searchTriplets(new int[] { -1, 0, 2, 3 }, 3));
        System.out.println(
                ThreeSumWithSmallerSum.searchTriplets(new int[] { -1, 4, 2, 1, 3 }, 5));
        // System.out.println(
        // ThreeSumWithSmallerSum.searchTriplets(new int[] { -1, 4, 2, 1, 3 }, -50));
    }
}