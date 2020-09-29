
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Files {
    public static void importCsv(Dictionary dictionary) {
        try {
            Scanner scan = new Scanner(new File("dictionaries.csv"));
            while (scan.hasNextLine()) {
                String[] word = scan.nextLine().split(",");
                dictionary.addWord(word[0].toLowerCase(), "(" + word[1] + ") " + word[2], "", "");
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    
}
