/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: Lucas Wang
 * */
import java.util.Arrays;

public class SpellCheck {

    public String[] checkWords(String[] text, String[] dictionary) {
        // Put given dictionary into a TST
        TST dict = new TST();
        for (String word : dictionary) {
            dict.insert(word);
        }

        // Create a TST to track misspelled words
        TST misspelledtrie = new TST();

        // Also need an array to track order of inserted words
        String[] misspelled = new String[text.length];

        int counter = 0;
        for (String word : text) {
            // If word isn't in dictionary or in misspelledtrie, add the word to misspelledtrie and misspelled array
            if (!dict.lookup(word) && !misspelledtrie.lookup(word)) {
                misspelledtrie.insert(word);
                misspelled[counter] = word;
                counter++;
            }
        }

        // Use copyOf to resize the array before submitting
        return Arrays.copyOf(misspelled, counter);
    }
}
