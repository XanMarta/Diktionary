
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConsoleC {
    public static Scanner scan = new Scanner(System.in);

    public static String textToHtml(String word, int size) {
        return "<html><p style=\"width:" + String.valueOf(size) + "px\">" + word.replace(";", "<br>") + "</p></html>";
    }

    public static String toUTF8(String word) {
        byte[] bytes = word.getBytes(StandardCharsets.UTF_16);
        return new String(bytes, StandardCharsets.UTF_16);
    }

}
