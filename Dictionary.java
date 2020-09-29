import java.util.*;

public class Dictionary {
    // Of course this is Dictionary class
    public Vector<String> wordList = new Vector<String>(20, 20);
    public HashMap<String, Word> word = new HashMap<String, Word>();

    public void addWord(String target, String explain, String synonyms, String antonyms) {
        if (!word.containsKey(target)) {
            Word newWord = new Word(target, explain, synonyms, antonyms);
            wordList.addElement(target);
            word.put(target, newWord);
        }
    }

}
