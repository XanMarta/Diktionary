import java.util.*;
import java.io.*;

public class DictionaryManagement {
    
    public Dictionary dictionary = null;
    final String sourceFile = "dictionaries.txt";
    
    public DictionaryManagement (Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input the amount of word: ");
        int wordAmount = scan.nextInt();
        System.out.println();
        scan.nextLine();

        for (int i = 1; i <= wordAmount; i++) {
            System.out.print("Input " + i + " target : ");
            String target = scan.nextLine();
            System.out.print("Input " + i + " explain: ");
            String explain = scan.nextLine();
            dictionary.addWord(target, explain);
        }
        scan.close();
    }

    //Load words from file
    public void insertFromFile() {
        try {
            System.out.println("-- Input from file --");
            File file = new File(sourceFile);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split("\\|");
                dictionary.addWord(data[0].trim(), data[1].trim());
            }
            scan.close();
            System.out.println("-- Input done --");
        } catch (FileNotFoundException e) {
            System.out.println("Can't read file: " + sourceFile);
        }
    }

    public void dictionaryLookup() {
        System.out.println("-- Look up --");
        Scanner scan = new Scanner(System.in);
        System.out.print("Input the word you need to find: ");
        String target = scan.nextLine();
        if (dictionary.word.containsKey(target)) {
            System.out.println("Meaning: " + dictionary.word.get(target).word_explain);
        } else {
            System.out.println("Your word is not in dictionary yet");
        }
        scan.close();
    }

}
