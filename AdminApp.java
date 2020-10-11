import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AdminApp {
    private JPanel mainPanel;
    private JTextField textInput;
    private JLabel labelPanel;
    private JList wordList;
    private JPanel buttonPanel;
    private JPanel wordPanel;
    private JPanel explainPanel;
    private JLabel wordLabel;
    private JButton addButton;
    private JButton removeButton;
    private JLabel meaningP;
    private JTextField meaningText;
    private JLabel synonymP;
    private JTextField synonymText;
    private JButton normalButton;
    private JScrollPane meaningPanel;
    private JScrollPane synonymPanel;
    private JLabel guildText;
    private JPanel bottomPanel;
    private JScrollPane wordlistPanel;
    private JPanel completePanel;
    private JButton saveButton;
    private JButton cancelButton;

    private boolean isEditting = false;


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
                    }
                }
            }
        });
        meaningText.getDocument().addDocumentListener(new DocumentListener() {
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
                wordLabel.setText("Word");
                meaningText.setText("");
                synonymText.setText("");
                completePanel.setVisible(false);
                removeButton.setVisible(false);
            }
        });
        meaningPanel.getHorizontalScrollBar().setUnitIncrement(20);
        synonymPanel.getHorizontalScrollBar().setUnitIncrement(20);
        changeSearchWord("");

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
        wordLabel.setText(word);
        meaningText.setText(Application.dictionary.word.get(word).word_explain);
        synonymText.setText(Application.dictionary.word.get(word).word_synonyms);
        isEditting = true;
        removeButton.setVisible(true);
    }

    public String getTextInput() {
        return textInput.getText().toLowerCase();
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}
