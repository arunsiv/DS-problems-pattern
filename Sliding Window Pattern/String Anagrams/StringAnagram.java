import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StringAnagram {
    // O(N+M) time | O(M) space
    // where N is the length of the string and M is the length of the pattern
    public static List<Integer> findStringAnagrams(String str, String pattern) {
        // base checks
        int n = str.length();
        int m = pattern.length();
        if (n == 0 || m == 0 || n < m) {
            throw new IllegalArgumentException();
        }

        HashMap<Character, Integer> charFrequencyMap = new HashMap<>();
        List<Integer> indexes = new ArrayList<Integer>();
        int windowStart = 0, matched = 0;

        for (char c : pattern.toCharArray()) {
            charFrequencyMap.put(c, charFrequencyMap.getOrDefault(c, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < n; windowEnd++) {
            char rightChar = str.charAt(windowEnd);

            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);

                if (charFrequencyMap.get(rightChar) == 0) {
                    matched++;
                }
            }

            // Anagram match found
            if (matched == charFrequencyMap.size()) {
                // add the starting index of the anagram to the list
                indexes.add(windowStart);
            }

            if (windowEnd >= m - 1) {
                char leftChar = str.charAt(windowStart);
                windowStart++;

                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap.get(leftChar) == 0) {
                        matched--;
                    }

                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }

        return indexes;
    }

    // O(N) time | O(1) space
    // where N is the length of the string
    public static List<Integer> findStringAnagramsOptimized(String str, String pattern) {
        // base checks
        if (str.length() == 0 || pattern.length() == 0 || str.length() < pattern.length()) {
            throw new IllegalArgumentException();
        }

        int[] charFrequencyMap = new int[26];
        List<Integer> indexes = new ArrayList<Integer>();
        int matched = pattern.length(), windowStart = 0, windowEnd = 0;

        // add pattern chars to array
        for (char c : pattern.toCharArray()) {
            charFrequencyMap[c - 'a']++;
        }

        // iterate through the string and check if each char in the string
        // is in the pattern
        while (windowEnd < str.length()) {
            // check if the char count is 0, char match is found
            if (charFrequencyMap[str.charAt(windowEnd) - 'a'] > 0) {
                matched--;
            }

            charFrequencyMap[str.charAt(windowEnd) - 'a']--;
            windowEnd++;

            // match found
            if (matched == 0) {
                indexes.add(windowStart);
            }

            // increase the window size until it matches pattern length
            // and then remove the left char
            // if the left char is in the pattern, add it back in the array and increase the
            // matched
            if (windowEnd - windowStart == pattern.length()) {
                if (charFrequencyMap[str.charAt(windowStart) - 'a'] >= 0) {
                    matched++;
                }

                charFrequencyMap[str.charAt(windowStart) - 'a']++;
                windowStart++;
            }
        }

        return indexes;
    }

    public static void main(String[] args) {
        System.out.println(StringAnagram.findStringAnagrams("ppqp", "pq"));
        System.out.println(StringAnagram.findStringAnagrams("abbcabc", "abc"));
        System.out.println(StringAnagram.findStringAnagrams("abbabaab", "ab"));

        System.out.println(StringAnagram.findStringAnagramsOptimized("ppqp", "pq"));
        System.out.println(StringAnagram.findStringAnagramsOptimized("abbcabc", "abc"));
        System.out.println(StringAnagram.findStringAnagramsOptimized("abbabaab", "ab"));
    }
}