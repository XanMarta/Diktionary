import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;


public class Dictionary {
    // Of course this is Dictionary class
    public Trie<String, Word> word = new PatriciaTrie<>();

    public void addWord(String target, String mainmean, String explain, String synonyms) {
        if (!word.containsKey(target)) {
            Word newWord = new Word(target, mainmean, explain, synonyms);
            word.put(target, newWord);
        } else {
            word.get(target).word_mainmean = mainmean;
            word.get(target).word_explain = explain;
            word.get(target).word_synonyms = synonyms;
        }
    }
}
