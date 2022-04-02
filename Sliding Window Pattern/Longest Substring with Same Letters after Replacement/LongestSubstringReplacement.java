import java.util.HashMap;

public class LongestSubstringReplacement {
    //O(N) time | O(1) space
    //where N is the length of the string
    public static int findLength(String str, int K) {
        // base checks
        if (str == null || str.length() == 0 || str.length() < K) {
            throw new IllegalArgumentException();
        }

        HashMap<Character, Integer> charFrequencyMap = new HashMap<>();
        int windowStart = 0, maxLength = 0, maxRepeatedCharCount = 0;

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char nextChar = str.charAt(windowEnd);
            charFrequencyMap.put(nextChar, charFrequencyMap.getOrDefault(nextChar, 0) + 1);
            maxRepeatedCharCount = Math.max(maxRepeatedCharCount, charFrequencyMap.get(nextChar));

            // current window size is from windowStart to windowEnd, overall we have a letter 
            // which is repeating 'maxRepeatLetterCount' times, this means we can have a window
            //  which has one letter repeating 'maxRepeatLetterCount' times and the remaining 
            // letters we should replace. If the remaining letters are more than 'k', it is the
            // time to shrink the window as we are not allowed to replace more than 'k' letters
            if ((((windowEnd - windowStart) + 1) - maxRepeatedCharCount) > K) {
                char leftChar = str.charAt(windowStart);
                charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
                windowStart++;
            }

            maxLength = Math.max(maxLength, (windowEnd - windowStart) + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(LongestSubstringReplacement.findLength("aabccbb", 2));
        System.out.println(LongestSubstringReplacement.findLength("abbcb", 1));
        System.out.println(LongestSubstringReplacement.findLength("abccde", 1));
        System.out.println(LongestSubstringReplacement.findLength("aaaaaq", 1));
    }
}