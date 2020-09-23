import java.util.*;
import java.io.*;

public class DictionaryManagement {
    
    public Dictionary dictionary = null;
    final String sourceFile = "dictionaries.txt";
    
    public DictionaryManagement (Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void insertFromCommandline() {
        System.out.print("Input the amount of word: ");
        int wordAmount = Console.scan.nextInt();
        System.out.println();
        Console.scan.nextLine();

        for (int i = 1; i <= wordAmount; i++) {
            System.out.print("Input " + i + " target : ");
            String target = Console.scan.nextLine();
            System.out.print("Input " + i + " explain: ");
            String explain = Console.scan.nextLine();
            dictionary.addWord(target, explain);
        }
        System.out.println();
    }

    //Load words from file
    public void insertFromFile() {
        try {
            System.out.println("    -- Input from file --");
            File file = new File(sourceFile);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split("\\|");
                dictionary.addWord(data[0].trim(), data[1].trim());
            }
            scan.close();
            System.out.println("    -- Input done --");
        } catch (FileNotFoundException e) {
            System.out.println("Can't read file: " + sourceFile);
        }
        System.out.println();
        dictionarySort();
    }

    public void dictionaryLookup() {
        System.out.println("    -- Look up --");
        System.out.print("Input the word you need to find: ");
        String target = Console.scan.nextLine();
        if (dictionary.word.containsKey(target)) {
            System.out.println("Meaning: " + dictionary.word.get(target).word_explain);
        } else {
            //System.out.println("Your word is not in dictionary yet");
            for (int i = 0; i < dictionary.wordList.size(); ++i) {
                if (dictionary.wordList.elementAt(i).substring(0, target.length()).equals(target.toLowerCase())
                        || dictionary.wordList.elementAt(i).substring(0, target.length()).equals(target.toUpperCase())
                        || dictionary.wordList.elementAt(i).substring(0, target.length()).equals(target)) {
                    System.out.println(dictionary.wordList.get(i));
                } else {
                    continue;
                }
            }
            System.out.println("Your word is not in dictionary yet");
        }
        System.out.println();
    }

    public void eraseWord() {
        System.out.print("Input word to erase: ");
        String target = Console.scan.nextLine();
        if (dictionary.wordList.remove(target)) {
            dictionary.word.remove(target);
            System.out.println("Remove word " + target + " successfully");
        } else {
            System.out.println("Dictionary doesn't contain the word " + target);
        }
        System.out.println();
    }

    public void addWord() {
        System.out.println("    -- Add word --");
        System.out.print("Input word target: ");
        String target = Console.scan.nextLine();
        if (dictionary.word.containsKey(target)) {
            System.out.println("Dictionary has already had word '" + target + "' with meaning '" + dictionary.word.get(target).word_explain + "'");
            System.out.print("Input y to continue to change the meaning: ");
            String temp = Console.scan.nextLine();
            if (temp.equals("y") || temp.equals("Y")) {
                System.out.print("Input word explain: ");
                String explain = Console.scan.nextLine();
                dictionary.word.get(target).word_explain = explain;
            }
        } else {
            System.out.print("Input word explain: ");
            String explain = Console.scan.nextLine();
            dictionary.addWord(target, explain);
        }
        System.out.println();
        dictionarySort();
    }

    public void dictionaryExportToFile() {
        try {
            System.out.print("Input y to export dictionary to file: ");
            String temp = Console.scan.nextLine();
            if (!(temp.equals("y") || temp.equals("Y"))) {
                return;
            }

            System.out.println("    -- Start writing --");
            FileWriter writer = new FileWriter(sourceFile);
            for (String target : dictionary.wordList) {
                writer.write(target + " | " + dictionary.word.get(target).word_explain + "\n");
            }
            writer.close();
            System.out.println("    -- Writing completed --");
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
        System.out.println();
    }

    public void dictionarySort() {
        Collections.sort(dictionary.wordList);
    }

}
