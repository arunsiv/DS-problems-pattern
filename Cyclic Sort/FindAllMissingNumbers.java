import java.util.ArrayList;
import java.util.List;

public class FindAllMissingNumbers {
    // O(n) time | O(1) space
    // where n is the length of the array
    // time: O(n)+O(n−1)+O(n) i.e. O(n) for while loop, O(n-1) for swapping and O(n)
    // for for loop
    public static List<Integer> findNumbers(int[] nums) {
        // base checks
        if (nums == null || nums.length == 0) {
            return null;
        }

        int i = 0;
        while (i < nums.length) {
            if (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }

        List<Integer> missingNumbers = new ArrayList<>();
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                missingNumbers.add(i + 1);
            }
        }

        return missingNumbers;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        List<Integer> missing = FindAllMissingNumbers.findNumbers(
                new int[] { 2, 3, 1, 8, 2, 3, 5, 1 });
        System.out.println("Missing numbers: " + missing);

        missing = FindAllMissingNumbers.findNumbers(new int[] { 2, 4, 1, 2 });
        System.out.println("Missing numbers: " + missing);

        missing = FindAllMissingNumbers.findNumbers(new int[] { 2, 3, 2, 1 });
        System.out.println("Missing numbers: " + missing);
    }
}