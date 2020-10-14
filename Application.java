import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Close driver");
                imageScrapter.driver.quit();
                super.windowClosing(e);
            }
        });
    }

    public void finalize() {
        if (imageScrapter.driver != null) {
            imageScrapter.driver.close();
            imageScrapter.driver = null;
        }
    }


    public static void main(String[] args) {
        manager.importDatabaseFile();
        manager.importChangeFile();
        startApplication(true);
        imageScrapter.initScrapter();
    }

}
