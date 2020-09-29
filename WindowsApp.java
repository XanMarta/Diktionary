import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;


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
                showWord();
            }
        });
    }


    public void changeSearchWord() {
        String textInput = textField1.getText();
        System.out.println("Text change: " + textInput);
        list1.setListData(manager.getWordHint(textInput));
    }

    public void showWord() {
        String word = (String)list1.getSelectedValue();
        wordLabel.setText(word);
        labelMeaning.setText(dictionary.word.get(word).word_explain);
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
    public static DictionaryCommandLine command = new DictionaryCommandLine(manager);

    public static void main(String[] args) {
        WindowsApp app = new WindowsApp();
        manager.insertFromFile();
        app.startApplication();
    }

}
