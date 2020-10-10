import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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
    private JButton voiceButton;
    private JButton starButton;
    private JLabel loadingPanel;

    public WindowsApp() {
        textInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {changeSearchWord();}
            public void removeUpdate(DocumentEvent e) {changeSearchWord();}
            public void changedUpdate(DocumentEvent e) {}
        });
        textInput.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    translateWord(textInput.getText());
                }
            }
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
        voiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ttsTranslate(labelExplain.getText());
            }
        });
        try {
            Image img = ImageIO.read(getClass().getResource("image/speaker.png"));
            voiceButton.setIcon(new ImageIcon(img));
        } catch (Exception e) { }
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image img = toolkit.createImage("image/loading.gif");
            toolkit.prepareImage(img, -1, -1, null);
            loadingPanel.setIcon(new ImageIcon(img));
        } catch (Exception e) { }

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
            textMeaning.setText("There no word \"" + word + "\" in diktionary");
            textSynonym.setText("There no word \"" + word + "\" in diktionary");
        }
    }

    public void apiTranslate(String word) {
        new Thread() {
            public void run() {
                loadingPanel.setVisible(true);
                String result = apitranslator.apiTranslate(word);
                labelExplain.setText(word);
                textMeaning.setText(result);
                loadingPanel.setVisible(false);
            }
        }.start();
    }

    public void seleTranslate(String word) {
        new Thread() {
            public void run() {
                loadingPanel.setVisible(true);
                seletranslator.seleTranslate(word);
                loadingPanel.setVisible(false);
            }
        }.start();
    }

    public void ttsTranslate(String word) {
        new Thread() {
            public void run() {
                loadingPanel.setVisible(true);
                ttstranslator.ttsSpeak(word);
                loadingPanel.setVisible(false);
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
    public static ttsTranslator ttstranslator = new ttsTranslator();


    public static void main(String[] args) {
        manager.importCsvFile();
        startApplication();
    }
}
