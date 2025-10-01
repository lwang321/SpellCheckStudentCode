/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: [YOUR NAME HERE]
 * */
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class SpellCheck {


    /**
     * checkWords finds all words in text that are not present in dictionary
     *
     * @param text The list of all words in the text.
     * @param dictionary The list of all accepted words.
     * @return String[] of all mispelled words in the order they appear in text. No duplicates.
     */
    public String[] checkWords(String[] text, String[] dictionary) {
        Set<String> dict = new HashSet<>();
        for (String word : dictionary) {
            dict.add(word);
        }

        Set<String> mispelledSet = new HashSet<>();
        String[] misspelled = new String[text.length];
        String word;
        int nummispelled = 0;
        for (int i=0; i<text.length; i++) {
            word = text[i];
            if (!dict.contains(word) && !mispelledSet.contains(word)) {
                mispelledSet.add(word);
                misspelled[nummispelled] = word;
                nummispelled++;
            }
        }

        String[] answer = Arrays.copyOf(misspelled, nummispelled);
        return answer;
    }
}
