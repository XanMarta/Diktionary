import java.util.Scanner;

public class DictionaryManagement {
    
    static Dictionary dictionary = new Dictionary();
    static DictionaryCommandLine command = new DictionaryCommandLine();

    public static void main(String[] args) {
        command.dictionaryBasic(dictionary);
    }

}
