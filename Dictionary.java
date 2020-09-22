import java.util.*;

public class Dictionary {

    public Vector<String> wordList = new Vector<String>(10, 10);
    public HashMap<String, Word> word = new HashMap<String, Word>();

    public void addWord(String target, String explain) {
        Word newWord = new Word(target, explain);
        wordList.addElement(target);
        word.put(target, newWord);
    }
    
}
