public class TST {
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
        root.setLetter('m');

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
