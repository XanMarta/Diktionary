
public class Word {

    public String word_target = "";
    public String word_mainmean = "";
    public String word_explain = "";
    public String word_synonyms = "";

    public Word(String target, String mainmean, String explain, String synonyms) {
        this.word_target = target;
        this.word_explain = explain;
        this.word_synonyms = synonyms;
        this.word_mainmean = mainmean;
    }

}