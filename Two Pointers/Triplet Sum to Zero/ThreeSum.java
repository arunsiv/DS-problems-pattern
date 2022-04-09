import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {
    // O(N^2) time | O(N) space (ignoring output)
    // where N is the length of the input array
    // time: O(NlogN) for array sorting and O(N^2) for finding the triplets
    // space: O(N) for sorting
    public static List<List<Integer>> searchTriplets(int[] nums) {
        // base checks
        if (nums.length < 3) {
            throw new IllegalArgumentException();
        }

        // sort the input array
        Arrays.sort(nums);

        int n = nums.length;
        List<List<Integer>> result = new LinkedList<>();

        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0) {
                break;
            }

            // check for duplicates and skip the iteration if there are duplicates
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int left = i + 1, right = n - 1, sum = 0 - nums[i];

                while (left < right) {
                    int targetSum = nums[left] + nums[right];

                    if (targetSum == sum) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        left++;
                        right--;

                        // check for duplicates
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }

                        // check for duplicates
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (targetSum > sum) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(ThreeSum.searchTriplets(
                new int[] { -3, 0, 1, 2, -1, 1, -2 }));
        System.out.println(ThreeSum.searchTriplets(new int[] { -5, 2, -1, -2, 3 }));
        System.out.println(ThreeSum.searchTriplets(new int[] { -1, 0, 1, 2, -1, -4 }));
    }
}