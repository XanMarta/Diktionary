
public class Word {

    public String word_target = "";
    public String word_explain = "";
    public String word_synonyms = "";
    public String word_atonyms = "";
    //public String word_pronounce = ""; //file mp3

    public Word(String target, String explain, String synonyms, String atonyms) {
        this.word_target = target;
        this.word_explain = explain;
        this.word_synonyms = synonyms;
        this.word_atonyms = atonyms;
    }

}