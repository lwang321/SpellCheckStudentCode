/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: [YOUR NAME HERE]
 * */
import java.util.Arrays;

// Node class for Trie
class Node  {
    // Tracks if the node is the end of a word
    private boolean isWord;

    // use 256 to store all ASCII values
    private Node[] children = new Node[256];

    public boolean isWord() {
        return isWord;
    }

    public void setWord() {
        isWord = true;
    }

    public Node[] getChildren() {
        return children;
    }
}

class TSTNode  {
    // Tracks if the node is the end of a word
    private boolean isWord;

    // children[] keeps track of left, down, right children
    private TSTNode[] children = new TSTNode[3];
    private char letter;

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }


    public boolean isWord() {
        return isWord;
    }

    public void setWord() {
        isWord = true;
    }

    public TSTNode[] getChildren() {
        return children;
    }
}

class Trie {
    private Node root = new Node();

    void insert(String s) {
        Node current = root;
        for (int i = 0; i < s.length(); i++) {
            // Convert each letter to an int, which will be used to index in the children array
            int childindex = s.charAt(i);

            // Check if there already is a node in the child I want to visit, if not, create
            if (current.getChildren()[childindex] == null) {
                current.getChildren()[childindex] = new Node();
            }

            // Move to the next Node
            current = current.getChildren()[childindex];

        }

        // At the end of the word, mark it as a word
        current.setWord();
    }

    boolean lookup(String s) {
        Node current = root;
        for (int i = 0; i < s.length(); i++) {
            int childindex = s.charAt(i);

            // If the child in the children array at index childindex doesn't exist, return false
            if (current.getChildren()[childindex] == null) {
                return false;
            }

            // Move to the next Node
            current = current.getChildren()[childindex];

        }

        // Return the isWord value at the end of the search
        return current.isWord();
    }
}

class TST {
    TSTNode root = new TSTNode();

    // childindex returns 0, 1, or 2 depending on whether you should go left, down, or right
    int childindex(char c, char compareto) {
        if (c == compareto) {
            return 1;
        }
        else if (c < compareto) {
            return 0;
        }
        else {
            return 2;
        }
    }

    void insert(String s) {
        TSTNode current = root;

        // Set the root letter to 'a' to get things started
        root.setLetter('a');

        // i keeps track of index in string s
        int i = 0;

        // Loop through the string; can't use for loop because when moving left/right you can't go to the next letter in s
        while (i < s.length()) {

            // If there's no letter, set your position as your current letter
            if (current.getLetter() == 0) {
                current.setLetter(s.charAt(i));
            }

            // Will I go left (0), down (1), or right (2)?
            int childindex = childindex(s.charAt(i), current.getLetter());

            // Check if there already is a node in the child I want to visit, if not, create
            if (current.getChildren()[childindex] == null) {
                current.getChildren()[childindex] = new TSTNode();
            }

            // If letter "hits" (character in string equals character in your position in TST)
            if (childindex == 1) {
                // move to next letter in string
                i++;
            }

            // Move to the next TSTNode; left, right or middle based on childindex
            current = current.getChildren()[childindex];
        }

        // At the end of the word, mark it as a word
        current.setWord();
    }

    boolean lookup(String s) {
        TSTNode current = root;

        // i keeps track of index in string s
        int i = 0;
        while (i < s.length()) {
            int childindex = childindex(s.charAt(i), current.getLetter());

            // If the child in the children array at index childindex doesn't exist, return false
            if (current.getChildren()[childindex] == null) {
                return false;
            }

            // If letter "hits" (character in string equals character in your position in TST)
            if (childindex == 1) {
                i++;
            }

            // Move to the next TSTNode; left, right or middle based on childindex
            current = current.getChildren()[childindex];
        }

        // Return the isWord value at the end of the search
        return current.isWord();
    }
}

public class SpellCheck {
    /**
     * checkWords finds all words in text that are not present in dictionary
     *
     * @param text The list of all words in the text.
     * @param dictionary The list of all accepted words.
     * @return String[] of all mispelled words in the order they appear in text. No duplicates.
     */
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
