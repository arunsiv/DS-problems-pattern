public class CyclicSort {
    // O(n) time | O(1) space
    // where n is the number of emelents in the array
    public static void sort(int[] arr) {
        int i = 0;

        while (i < arr.length) {
            int j = arr[i] - 1;
            if (arr[i] != arr[j]) {
                swap(arr, i, j);
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 3, 1, 5, 4, 2 };
        CyclicSort.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 2, 6, 4, 3, 1, 5 };
        CyclicSort.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[] { 1, 5, 6, 4, 3, 2 };
        CyclicSort.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }
}