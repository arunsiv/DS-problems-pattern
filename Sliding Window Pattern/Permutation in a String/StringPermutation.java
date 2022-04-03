import java.util.HashMap;

public class StringPermutation {
    //
    public static boolean findPermutation(String str, String pattern) {
        // base checks
        if (str.length() == 0 || pattern.length() == 0 || str.length() < pattern.length()) {
            return false;
        }

        HashMap<Character, Integer> charFrequencyMap = new HashMap<>();
        int windowStart = 0, matched = 0;

        // add pattern chars to hash map
        for (char c : pattern.toCharArray()) {
            charFrequencyMap.put(c, charFrequencyMap.getOrDefault(c, 0) + 1);
        }

        // iterate throught the string and check if each char in the string
        // is in the pattern
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            // check if the char is in the pattern, remove the count
            // if the count is zero, there is a char match
            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);

                // char match found
                if (charFrequencyMap.get(rightChar) == 0) {
                    matched++;
                }
            }

            // if matched is equal to the pattern length, match found
            if (matched == charFrequencyMap.size()) {
                System.out.println("matched: " + matched);
                return true;
            }

            // if window size is greater than the pattern length, shrink the window
            if (windowEnd >= pattern.length() - 1) {
                char leftChar = str.charAt(windowStart);
                windowStart++;

                // if the char being removed is in the pattern, put it back
                // Also, if the count is already zero, decrement matched
                if (charFrequencyMap.containsKey(leftChar)) {

                    // decrement the matched count
                    if (charFrequencyMap.get(leftChar) == 0) {
                        matched--;
                    }

                    // put the char back
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }

        System.out.println("matched: " + matched);
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Permutation exist: "
                + StringPermutation.findPermutation("oidbcaf", "abc"));
        System.out.println("Permutation exist: "
                + StringPermutation.findPermutation("odicf", "dc"));
        System.out.println("Permutation exist: "
                + StringPermutation.findPermutation("bcdxabcdy", "bcdyabcdx"));
        System.out.println("Permutation exist: "
                + StringPermutation.findPermutation("aaacb", "abc"));
    }
}