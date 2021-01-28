package spell;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public class SpellCorrector implements ISpellCorrector{

    Trie dictionary = new Trie();

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        dictionary = new Trie();

        Scanner scanner = new Scanner(new File(dictionaryFileName));
        while (scanner.hasNext()) {
            String line = scanner.next();
            dictionary.add(line.replace("\n", ""));
        }
    }


    @Override
    public String suggestSimilarWord(String unsignedInputWord) {

        String inputWord = unsignedInputWord.toLowerCase();

        if (dictionary.find(inputWord) != null){ // the exact word is found

            return inputWord;
        }

        //call edit distances
        TreeSet<String> distance1 = new TreeSet<>();

        deletion(inputWord, distance1); // for distance 2, loop through posibilites
        transposition(inputWord, distance1);
        alteration(inputWord, distance1);
        insertion(inputWord,distance1);

        TreeSet<String> distance2 = new TreeSet<>(distance1);
        deletion(inputWord, distance2); // for distance 2, loop through posibilites
        transposition(inputWord, distance2);
        alteration(inputWord, distance2);
        insertion(inputWord,distance2);

        INode node = dictionary.find("yea");

        distance1.removeIf(s -> dictionary.find(s+"") == null);
        distance2.removeIf(s -> dictionary.find(s+"") == null);

        boolean bool = distance1.contains(inputWord);

        // just one distance 1 word
        if (distance1.size() == 1) {
            return distance1.iterator().next();
        }

        if (distance1.size() > 1){
            ArrayList<String> dis1words = new ArrayList<>(distance1);
            for (String s : dis1words) {
                if (dictionary.find(s).getValue() == dictionary.getHighestWordCount()){
                    dis1words.add(s);
                }
            }

            if (dis1words.size() == 1) {
                return dis1words.get(0);
            }

            Collections.sort(dis1words);
            return dis1words.get(0);
        }



        if (distance2.contains(inputWord)){
            return null;
        }


        return null;
    }

    // deletion
    // get edit distance 1 first!
    private void deletion(String string, TreeSet<String> possibilities) {
        for (int i = 0; i < string.length(); i++) {
            StringBuilder builder = new StringBuilder(string);
            String s = builder.deleteCharAt(i).toString();
            possibilities.add(s);
        }
    }

    //transposition
    private void transposition(String string, TreeSet<String> possibilities) {
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
    private void alteration(String string, TreeSet<String> possibilities) {
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

    private void insertion(String string, TreeSet<String> possibilities) {
        for (int i = 0; i < string.length() + 1; i++) {
            StringBuilder builder = new StringBuilder(string);
            for (int j = 0; j < 26; j++) {
                StringBuilder builder2 = new StringBuilder(builder);
                char currentChar =  (char)(j+'a');
                builder2.insert(i, currentChar);
                possibilities.add(builder2.toString());
            }
        }
    }

}
