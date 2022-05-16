public class CorruptPair {
    // O(n) time | O(1) space
    // where n is the length of the array
    // time: O(n)+O(n−1)+O(n) i.e. O(n) for while loop, O(n-1) for swapping and O(n)
    // for for loop    
    public static int[] findNumbers(int[] nums) {
        // base checks
        if (nums == null || nums.length == 0) {
            return new int[] { -1, -1 };
        }

        int i = 0, j = 0;
        while (i < nums.length) {
            j = nums[i] - 1;
            if (nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[] { nums[i], i + 1 };
            }
        }

        return new int[] { -1, -1 };
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = CorruptPair.findNumbers(new int[] { 3, 1, 2, 5, 2 });
        System.out.println(nums[0] + ", " + nums[1]);
        nums = CorruptPair.findNumbers(new int[] { 3, 1, 2, 3, 6, 4 });
        System.out.println(nums[0] + ", " + nums[1]);
        nums = CorruptPair.findNumbers(new int[] { 4, 3, 2, 1 });
        System.out.println(nums[0] + ", " + nums[1]);
        nums = CorruptPair.findNumbers(new int[] { });
        System.out.println(nums[0] + ", " + nums[1]);
    }
}