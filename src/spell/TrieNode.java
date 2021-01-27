package spell;

public class TrieNode implements INode{

    public static final int LETTERS_OF_ALPHABET = 26;

    int count;
    TrieNode[] children;

    public TrieNode(){
        count = 0;
        children = new TrieNode[26];
    }

    @Override
    public int getValue() {
        return count;
    }

    @Override
    public void incrementValue() {
        count++;
    }

    @Override
    public TrieNode[] getChildren() {
        return children;
    }

    public int hashCode(){
        return children.length;
    }


}
