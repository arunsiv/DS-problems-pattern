import java.util.HashMap;

public class SmallWindowSubstring {
    //O(N+M) time | O(M) space
    public static String findSubString(String str, String pattern) {
        // base checks
        if (str.length() == 0 || pattern.length() == 0 || str.length() < pattern.length()) {
            return "";
        }

        HashMap<Character, Integer> patternMap = new HashMap<>();
        HashMap<Character, Integer> stringMap = new HashMap<>();

        // add the pattern chars to the pattern map along with char count
        for (char c : pattern.toCharArray()) {
            patternMap.put(c, patternMap.getOrDefault(c, 0) + 1);
        }

        int have = 0, need = patternMap.size();
        int windowStart = 0, subStrStart = -1, subStrEnd = -1, subStrLength = Integer.MAX_VALUE;

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            // add the right most chars to the map along with the char count
            char rightChar = str.charAt(windowEnd);
            stringMap.put(rightChar, stringMap.getOrDefault(rightChar, 0) + 1);

            // increament 'have' if the char is in the pattern
            // and if the char count is the same as the pattern
            if (patternMap.containsKey(rightChar) &&
                    patternMap.get(rightChar).intValue() == stringMap.get(rightChar).intValue()) {
                have++;
            }

            // 'have' and 'need' matches, which means there is a match
            // so, shrink the window to find the smallest substring
            while (have == need) {
                // update the substring data
                if (windowEnd - windowStart + 1 < subStrLength) {
                    subStrLength = windowEnd - windowStart + 1;
                    subStrStart = windowStart;
                    subStrEnd = windowEnd;
                }

                // Shrink the window by removing the char from the begining of the window
                char leftChar = str.charAt(windowStart);
                stringMap.put(leftChar, stringMap.get(leftChar) - 1);

                // decrement 'have' if the removed char is in the pattern
                // and if the char count is less than that of the pattern
                if (patternMap.containsKey(leftChar) &&
                        stringMap.get(leftChar).intValue() < patternMap.get(leftChar).intValue()) {
                    have--;
                }

                // shrink the window
                windowStart++;
            }
        }

        return subStrLength == Integer.MAX_VALUE ? "" : str.substring(subStrStart, subStrEnd + 1);
    }

    public static void main(String[] args) {
        System.out.println(SmallWindowSubstring.findSubString("aabdec", "abc"));
        System.out.println(SmallWindowSubstring.findSubString("aabdec", "abec"));
        System.out.println(SmallWindowSubstring.findSubString("abdbca", "abc"));
        System.out.println(SmallWindowSubstring.findSubString("adcad", "abc"));
        System.out.println(SmallWindowSubstring.findSubString("aaaabaa", "aa"));
    }
}