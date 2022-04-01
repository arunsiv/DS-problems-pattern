import java.util.HashMap;

public class LongestSubstringKDistinct {
    //
    public static int findLength(String str, int k) {
        // base checks
        if (str == null || str.length() == 0 || str.length() < k) {
            throw new IllegalArgumentException();
        }

        HashMap<Character, Integer> charFrequency = new HashMap<>();
        int windowStart = 0, maxLength = 0;

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char nextChar = str.charAt(windowEnd);

            //add next char to hash map and increase the char count
            charFrequency.put(nextChar, charFrequency.getOrDefault(nextChar, 0) + 1);

            while (charFrequency.size() > k) {
                char leftChar = str.charAt(windowStart);

                //decrease the char count of the left most char
                charFrequency.put(leftChar, charFrequency.get(leftChar) - 1);

                //if count is zero, remove it from hashmap
                if (charFrequency.get(leftChar) == 0) {
                    charFrequency.remove(leftChar);
                }

                //slide the window
                windowStart++;
            }

            //window length = (windowEnd - windowStart) + 1
            maxLength = Math.max(maxLength, (windowEnd - windowStart) + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: "
                + LongestSubstringKDistinct.findLength("araaci", 2));
        System.out.println("Length of the longest substring: "
                + LongestSubstringKDistinct.findLength("araaci", 1));
        System.out.println("Length of the longest substring: "
                + LongestSubstringKDistinct.findLength("cbbebi", 3));
        System.out.println("Length of the longest substring: "
                + LongestSubstringKDistinct.findLength("cbbebi", 13));
    }
}