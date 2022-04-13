public class StringWithBackspace {
    // O(m+n) time | O(1) space
    // where m and n are length of string1 and string 2
    public static boolean compare(String str1, String str2) {
        // base checks
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return false;
        }

        int index1 = str1.length() - 1, index2 = str2.length() - 1;

        while (index1 >= 0 || index2 >= 0) {
            // get the index for s1
            int i1 = getValidStringIndex(str1, index1);

            // get the index for s2
            int i2 = getValidStringIndex(str2, index2);

            // reached the end of both the strings
            if (i1 < 0 && i2 < 0) {
                return true;
            }

            // reached the end of one of the strings
            if (i1 < 0 || i2 < 0) {
                return false;
            }

            // chars are not equal
            if (str1.charAt(i1) != str2.charAt(i2)) {
                return false;
            }

            // update the indexes
            index1 = i1 - 1;
            index2 = i2 - 1;
        }

        return true;
    }

    private static int getValidStringIndex(String s, int index) {
        int backspaceIndex = 0;

        while (index >= 0) {
            if (s.charAt(index) == '#') {
                backspaceIndex++; // backspace char
            } else if (backspaceIndex > 0) {
                backspaceIndex--; // not a backspace char
            } else {
                break;
            }

            index--; // skip the backspace or valid char
        }

        return index;
    }

    public static void main(String[] args) {
        System.out.println(StringWithBackspace.compare("xy#z", "xzz#"));
        System.out.println(StringWithBackspace.compare("xy#z", "xyz#"));
        System.out.println(StringWithBackspace.compare("#xp", "xyz##"));
        System.out.println(StringWithBackspace.compare("bbbextm", "bbb#extm"));
    }
}