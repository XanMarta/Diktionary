import javax.swing.*;

public class Application {

    public static Dictionary dictionary = new Dictionary();
    public static DictionaryManagement manager = new DictionaryManagement();
    public static apiTranslator apitranslator = new apiTranslator();
    public static seleTranslator seletranslator = new seleTranslator();
    public static ttsTranslator ttstranslator = new ttsTranslator();
    public static ImageScrapter imageScrapter = new ImageScrapter();

    public static JFrame mainFrame;
    public static WindowsApp windowsApp = new WindowsApp();
    public static AdminApp adminApp = new AdminApp();
    public static Thread imageThread = null;


    public static void startApplication(boolean isWindows) {
        String title = "";
        JPanel mainPanel = null;
        if (isWindows) {
            title = "DIKTIONARY";
            mainPanel = windowsApp.getMainPanel();
        } else {
            title = "ADMIN MODE";
            mainPanel = adminApp.getMainPanel();
        }
        mainFrame = new JFrame(title);
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }


    public static void main(String[] args) {
        manager.importDatabaseFile();
        manager.importChangeFile();
        startApplication(true);
        imageScrapter.initScrapter();
    }

}
