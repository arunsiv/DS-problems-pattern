public class PositiveNumber {
    // O(n) time | O(1) space
    // where n is the length of the array
    // time: O(n)+O(n−1)+O(n) i.e. O(n) for while loop, O(n-1) for swapping and O(n)
    // for for loop
    public static int findNumber(int[] nums) {
        // base checks
        if (nums == null || nums.length == 0) {
            return -1;
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

        // for (int k : nums) {
        //     System.out.print(k + "\t");
        // }
        // System.out.println("");

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(
                PositiveNumber.findNumber(new int[] { -3, 1, 5, 4, 2 }));
        System.out.println(
                PositiveNumber.findNumber(new int[] { 3, -2, 0, 1, 2 }));
        System.out.println(
                PositiveNumber.findNumber(new int[] { 3, 2, 5, 1 }));
        System.out.println(
                PositiveNumber.findNumber(new int[] { 3, 2, 4, 1 }));
        System.out.println(
                PositiveNumber.findNumber(new int[] { }));
    }
}
