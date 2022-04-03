import java.util.HashMap;

public class StringPermutation {
    // O(N*M) time | O(M) space
    // where N is the length of the string and M is the length of the pattern
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

        // iterate through the string and check if each char in the string
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

        return false;
    }

    // O(N) time | O(1) space
    // where N is the length of the string
    public static boolean findPermutationOptimized(String str, String pattern) {
        // base checks
        if (str.length() == 0 || pattern.length() == 0 || str.length() < pattern.length()) {
            return false;
        }

        int[] charFrequencyMap = new int[26];
        int matched = pattern.length(), windowStart = 0, windowEnd = 0;

        // add pattern chars to array
        for (char c : pattern.toCharArray()) {
            charFrequencyMap[c - 'a']++;
        }

        // iterate through the string and check if each char in the string
        // is in the pattern
        while (windowEnd < str.length()) {
            //check if the char count is 0, char match is found 
            if (charFrequencyMap[str.charAt(windowEnd++) - 'a']-- > 0) {
                matched--;
            }

            //match found
            if (matched == 0) {
                return true;
            }

            //increase the window size until it matches pattern length
            //and then remove the left char
            //if the left char is in the pattern, add it back in the array and increase the matched
            if (windowEnd - windowStart == pattern.length()
                    && charFrequencyMap[str.charAt(windowStart++) - 'a']++ >= 0) {
                        matched++;
            }
        }

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

        System.out.println("Permutation exist: "
                + StringPermutation.findPermutationOptimized("oidbcaf", "abc"));
        System.out.println("Permutation exist: "
                + StringPermutation.findPermutationOptimized("odicf", "dc"));
        System.out.println("Permutation exist: "
                + StringPermutation.findPermutationOptimized("bcdxabcdy", "bcdyabcdx"));
        System.out.println("Permutation exist: "
                + StringPermutation.findPermutationOptimized("aaacb", "abc"));
    }
}