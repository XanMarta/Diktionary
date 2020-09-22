public class Application {

    static Dictionary dictionary = new Dictionary();
    static DictionaryManagement manager = new DictionaryManagement(dictionary);
    static DictionaryCommandLine command = new DictionaryCommandLine(manager);

    public static void main(String[] args) {
        command.dictionaryAdvanced();
    }

}
