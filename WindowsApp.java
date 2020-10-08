import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class WindowsApp {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel wordLabel;
    private JTextField textField1;
    private JTabbedPane tabbedPane1;
    private JPanel Meaning;
    private JPanel Synonym;
    private JPanel Antonym;
    private JList list1;
    private JLabel labelMeaning;
    private JButton internetTranslateButton;
    private JScrollPane scrollPane;
    private JButton googleButton;
    private JButton ttsButton;


    public WindowsApp() {
        textField1.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                changeSearchWord();
            }
            public void removeUpdate(DocumentEvent e) {
                changeSearchWord();
            }
            public void changedUpdate(DocumentEvent e) {}
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!list1.isSelectionEmpty()) {
                    showWord();
                }
            }
        });

        internetTranslateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                internetTranslale();
            }
        });

        try {
            Image img = ImageIO.read(getClass().getResource("image/google.png"));
            googleButton.setIcon(new ImageIcon(img));
        } catch (IOException e) { }

        try {
            Image img = ImageIO.read(getClass().getResource("image/speaker.png"));
            ttsButton.setIcon(new ImageIcon(img));
        } catch (IOException e) { }

        googleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seletranslator.seleTranslate(textField1.getText());
            }
        });

        ttsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ttstranslator.speakTTS(textField1.getText());
            }
        });

        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
    }


    public void internetTranslale() {
        String word = textField1.getText();
        String result = apitranslator.apiTranslate(word);
        wordLabel.setText(word);
        labelMeaning.setText(result);
    }

    public void changeSearchWord() {
        String textInput = textField1.getText().toLowerCase();
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addAll(manager.getWordHint(textInput));
        list1.setModel(model);
    }

    public void showWord() {
        String word = (String)list1.getSelectedValue();
        wordLabel.setText(word);
        labelMeaning.setText("<html><p style=\"width:768px\">" + dictionary.word.get(word).word_explain + "</p></html>");
    }

    public void startApplication() {
        JFrame frame = new JFrame("WindowsApp");
        frame.setContentPane(new WindowsApp().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }


    public static Dictionary dictionary = new Dictionary();
    public static DictionaryManagement manager = new DictionaryManagement(dictionary);
    public static apiTranslator apitranslator = new apiTranslator();
    public static seleTranslator seletranslator = new seleTranslator();
    public static translateTTS ttstranslator = new translateTTS();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[3].getClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        WindowsApp app = new WindowsApp();
//        manager.insertFromFile();
        manager.importCsvFile();
        app.startApplication();
    }

}
