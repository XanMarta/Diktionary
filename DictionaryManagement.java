import java.util.Scanner;

public class DictionaryManagement {
    
    static Dictionary dictionary = new Dictionary();
    static DictionaryCommandline command = new DictionaryCommandline();

    static void insertFromCommandline() {
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
    }

    public static void main(String[] args) {
        insertFromCommandline();
        command.showAllWords(dictionary);
    }

}
