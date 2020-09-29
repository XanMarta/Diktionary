
public class DictionaryCommandLine {
    
    final int noSpace = 4;
    final int targetSpace = 25;
    DictionaryManagement manager = null;

    public DictionaryCommandLine(DictionaryManagement manager) {
        this.manager = manager;
    }

    public void showAllWords() {
        Dictionary dictionary = manager.dictionary;
        System.out.println("    -- Show all words: --");
        // Print header
        System.out.print("No");
        for (int i = 1; i <= noSpace - 2; i++) {
            System.out.print(" ");
        }
        System.out.print("|  English");
        for (int i = 1; i <= targetSpace - 9; i++) {
            System.out.print(" ");
        }
        System.out.println("|  Vietnamese");
        // Print content
        for (int i = 1; i <= dictionary.wordList.size(); i++) {
            String no = i + "";
            String target = dictionary.wordList.get(i - 1);
            String explain = dictionary.word.get(target).word_explain;

            System.out.print(no);
            for (int j = 1; j <= noSpace - no.length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|" + target);
            for (int j = 1; j <= targetSpace - target.length(); j++) {
                System.out.print(" ");
            }
            System.out.println("|" + explain);
        }
        System.out.println();
    }


    public void dictionaryBasic() {
        manager.insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced() {
        manager.insertFromFile();
        showAllWords();
        manager.dictionaryLookup();
    }

}
