public class FindMissingNumber {
    // O(n) time | O(1) space
    // where n is the length of the array
    // time: O(n)+O(n−1)+O(n) i.e. O(n) for while loop, O(n-1) for swapping and O(n)
    // for for loop
    public static int findMissingNumber(int[] arr) {
        int i = 0;

        while (i < arr.length) {
            if (arr[i] < arr.length && arr[i] != arr[arr[i]]) {
                swap(arr, i, arr[i]);
            } else {
                i++;
            }
        }

        for (i = 0; i < arr.length; i++) {
            if (arr[i] != i) {
                return i;
            }
        }

        return arr.length;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // O(n) time | O(1) space
    // where n is the length of the array
    public static int findMissingNumberAlternative(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int s = (n * (n + 1) / 2);
        return (s - sum);
    }

    public static void main(String[] args) {
        System.out.println(FindMissingNumber.findMissingNumber(new int[] { 4, 0, 3, 1 }));
        System.out.println(FindMissingNumber.findMissingNumber(
                new int[] { 7, 3, 5, 2, 4, 6, 0, 1 }));
        System.out.println(FindMissingNumber.findMissingNumberAlternative(new int[] { 4, 0, 3, 1 }));
        System.out.println(FindMissingNumber.findMissingNumberAlternative(
                new int[] { 7, 3, 5, 2, 4, 6, 0, 1 }));
    }
}