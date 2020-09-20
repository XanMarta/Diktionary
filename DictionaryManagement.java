import java.util.*;
import java.io.*;

public class DictionaryManagement {
    
    static Dictionary dictionary = new Dictionary();
    static DictionaryCommandLine command = new DictionaryCommandLine();

    static void insertFromFile(String file_name) {
        try {
            File file = new File(file_name);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split("\\|");
                dictionary.addWord(data[0].trim(), data[1].trim());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't read file: " + file_name);
        }
    }

    public static void main(String[] args) {
        insertFromFile("dictionaries.txt");
        command.showAllWords(dictionary);
    }

}
