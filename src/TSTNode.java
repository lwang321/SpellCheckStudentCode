
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