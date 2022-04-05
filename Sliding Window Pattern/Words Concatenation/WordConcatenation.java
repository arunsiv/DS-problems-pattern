import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordConcatenation {
    // This is the bruteforce approach
    // O(N*M*Len) time | O(N+M) space
    // N is the length of the string
    // M is the number of words (length of the words array)
    // Len is the length of each word
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        // base checks
        if (str.length() == 0 || words.length == 0) {
            throw new IllegalArgumentException();
        }

        //
        HashMap<String, Integer> wordFrequencyMap = new HashMap<>();
        for (String word : words) {
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        }

        List<Integer> indexes = new ArrayList<>();
        int wordCount = words.length, wordLength = words[0].length();
        int wordsLength = wordCount * wordLength;

        // System.out.println((str.length() - wordsLength) + "::::" + wordCount);
        //
        for (int leftIndex = 0; leftIndex <= str.length() - wordsLength; leftIndex++) {
            HashMap<String, Integer> wordsSeen = new HashMap<>();
            //
            for (int rightIndex = 0; rightIndex < wordCount; rightIndex++) {
                //
                int nextWordStartIndex = leftIndex + rightIndex * wordLength;
                int nextWordEndIndex = nextWordStartIndex + wordLength;
                // System.out.println(leftIndex + ":" + rightIndex + ":" + nextWordStartIndex);

                if (nextWordEndIndex > str.length()) {
                    break;
                }

                String nextWord = str.substring(nextWordStartIndex, nextWordEndIndex);
                // System.out.println("next word: " + nextWord);

                //
                if (!wordFrequencyMap.containsKey(nextWord)) {
                    break;
                }

                //
                wordsSeen.put(nextWord, wordsSeen.getOrDefault(nextWord, 0) + 1);
                // System.out.println("-=-=>" + wordsSeen.toString());

                //
                if (wordsSeen.get(nextWord).intValue() > wordFrequencyMap.get(nextWord).intValue()) {
                    break;
                }

                //
                if (rightIndex + 1 == wordCount) {
                    // System.out.println("----->" + wordsSeen.toString());
                    indexes.add(leftIndex);
                }

                // System.out.println("----->" + indexes.toString());
            }
        }

        return indexes;
    }

    public static List<Integer> findWordConcatenationVariant(String str, String[] words) {
        // base checks
        if (str == null || str.length() == 0 || words.length == 0) {
            throw new IllegalArgumentException();
        }

        // Add the words in the array and the frequency
        HashMap<String, Integer> wordFrequencyMap = new HashMap<>();
        for (String word : words) {
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        }

        // list to store the word indexed
        List<Integer> indexes = new ArrayList<>();
        // other variables
        int wordCount = words.length, wordLength = words[0].length();
        int wordsLength = wordCount * wordLength;

        // iterate through the substring
        for (int leftIndex = 0; leftIndex < str.length() - wordsLength + 1; leftIndex++) {
            // Important: Map must be initialized for each interation
            HashMap<String, Integer> wordsSeen = new HashMap<>();

            // iterate through the number of words in the array
            for (int rightIndex = 0; rightIndex < wordCount; rightIndex++) {
                // determine the next word starting index
                int nextWordStartIndex = leftIndex + rightIndex * wordLength;
                int nextWordEndIndex = nextWordStartIndex + wordLength;

                // System.out.println(leftIndex + ":" + rightIndex + ":" + nextWordStartIndex +
                // ":" + nextWordEndIndex);

                // break if the next word index is more than the string length
                if (nextWordEndIndex > str.length()) {
                    break;
                }

                // get the enxt word
                String nextWord = str.substring(nextWordStartIndex, nextWordEndIndex);

                // if the word is not in the array, break
                if (!wordFrequencyMap.containsKey(nextWord)) {
                    break;
                }

                // add the word and the count
                wordsSeen.put(nextWord, wordsSeen.getOrDefault(nextWord, 0) + 1);
                // System.out.println("-=-=>" + wordsSeen.toString());

                // break if count found is more than the expected
                if (wordsSeen.get(nextWord).intValue() > wordFrequencyMap.get(nextWord).intValue()) {
                    break;
                }
            }

            // if all the words are found, add the index
            if (wordsSeen.equals(wordFrequencyMap)) {
                indexes.add(leftIndex);
                System.out.println(leftIndex + ":" + wordsSeen.toString() + ":" + wordFrequencyMap.toString());
                // System.out.println("----->" + indexes.toString());
            }

            System.out.println("-=-=-=-=-=-=-=-=-=-=");
        }

        return indexes;
    }

    public static void main(String[] args) {
        // List<Integer> result = WordConcatenation.findWordConcatenation(
        // "catfoxcat", new String[] { "cat", "fox" });
        // System.out.println(result);

        // List<Integer> result = WordConcatenation.findWordConcatenation(
        // "wordgoodgoodgoodbestword", new String[] { "word", "good", "best", "word" });
        // System.out.println(result);

        // result = WordConcatenation.findWordConcatenation(
        // "a", new String[] { "a" });
        // System.out.println(result);

        // List<Integer> result = WordConcatenation.findWordConcatenation(
        // "bcabbcaabbccacacbabccacaababcbb", new String[] { "c", "b", "a", "c", "a",
        // "a", "a", "b", "c" });
        // System.out.println(result);

        // result = WordConcatenation.findWordConcatenationVariant(
        // "catfoxcat", new String[] { "cat", "fox" });
        // System.out.println(result);

        // result = WordConcatenation.findWordConcatenationVariant(
        // "wordgoodgoodgoodbestword", new String[] { "word", "good", "best", "word" });
        // System.out.println(result);

        // result = WordConcatenation.findWordConcatenationVariant(
        // "a", new String[] { "a" });
        // System.out.println(result);

        List<Integer> result = WordConcatenation.findWordConcatenationVariant(
                "bcabbcaabbccacacbabccacaababcbb", new String[] { "c", "b", "a", "c", "a", "a", "a", "b", "c" });
        System.out.println(result);
    }
}