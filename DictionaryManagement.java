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
        int wordAmount = ConsoleC.scan.nextInt();
        System.out.println();
        ConsoleC.scan.nextLine();

        for (int i = 1; i <= wordAmount; i++) {
            System.out.print("Input " + i + " target : ");
            String target = ConsoleC.scan.nextLine();
            System.out.print("Input " + i + " explain: ");
            String explain = ConsoleC.scan.nextLine();
            System.out.print("Input " + i + " synonym: ");
            String synonym = ConsoleC.scan.nextLine();
            System.out.print("Input " + i + " antonym: ");
            String antonym = ConsoleC.scan.nextLine();
            dictionary.addWord(target, explain, synonym, antonym);
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
                dictionary.addWord(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim());
            }
            scan.close();
            System.out.println("    -- Input done --");
        } catch (FileNotFoundException e) {
            System.out.println("Can't read file: " + sourceFile);
        }
        System.out.println();
        dictionarySort();
    }

    public static int binary_search(Vector<String> wordList, String target) {
        int left = 0, right = wordList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (wordList.get(mid).compareToIgnoreCase(target) == 0) return mid;
            else {
                if (wordList.get(mid).compareToIgnoreCase(target) < 0) left = mid + 1;
                else if (wordList.get(mid).compareToIgnoreCase(target) > 0) right = mid - 1;
            }
        }
        return -1;
    }

    public void dictionaryLookup() {
        System.out.println("    -- Look up --");
        System.out.print("Input the word you need to find: ");
        ////String target = Console.scan.nextLine();
        String target = ConsoleC.scan.nextLine();
        int mid = binary_search(dictionary.wordList, target);
        if (mid != -1) {
            System.out.println("Meaning: " + dictionary.word.get(dictionary.wordList.get(mid)).word_explain);
            System.out.println("Synonyms: " + dictionary.word.get(dictionary.wordList.get(mid)).word_synonyms);
            System.out.println("Antonyms: " + dictionary.word.get(dictionary.wordList.get(mid)).word_antonyms);
        } else {
            boolean found = false;
            for (String key : dictionary.word.keySet()) {
                    if (key.substring(0, target.length()).equalsIgnoreCase(target)) {
                        System.out.println(key);
                        found = true;
                    }
                }
                if (found == false) {
                    System.out.println("Your word is not in dictionary yet");
                }
            }
        }

    public void eraseWord() {
        System.out.print("Input word to erase: ");
        String target = ConsoleC.scan.nextLine();
        int mid = binary_search(dictionary.wordList, target);
        if (mid != -1) {
            target = dictionary.wordList.get(mid);
            if (dictionary.wordList.remove(target)) {
                dictionary.word.remove(target);
                System.out.println("Remove word " + target + " successfully");
            }
        } else {
            System.out.println("Dictionary doesn't contain the word " + target);
        }
        System.out.println();
        /*
        if (dictionary.wordList.remove(target)) {
            dictionary.word.remove(target);
            System.out.println("Remove word " + target + " successfully");
        } else {
            System.out.println("Dictionary doesn't contain the word " + target);
        }
        System.out.println();
         */
    }

    public void addWord() {
        System.out.println("    -- Add word --");
        System.out.print("Input word target: ");
        String target = ConsoleC.scan.nextLine();
        int mid = binary_search(dictionary.wordList, target);
        if (mid != -1) {
            System.out.println("Dictionary has already had word '" + target + "' with meaning '" + dictionary.word.get(target).word_explain + "'");
            System.out.print("Input y to continue to change the meaning: ");
            String temp = ConsoleC.scan.nextLine();
            if (temp.equals("y") || temp.equals("Y")) {
                System.out.print("Input word explain: ");
                String explain = ConsoleC.scan.nextLine();
                System.out.print("Input word synonym: ");
                String synonym = ConsoleC.scan.nextLine();
                System.out.print("Input word antonym: ");
                String antonym = ConsoleC.scan.nextLine();
                dictionary.word.get(target).word_explain = explain;
                dictionary.word.get(target).word_synonyms = synonym;
                dictionary.word.get(target).word_antonyms = antonym;
            }
        } else {
            System.out.print("Input word explain: ");
            String explain = ConsoleC.scan.nextLine();
            System.out.print("Input word synonym: ");
            String synonym = ConsoleC.scan.nextLine();
            System.out.print("Input word antonym: ");
            String antonym = ConsoleC.scan.nextLine();
            dictionary.addWord(target, explain, synonym, antonym);
        }
        System.out.println();
        dictionarySort();
    }

    public void dictionaryExportToFile() {
        try {
            System.out.print("Input y to export dictionary to file: ");
            String temp = ConsoleC.scan.nextLine();
            if (!(temp.equals("y") || temp.equals("Y"))) {
                return;
            }
            System.out.println("    -- Start writing --");
            FileWriter writer = new FileWriter(sourceFile);
            for (String target : dictionary.wordList) {
                writer.write(target + " | "
                        + dictionary.word.get(target).word_explain
                        + " | " + dictionary.word.get(target).word_synonyms
                        + " | " + dictionary.word.get(target).word_antonyms + "\n");
            }
            writer.close();
            System.out.println("    -- Writing completed --");
        } catch (IOException e) {
            System.out.println("Cannot write to file");
        }
        System.out.println();
    }

    public void dictionarySort() {
        Collections.sort(dictionary.wordList, String.CASE_INSENSITIVE_ORDER);
    }

}
