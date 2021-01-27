package spell;

public class Trie implements ITrie{

    public static final int LETTERS_OF_ALPHABET = 26;

    private TrieNode root;

    int wordCount;
    int nodeCount;
    int hashCode;

    public Trie() {
        wordCount = 0;
        nodeCount = 1;
        hashCode = 0;

        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return this.root;
    }

    @Override
    public void add(String word) {
            String w = word.toLowerCase();
            hashCode += w.hashCode();
            addHelper(root, w);
    }

    private void addHelper(TrieNode node, String string) {

        // base case -> string is empty
        if(string.length() == 0){
            if(node.getValue() == 0){
                wordCount++;
            }

            node.incrementValue();
            return;
        }

        TrieNode[] currChildren = node.getChildren();

        // if node does not exist
        if (node.getChildren()[string.charAt(0) - 'a'] == null){
            nodeCount++;
            currChildren[string.charAt(0) - 'a'] = new TrieNode();

        }

        // if node exists
        addHelper(currChildren[string.charAt(0) - 'a'], string.substring(1, string.length()));

    }


    @Override
    public INode find(String word) {
        String w = word.toLowerCase();

        // consider edge cases here
        //

        TrieNode currNode = this.root;

        for (int i = 0; i < word.length(); i++){
            if (currNode.getChildren()[word.charAt(i) - 'a'] != null) {
                currNode = currNode.getChildren()[word.charAt(i) - 'a'];
                if (currNode.getValue() > 0 && i == word.length() - 1) {
                    return currNode;
                }
            }

        }
        return null;
    }

    @Override
    public int getWordCount() {
        return wordCount;
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder currWord = new StringBuilder();
        StringBuilder output = new StringBuilder();
        toString_helper(root, currWord, output);
        return output.toString();
    }

    private void toString_helper(INode n, StringBuilder currWord, StringBuilder output){

        if (n.getValue() > 0) {
            //append n's word to output
            output.append(currWord.toString());
            output.append("\n");
        }

        for (int i = 0; i < n.getChildren().length ; i++) {
            INode child = n.getChildren()[i];
            if (child != null) {

                char childLetter = (char)('a' + i);
                currWord.append(childLetter);
                toString_helper(child, currWord, output);
                currWord.deleteCharAt(currWord.length() - 1); // just make sure you're removing the last letter
            }
        }
    }

    public boolean equals(Object o) {

        if (!(o instanceof ITrie)){
            return false;
        }

        Trie myTrie = (Trie) o;
        return equalsHelper(root, myTrie.getRoot());
    }

    private boolean equalsHelper(TrieNode n1, TrieNode n2) {// returns false when difference is found

        if (n1 == null && n2 == null){ // both null
            return true;
        }

        //n1 null
        if (n1 == null && n2 != null){
            return false;
        }

        //n2 null
        if (n1 != null && n2 == null){
            return false;
        }

        if (n1.getValue() != n2.getValue()){
            int i = 0;
            return false;
        }

        //both not null
        for (int i = 0; i < n1.getChildren().length; i++){
            if (equalsHelper(n1.getChildren()[i], n2.getChildren()[i])) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }
}
