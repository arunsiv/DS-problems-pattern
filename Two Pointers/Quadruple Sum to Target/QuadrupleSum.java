import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuadrupleSum {
    //O(N^3) time | O(N) space (ignoring output list)
    //time: O(NlogN) for sorting and O(N^3) for finding quadruplets. so asymptotically its O(N^3)
    //space: O(N) for sorting
    public static List<List<Integer>> searchQuadruplets(int[] nums, int targetSum) {
        // base checks
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        // sort the array
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int first = 0; first < nums.length - 3; first++) {
            // check for duplicates
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            for (int second = first + 1; second < nums.length - 2; second++) {
                // check for duplicates
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                // initialize two pointers
                int left = second + 1, right = nums.length - 1;

                while (left < right) {
                    // get the currentSum
                    int currentSum = nums[first] + nums[second] + nums[left] + nums[right];

                    // check if currentSum == targetSum
                    if (currentSum == targetSum) {
                        // add the quadruplets to the list
                        result.add(Arrays.asList(nums[first], nums[second], nums[left], nums[right]));

                        // increment & decrement the two pointers
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
                    } else if (currentSum < targetSum) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                QuadrupleSum.searchQuadruplets(new int[] { 4, 1, 2, -1, 1, -3 }, 1));
        System.out.println(
                QuadrupleSum.searchQuadruplets(new int[] { 2, 0, -1, 1, -2, 2 }, 2));
        System.out.println(
                QuadrupleSum.searchQuadruplets(new int[] { 4, 2, -4, -4, 5, -7 }, -2));
        System.out.println(
                QuadrupleSum.searchQuadruplets(new int[] { -2, -1, -1, 1, 1, 2, 2 }, 0));
    }
}