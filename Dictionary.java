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

    public static void main(String[] args) throws FileNotFoundException {
        Trie<String, String> trie = new PatriciaTrie<>();
        Scanner scan = new Scanner(new File("dictionaries.txt"));
        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split("\\|");
            for (String word : line) {
                word = word.trim();
                trie.put(word, word.toUpperCase());
            }
        }
        scan.close();

//        trie.
//        System.out.println(trie.get("donkey") + " " + trie.get("file"));
    }

}
