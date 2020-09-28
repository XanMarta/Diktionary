import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class WindowsApp {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel wordLabel;
    private JScrollPane contentPanel;
    private JTextField textField1;
    private JList list1;
    private JTabbedPane tabbedPane1;


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
