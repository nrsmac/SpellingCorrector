package spell;

import java.io.*;
import java.lang.reflect.Array;
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
        ArrayList<String> distance1 = new ArrayList<>();


        deletion(inputWord, distance1); // for distance 2, loop through posibilites
        transposition(inputWord, distance1);
        alteration(inputWord, distance1);
        insertion(inputWord,distance1);

        ArrayList<String> distance2 = new ArrayList<>(distance1);
        deletion(inputWord, distance2); // for distance 2, loop through posibilites
        transposition(inputWord, distance2);
        alteration(inputWord, distance2);
        insertion(inputWord,distance2);

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
    private void transposition(String string, ArrayList<String> possibilities) {
        for (int i = 0; i < string.length(); i++) {
            StringBuilder builder = new StringBuilder(string);
            char[] stringChars = string.toCharArray();
            builder.deleteCharAt(i);
            for (int j = 0; j < stringChars.length; j++) {
                StringBuilder builder2 = new StringBuilder(builder);
                String currentChar = stringChars[j] + "";
                builder2.replace(i, i, currentChar);
                possibilities.add(builder2.toString());
            }
        }
    }

    //alteration
    private void alteration(String string, ArrayList<String> possibilities) {
        for (int i = 0; i < string.length(); i++) {
            StringBuilder builder = new StringBuilder(string);
            builder.deleteCharAt(i);
            for (int j = 0; j < 26; j++) {
                StringBuilder builder2 = new StringBuilder(builder);
                String currentChar =  (char)(j+'a') + "";
                builder2.replace(i, i, currentChar);
                possibilities.add(builder2.toString());
            }
        }
    }

    //insertion
    // use a-z

    private void insertion(String string, ArrayList<String> possibilities) {
        for (int i = 0; i < string.length(); i++) {
            StringBuilder builder = new StringBuilder(string);
            for (int j = 0; j < 26; j++) {
                StringBuilder builder2 = new StringBuilder(builder);
                String currentChar =  (char)(j+'a') + "";
                builder2.insert(i, currentChar);
                possibilities.add(builder2.toString());
            }
        }
    }

}
