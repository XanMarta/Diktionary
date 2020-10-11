import javax.swing.*;

public class Application {

    public static Dictionary dictionary = new Dictionary();
    public static DictionaryManagement manager = new DictionaryManagement();
    public static apiTranslator apitranslator = new apiTranslator();
    public static seleTranslator seletranslator = new seleTranslator();
    public static ttsTranslator ttstranslator = new ttsTranslator();

    public static JFrame mainFrame;


    public static void startApplication(JPanel mainPanel, String title) {
        mainFrame = new JFrame(title);
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }


    public static void main(String[] args) {
        manager.importCsvFile();
        startApplication(new WindowsApp().getMainPanel(), "Diktionary");
    }

}
