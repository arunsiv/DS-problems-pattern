public class DutchFlag {
    // O(N) time | O(1) space
    // where N is the length of the array
    public static int[] sort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[] {};
        }

        for (int i = 0, j = 0, k = 0; k < nums.length; k++) {
            // assign current element to temp
            int temp = nums[k];

            // assign 2
            nums[k] = 2;

            // if temp is < 2, assign 1 and increment j
            if (temp < 2) {
                nums[j] = 1;
                j++;
            }

            // if temp is < 1, assign 0 and increment i
            if (temp < 1) {
                nums[i] = 0;
                i++;
            }
        }

        return nums;
    }

    public static int[] sortWithSwap(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[] {};
        }

        int low = 0, high = nums.length - 1;

        for (int i = low; i <= high;) {
            if (nums[i] == 0) {
                swap(nums, i, low);
                i++;
                low++;
            } else if (nums[i] == 1) {
                // no swap is needed
                i++;
            } else {
                swap(nums, i, high);
                high--;
            }
        }

        return nums;
    }

    // O(N) time | O(1) space
    // where N is the length of the array
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 0, 2, 1, 0 };
        DutchFlag.sort(arr);
        for (int num : arr)
            System.out.print(num + "\t");
        System.out.println("");

        arr = new int[] { 2, 2, 0, 1, 2, 0 };
        DutchFlag.sort(arr);
        for (int num : arr)
            System.out.print(num + "\t");
        System.out.println("");

        arr = new int[] { 1, 0, 2, 1, 0 };
        DutchFlag.sortWithSwap(arr);
        for (int num : arr)
            System.out.print(num + "\t");
        System.out.println("");

        arr = new int[] { 2, 2, 0, 1, 2, 0 };
        DutchFlag.sortWithSwap(arr);
        for (int num : arr)
            System.out.print(num + "\t");
        System.out.println("");
    }
}