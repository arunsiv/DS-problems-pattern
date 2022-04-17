public class HappyNumber {
    //O(logN) time | O(1) space
    public static boolean find(int number) {
        int slowPointer = number;
        int fastPointer = number;

        do {
            slowPointer = findSquareSum(slowPointer);
            fastPointer = findSquareSum(findSquareSum(fastPointer));
        } while (slowPointer != fastPointer);

        return (slowPointer == 1);
    }

    private static int findSquareSum(int number) {
        int sum = 0;
        int digit = 0;

        while (number > 0) {
            digit = number % 10;
            sum += digit * digit;
            number /= 10;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(HappyNumber.find(23));
        System.out.println(HappyNumber.find(12));
    }
}