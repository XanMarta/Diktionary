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

        Vector<String> wordList = new Vector<>();
        wordList.add("Hello");
        wordList.add("Absort");
        wordList.add("Brave");
        wordList.add("Cymatics");
        list1.setListData(wordList);

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                wordLabel.setText((String)list1.getSelectedValue());
            }
        });
    }

    public void changeSearchWord() {
        System.out.println("Text change: " + textField1.getText());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("WindowsApp");
        frame.setContentPane(new WindowsApp().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

}
