package spell;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellCorrector implements ISpellCorrector{

    Trie dictionary = new Trie();

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        dictionary = new Trie();

        Scanner scanner = new Scanner(new File(dictionaryFileName));
        while (scanner.hasNext()) {
            String line = scanner.next();
            dictionary.add(line);
        }
    }


    @Override
    public String suggestSimilarWord(String inputWord) {
        /*
    Most similar rules:
        1. it has the "closest" edit distance from the input string
        2. If multiple words have the same edit distance,
            the most similar word is the one with the closest edit distance
            that is found the most times in the dictionary
        3. If multiple words are the same edit distance and have the same count/frequency.
            the most similar word is the one that is first alphabetically

     */

        if (dictionary.equals(inputWord)){ // the exact word is found
            return inputWord;
        }

        //call edit distances
        ArrayList<String> possibilities1 = new ArrayList<>();
        ArrayList<String> possibilities2 = new ArrayList<>();
//        deletion(inputWord, possibilities1); // for distance 2, loop through posibilites


        int i = 0;

        return null;
    }

    // deletion
    // get edit distance 1 first!
    private void deletion(String string, ArrayList<String> possibilities) {
        for (int i = 0; i < string.length(); i++) {
            StringBuilder builder = new StringBuilder(string);
            String s = builder.deleteCharAt(i).toString();
            possibilities.add(s);
        }
    }

    //transposition
    private void transposition(String string) {

    }

    //alteration
    private void alteration(String string) {
        // use a-z
    }

    //insertion
    // use a-z

}
