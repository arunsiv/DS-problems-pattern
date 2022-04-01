import java.util.HashMap;

public class FruitBasket {
    // O(N) time | O(1) space
    // N is the length of the string
    public static int findLength(char[] tree) {
        // base checks
        if (tree.length == 0 || tree.length < 2) {
            throw new IllegalArgumentException();
        }

        HashMap<Character, Integer> fruitFrequencyMap = new HashMap<>();
        int windowStart = 0, maxLength = 0;

        for (int windowEnd = 0; windowEnd < tree.length; windowEnd++) {
            char nextTree = tree[windowEnd];

            // add next char to hash map and increase the char count
            fruitFrequencyMap.put(nextTree, fruitFrequencyMap.getOrDefault(nextTree, 0) + 1);

            while (fruitFrequencyMap.size() > 2) {
                char leftTree = tree[windowStart];

                // decrease the char count of the left most char
                fruitFrequencyMap.put(leftTree, fruitFrequencyMap.get(leftTree) - 1);

                // if count is zero, remove it from hashmap
                if (fruitFrequencyMap.get(leftTree) == 0) {
                    fruitFrequencyMap.remove(leftTree);
                }

                // slide the window
                windowStart++;
            }

            // window length = (windowEnd - windowStart) + 1
            maxLength = Math.max(maxLength, (windowEnd - windowStart) + 1);
        }

        System.out.println(fruitFrequencyMap.toString());

        return maxLength;
    }

    public static void main(String[] args) {
        System.out
                .println("Maximum number of fruits: " + FruitBasket.findLength(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        System.out.println(
                "Maximum number of fruits: " + FruitBasket.findLength(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));
    }
}