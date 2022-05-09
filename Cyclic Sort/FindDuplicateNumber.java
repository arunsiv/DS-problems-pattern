public class FindDuplicateNumber {
    //
    public static int findNumber(int[] nums) {
        // base checks
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i + 1) {
                if (nums[i] != nums[nums[i] - 1]) {
                    swap(nums, i, nums[i] - 1);
                } else {
                    return nums[i];
                }
            } else {
                i++;
            }
        }

        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int findNumberTwoPointers(int[] nums) {
        int slow = 0, fast = 0;
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);
        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(FindDuplicateNumber.findNumber(new int[] { 1, 4, 4, 3, 2 }));
        System.out.println(FindDuplicateNumber.findNumber(new int[] { 2, 1, 3, 3, 5, 4 }));
        System.out.println(FindDuplicateNumber.findNumber(new int[] { 2, 4, 1, 4, 4 }));

        System.out.println(FindDuplicateNumber.findNumberTwoPointers(new int[] { 1, 4, 4, 3, 2 }));
        System.out.println(FindDuplicateNumber.findNumberTwoPointers(new int[] { 2, 1, 3, 3, 5, 4 }));
        System.out.println(FindDuplicateNumber.findNumberTwoPointers(new int[] { 2, 4, 1, 4, 4 }));
    }
}