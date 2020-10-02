import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dictionary {
    // Of course this is Dictionary class
    public Trie<String, Word> word = new PatriciaTrie<>();

    public void addWord(String target, String explain, String synonyms, String antonyms) {
        if (!word.containsKey(target)) {
            Word newWord = new Word(target, explain, synonyms, antonyms);
            word.put(target, newWord);
        } else {
            word.get(target).word_explain += "<br>" + explain;
        }
    }
}
