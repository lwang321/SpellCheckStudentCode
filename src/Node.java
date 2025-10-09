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