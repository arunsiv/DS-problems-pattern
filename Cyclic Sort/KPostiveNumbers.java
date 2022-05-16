import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KPostiveNumbers {
    // O(n+k) time | O(k) space
    // where n is the length of the input and k is the number of missing positive
    // numbers
    public static List<Integer> findNumbers(int[] nums, int k) {
        // base checks
        if (nums == null || nums.length == 0) {
            return null;
        }

        int i = 0, j = 0;
        while (i < nums.length) {
            j = nums[i] - 1;
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        // find k positive missing numbers
        List<Integer> missingNumbers = new ArrayList<>();
        for (i = 0; i < nums.length; i++) {
            if (k > 0 && nums[i] != i + 1) {
                missingNumbers.add(i + 1);
                k--;
            }
        }

        // printArr(missingNumbers);

        if (k > 0) {
            int n = nums.length + 1;
            while (k > 0) {
                if (!numExists(nums, n)) {
                    missingNumbers.add(n);
                    k--;
                }

                n++;
            }
        }

        return missingNumbers;
    }

    // O(n+k) time | O(k) space
    // where n is the length of the input and k is the number of missing positive
    // numbers
    public static List<Integer> findNumbersBetterSolution(int[] nums, int k) {
        // base checks
        if (nums == null || nums.length == 0) {
            return null;
        }

        int i = 0, j = 0;
        while (i < nums.length) {
            j = nums[i] - 1;
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        // to store the result
        List<Integer> missingNumbers = new ArrayList<>();

        // to store the numbers that are not in the correct index
        Set<Integer> extraNumbers = new HashSet<>();

        // find k positive missing numbers
        // 1. iterating throught the input array
        for (i = 0; i < nums.length && missingNumbers.size() < k; i++) {
            if (k > 0 && nums[i] != i + 1) {
                // add the missing positive numbers to the result
                missingNumbers.add(i + 1);

                // add the numbers that are not in the correct index
                extraNumbers.add(nums[i]);
            }
        }

        // 2. add the remaining missing 'k' numbers
        // note: the candidate number could be in the input
        for (i = 1; missingNumbers.size() < k; i++) {
            int candidateNumber = i + nums.length;

            // check if the candidate number is in the input
            // if so, ignore the candidate number
            if (!extraNumbers.contains(candidateNumber)) {
                missingNumbers.add(candidateNumber);
            }
        }

        return missingNumbers;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean numExists(int[] nums, int num) {
        for (int i : nums) {
            if (num == i) {
                return true;
            }
        }

        return false;
    }

    private static void printArr(List<Integer> nums) {
        System.out.println("-----");
        for (Integer num : nums) {
            System.out.print(num + "\t");
        }

        System.out.println("\n-----");
    }

    public static void main(String[] args) {
        List<Integer> missingNumbers = KPostiveNumbers.findNumbers(
                new int[] { 3, -1, 4, 5, 5 }, 3);
        System.out.println("Missing numbers: " + missingNumbers);

        missingNumbers = KPostiveNumbers.findNumbers(new int[] { 2, 3, 4 }, 3);
        System.out.println("Missing numbers: " + missingNumbers);

        missingNumbers = KPostiveNumbers.findNumbers(new int[] { -2, -3, 4 }, 2);
        System.out.println("Missing numbers: " + missingNumbers);

        missingNumbers = KPostiveNumbers.findNumbersBetterSolution(
                new int[] { 3, -1, 4, 5, 5 }, 3);
        System.out.println("Missing numbers: " + missingNumbers);

        missingNumbers = KPostiveNumbers.findNumbersBetterSolution(new int[] { 2, 3, 4 }, 3);
        System.out.println("Missing numbers: " + missingNumbers);

        missingNumbers = KPostiveNumbers.findNumbersBetterSolution(new int[] { -2, -3, 4 }, 2);
        System.out.println("Missing numbers: " + missingNumbers);
    }
}