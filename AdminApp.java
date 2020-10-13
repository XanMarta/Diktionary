import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdminApp {
    private JPanel mainPanel;
    private JTextField textInput;
    private JLabel labelPanel;
    private JList wordList;
    private JPanel buttonPanel;
    private JPanel wordPanel;
    private JPanel wordExplain;
    private JButton addButton;
    private JButton removeButton;
    private JLabel explainLabel;
    private JTextField explainText;
    private JLabel synonymLabel;
    private JTextField synonymText;
    private JButton normalButton;
    private JScrollPane explainPanel;
    private JScrollPane synonymPanel;
    private JLabel guildText;
    private JPanel bottomPanel;
    private JScrollPane wordlistPanel;
    private JPanel completePanel;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel targetPanel;
    private JPanel mainmeanPanel;
    private JLabel targetLabel;
    private JTextField mainmeanLabel;

    private boolean isEditting = false;
    private boolean isAdding = false;


    public AdminApp() {
        textInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {changeSearchWord(getTextInput());}
            public void removeUpdate(DocumentEvent e) {changeSearchWord(getTextInput());}
            public void changedUpdate(DocumentEvent e) {}
        });
        wordList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!wordList.isSelectionEmpty()) {
                    selectWord((String)wordList.getSelectedValue());
                }
            }
        });
        textInput.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String word = getTextInput();
                if (Application.dictionary.word.containsKey(word)) {
                    selectWord(word);
                } else {
                    addWord();
                }
            }
            }
        });
        explainText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {textChange();}
            public void removeUpdate(DocumentEvent e) {textChange();}
            public void changedUpdate(DocumentEvent e) { }
        });
        synonymText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {textChange();}
            public void removeUpdate(DocumentEvent e) {textChange();}
            public void changedUpdate(DocumentEvent e) { }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isEditting = false;
                isAdding = false;
                targetLabel.setText("Word");
                explainText.setText("");
                synonymText.setText("");
                completePanel.setVisible(false);
                removeButton.setVisible(false);
            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWord();
            }
        });
        normalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(mainPanel, "Are you sure to return to Normal Mode ?",
                        "RETURN", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    returnNormalMode();
                }
            }
        });
        explainPanel.getHorizontalScrollBar().setUnitIncrement(20);
        synonymPanel.getHorizontalScrollBar().setUnitIncrement(20);
        changeSearchWord("");

    }


    public void returnNormalMode() {
        Application.mainFrame.dispose();
        Application.startApplication(true);
    }

    public void addWord() {
        targetLabel.setText(textInput.getText());
        explainText.setText("");
        synonymText.setText("");
        completePanel.setVisible(true);
        addButton.setVisible(false);
        removeButton.setVisible(false);
        isAdding = true;
    }

    public void textChange() {
        if (isEditting) {
            completePanel.setVisible(true);
        }
    }

    public void changeSearchWord(String word) {
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addAll(Application.manager.getWordHint(word));
        wordList.setModel(model);
        addButton.setVisible(!Application.dictionary.word.containsKey(word));
    }

    public void selectWord(String word) {
        isEditting = false;
        addButton.setVisible(false);
        completePanel.setVisible(false);
        targetLabel.setText(word);
        explainText.setText(Application.dictionary.word.get(word).word_explain);
        synonymText.setText(Application.dictionary.word.get(word).word_synonyms);
        isEditting = true;
        isAdding = false;
        removeButton.setVisible(true);
    }

    public String getTextInput() {
        return textInput.getText().toLowerCase();
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}
