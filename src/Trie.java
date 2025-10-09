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