public class MinimumWindowSort {
    //O(N) time | O(1) space
    //where N is the number of elements in the array
    public static int findUnsortedSubarrayAlternativeSolution(int[] nums) {
        int len = nums.length;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int start = -1, end = -1;

        for (int i = 0; i < len; i++) {
            System.out.println(max + ":" + min + ":" + end + ":" + start);

            max = Math.max(max, nums[i]); // from left to right, search the current max
            min = Math.min(min, nums[len - i - 1]); // from right to left, search the current min

            if (nums[i] < max) {
                end = i;
            }

            if (nums[len - i - 1] > min) {
                start = len - i - 1;
            }

            System.out.println(max + ":" + min + ":" + end + ":" + start);
            System.out.println("-----");
        }

        if (start == -1) {
            // the entire array is already sorted
            return 0;
        }

        System.out.println(end + "::::" + start);

        return end - start + 1;
    }

    //O(N) time | O(1) space
    //where N is the number of elements in the array
    public static int findUnsortedSubarray(int[] nums) {
        // base checks
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }

        int low = 0, high = nums.length - 1;

        //left -> right, find the first number out of sorting order
        while (low < nums.length - 1 && nums[low] <= nums[low + 1]) {
            low++;
        }

        // array is already sorted
        if (low == nums.length - 1) {
            return 0;
        }

        //right -> left, find the first number out of sorting order
        while (high > 0 && nums[high] >= nums[high - 1]) {
            high--;
        }

        System.out.println("Candidate subarray indexes are " + low + " - " + high);

        // find the min and max elements in the subarray
        int subarrayMin = Integer.MAX_VALUE, subarrayMax = Integer.MIN_VALUE;
        for (int k = low; k <= high; k++) {
            subarrayMin = Math.min(subarrayMin, nums[k]);
            subarrayMax = Math.max(subarrayMax, nums[k]);
        }

        System.out.println("Candidate subarray min is : " + subarrayMin);
        System.out.println("Candidate subarray max is : " + subarrayMax);

        // extend the subarray from low to the begining of the array
        // to include any number which is bigger than the minimum of the subarray
        while (low > 0 && nums[low - 1] > subarrayMin) {
            low--;
        }

        // extend the subarray from high to the end of the array
        // to include any number which is smaller than the maximum of the subarray
        while (high < nums.length - 1 && nums[high + 1] < subarrayMax) {
            high++;
        }

        System.out.println("Subarray indexes are " + low + " - " + high);

        return high - low + 1;
    }

    public static void main(String[] args) {
        System.out.println(MinimumWindowSort.findUnsortedSubarray(new int[] { 1, 2, 5, 3, 7, 10, 9, 12 }));
        System.out.println(MinimumWindowSort.findUnsortedSubarray(new int[] { 1, 3, 2, 0, -1, 7, 10 }));
        System.out.println(MinimumWindowSort.findUnsortedSubarray(new int[] { 1, 2, 3}));
        System.out.println(MinimumWindowSort.findUnsortedSubarray(new int[] { 22, 6, 4, 18, 16, 13, 15 }));
        //System.out.println(MinimumWindowSort.findUnsortedSubarrayAlternativeSolution(new int[] { 2,6,4,8,10,9,15 }));
    }
}