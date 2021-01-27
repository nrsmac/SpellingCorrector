package spell;

import java.io.IOException;

public class SpellCorrector implements ISpellCorrector{
    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {

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
        return null;
    }
}
