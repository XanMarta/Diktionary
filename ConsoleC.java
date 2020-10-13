import java.util.Scanner;

public class ConsoleC {
    public static Scanner scan = new Scanner(System.in);

    public static String textToHtml(String word, int size) {
        return "<html><p style=\"width:" + String.valueOf(size) + "px\">" + word.replace(";", "<br>") + "</p></html>";
    }
}
