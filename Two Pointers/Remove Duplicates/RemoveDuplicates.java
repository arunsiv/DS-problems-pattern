class RemoveDuplicates {
    // O(N) time | O(1) space
    // where N is the length of the array
    public static int remove(int[] arr) {
        int nextNonDuplicate = 1; // index of the next non-duplicate element
        for (int i = 0; i < arr.length; i++) {
            if (arr[nextNonDuplicate - 1] != arr[i]) {
                arr[nextNonDuplicate] = arr[i];
                nextNonDuplicate++;
            }
        }

        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();

        return nextNonDuplicate;
    }

    // O(N) time | O(1) space
    // where N is the length of the array
    public static int removeDuplicateKey(int[] arr, int key) {
        int nextElement = 0; // index of the next element that is not the key
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != key) {
                arr[nextElement] = arr[i];
                nextElement++;
            }
        }

        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();

        return nextElement;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        System.out.println(RemoveDuplicates.remove(arr));

        arr = new int[] { 2, 2, 2, 11 };
        System.out.println(RemoveDuplicates.remove(arr));

        arr = new int[] { -2, -1, -1, -1, 1, 2, 11 };
        System.out.println(RemoveDuplicates.remove(arr));

        arr = new int[] { 2, 3, 3, 3, 6, 9, 9 };
        System.out.println(RemoveDuplicates.removeDuplicateKey(arr, 3));

        arr = new int[] { 2, 2, 2, 11 };
        System.out.println(RemoveDuplicates.removeDuplicateKey(arr, 11));

        arr = new int[] { -2, -1, -1, -1, 1, 2, 11 };
        System.out.println(RemoveDuplicates.removeDuplicateKey(arr, -11));
    }
}