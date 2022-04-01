import java.util.HashMap;

public class NoRepeatSubstring {
    // O(N) time | O(K) space
    // N is the length of the string
    // K is the distinct chars in the string and K < N
    public static int findLength(String str) {
        // base checks
        if (str.length() == 0) {
            throw new IllegalArgumentException();
        }

        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0, windowStart = 0;

        // extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char nextChar = str.charAt(windowEnd);

            // the substrings must be distinct
            // if the char is already in the map, slide the window so there is only 1 occurance of the char
            if (charIndexMap.containsKey(nextChar)) {
                // this is tricky; in the current window, we will not have any 'rightChar' after 
                // its previous index and if 'windowStart' is already ahead of the last index of
                // 'rightChar', we'll keep 'windowStart'
                windowStart = Math.max(windowStart, charIndexMap.get(nextChar) + 1);
                //windowStart = charIndexMap.get(nextChar) + 1;
            }

            //update the char index
            charIndexMap.put(nextChar, windowEnd);
            
            //determine the max length so far
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        System.out.println(charIndexMap.toString());

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: "
                + NoRepeatSubstring.findLength("aabccbb"));
        System.out.println("Length of the longest substring: "
                + NoRepeatSubstring.findLength("abbbb"));
        System.out.println("Length of the longest substring: "
                + NoRepeatSubstring.findLength("abccdeccde"));
    }
}