import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicateNumbers {
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

        for (int num : nums) {
            System.out.print(num + "\t");
        }
        System.out.println();

        List<Integer> duplicateNumbers = new ArrayList<>();
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                duplicateNumbers.add(nums[i]);
            }
        }

        return duplicateNumbers;    
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        List<Integer> duplicates = FindAllDuplicateNumbers.findNumbers(new int[] { 3, 4, 4, 5, 5 });
        System.out.println("Duplicates are: " + duplicates);

        duplicates = FindAllDuplicateNumbers.findNumbers(new int[] { 5, 4, 7, 2, 3, 5, 3 });
        System.out.println("Duplicates are: " + duplicates);
    }
}