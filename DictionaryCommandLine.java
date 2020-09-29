
class DictionaryCommandLine {

    final int noSpace = 4;
    final int targetSpace = 25;
    final int explainSpace = 20;
    final int synonymSpace = 20;
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
        System.out.print("|  Vietnamese");
        for (int i = 1; i <= 10; i++) {
            System.out.print(" ");
        }
        System.out.print("|  Synonyms");
        for (int i = 1; i <= 15; i++) {
            System.out.print(" ");
        }
        System.out.println("|  Antonyms");
        // Print content
        for (int i = 1; i <= dictionary.wordList.size(); i++) {
            String no = i + "";
            String target = dictionary.wordList.get(i - 1);
            String explain = dictionary.word.get(target).word_explain;
            String synonyms = dictionary.word.get(target).word_synonyms;
            String antonyms = dictionary.word.get(target).word_atonyms;

            System.out.print(no);
            for (int j = 1; j <= noSpace - no.length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|" + target);
            for (int j = 1; j <= targetSpace - target.length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|" + explain);
            for (int j = 1; j <= explainSpace - explain.length(); j++) { //getSpace - explain.length()
                System.out.print(" ");
            }
            System.out.print("|" + synonyms);
            for (int j = 1; j <= synonymSpace - synonyms.length(); j++) { //getSpace - explain.length()
                System.out.print(" ");
            }
            System.out.println("|" + antonyms);
        }
        System.out.println();
        /*
        System.out.println("Synonyms");
        for (int i = 1; i <= dictionary.wordList.size(); ++i) {

        }

         */
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
