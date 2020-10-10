import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WindowsApp {
    private JPanel mainPanel;
    private JPanel bodyPanel;
    private JPanel explainPanel;
    private JLabel labelExplain;
    private JTabbedPane tabExplain;
    private JPanel meaningTab;
    private JPanel synonymTab;
    private JPanel imageTab;
    private JPanel buttonPanel;
    private JScrollPane listPanel;
    private JList wordList;
    private JScrollPane scrollMeaning;
    private JTextField textInput;
    private JScrollPane textPanel;
    private JLabel textMeaning;
    private JLabel textSynonym;
    private JButton translateButton;
    private JButton apiButton;
    private JButton seleButton;

    public WindowsApp() {
        textInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {changeSearchWord();}
            public void removeUpdate(DocumentEvent e) {changeSearchWord();}
            public void changedUpdate(DocumentEvent e) {}
        });
        wordList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!wordList.isSelectionEmpty()) {
                    translateWord((String)wordList.getSelectedValue());
                }
            }
        });
        translateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                translateWord(textInput.getText());
            }
        });
        apiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                apiTranslate(textInput.getText());
            }
        });
        seleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleTranslate(textInput.getText());
            }
        });

        listPanel.getVerticalScrollBar().setUnitIncrement(20);
        listPanel.getHorizontalScrollBar().setUnitIncrement(20);
        scrollMeaning.getVerticalScrollBar().setUnitIncrement(20);
        scrollMeaning.getHorizontalScrollBar().setUnitIncrement(20);
    }


    public void changeSearchWord() {
        String word = textInput.getText().toLowerCase();
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addAll(manager.getWordHint(word));
        wordList.setModel(model);
    }

    public void translateWord(String word) {
        labelExplain.setText(word);
        if (dictionary.word.containsKey(word)) {
            textMeaning.setText(ConsoleC.textToHtml(dictionary.word.get(word).word_explain, 768));
            textSynonym.setText(ConsoleC.textToHtml(dictionary.word.get(word).word_synonyms, 768));
        } else {
            textMeaning.setText("There no word " + word + " in diktionary");
            textSynonym.setText("There no word " + word + " in diktionary");
        }
    }

    public void apiTranslate(String word) {
        new Thread() {
            public void run() {
                String result = apitranslator.apiTranslate(word);
                labelExplain.setText(word);
                textMeaning.setText(result);
            }
        }.start();
    }

    public void seleTranslate(String word) {
        new Thread() {
            public void run() {
                seletranslator.seleTranslate(word);
            }
        }.start();
    }

    public static void startApplication() {
        JFrame frame = new JFrame("Diktionary");
        frame.setContentPane(new WindowsApp().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }


    public static Dictionary dictionary = new Dictionary();
    public static DictionaryManagement manager = new DictionaryManagement(dictionary);
    public static apiTranslator apitranslator = new apiTranslator();
    public static seleTranslator seletranslator = new seleTranslator();
    public static translateTTS ttstranslator = new translateTTS();


    public static void main(String[] args) {
        manager.importCsvFile();
        startApplication();
    }
}
