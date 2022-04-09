public class SquaringArray {
    // O(N) time | O(N) space
    // where N is the length of the input array
    public static int[] makeSquares(int[] nums) {
        // base checks
        if (nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int[] result = new int[nums.length];
        int leftPointer = 0;
        int rightPointer = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {

            int leftPointerValue = nums[leftPointer] * nums[leftPointer];
            int rightPointerValue = nums[rightPointer] * nums[rightPointer];

            if (leftPointerValue > rightPointerValue) {
                result[i] = leftPointerValue;
                leftPointer++;
            } else {
                result[i] = rightPointerValue;
                rightPointer--;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[] result = SquaringArray.makeSquares(new int[] { -2, -1, 0, 2, 3 });
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();

        result = SquaringArray.makeSquares(new int[] { -3, -1, 0, 1, 2 });
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();
    }
}