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
import java.io.IOException;

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
    private JTextField mainmeanText;

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
                clearWordExplain();
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
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String word = targetLabel.getText();
                int result = JOptionPane.showConfirmDialog(mainPanel,
                        "Do you want to remove the word '" + word + "' ?",
                        "REMOVE", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Application.manager.dictionaryDeleteWord(word);
                    removeButton.setVisible(false);
                    clearWordExplain();
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String word = targetLabel.getText();
                String temp = isAdding ? "add" : "change";
                int result = JOptionPane.showConfirmDialog(mainPanel,
                        "Do you want to " + temp + " the word '" + word + "' ?",
                        temp.toUpperCase(), JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Application.manager.dictionaryAddWord(word, mainmeanText.getText(),
                            explainText.getText(), synonymText.getText());
                    completePanel.setVisible(false);
                    isAdding = false;
                }
            }
        });
        try {
            Image img = ImageIO.read(getClass().getResource("image/add.png"));
            addButton.setIcon(new ImageIcon(img));
        } catch (IOException e) { }
        try {
            Image img = ImageIO.read(getClass().getResource("image/remove.png"));
            removeButton.setIcon(new ImageIcon(img));
        } catch (IOException e) { }
        explainPanel.getHorizontalScrollBar().setUnitIncrement(20);
        synonymPanel.getHorizontalScrollBar().setUnitIncrement(20);
        changeSearchWord("");

    }


    public void clearWordExplain(){
        targetLabel.setText("Word");
        mainmeanText.setText("");
        explainText.setText("");
        synonymText.setText("");
        completePanel.setVisible(false);
    }

    public void returnNormalMode() {
        Application.mainFrame.dispose();
        Application.startApplication(true);
    }

    public void addWord() {
        targetLabel.setText(textInput.getText());
        mainmeanText.setText("");
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
        if (word.equals("") || Application.dictionary.word.containsKey(word)) {
            addButton.setVisible(false);
        } else {
            addButton.setVisible(true);
        }
    }

    public void selectWord(String word) {
        isEditting = false;
        addButton.setVisible(false);
        completePanel.setVisible(false);
        targetLabel.setText(word);
        Word target = Application.dictionary.word.get(word);
        mainmeanText.setText(target.word_mainmean);
        explainText.setText(target.word_explain);
        synonymText.setText(target.word_synonyms);
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
