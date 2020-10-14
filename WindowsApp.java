import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Set;


public class WindowsApp {
    private JPanel mainPanel;
    private JPanel bodyPanel;
    private JPanel explainPanel;
    private JLabel targetLabel;
    private JTabbedPane tabExplain;
    private JPanel explainTab;
    private JPanel synonymTab;
    private JPanel imageTab;
    private JPanel buttonPanel;
    private JScrollPane listPanel;
    private JList wordList;
    private JScrollPane scrollExplain;
    private JTextField textInput;
    private JScrollPane textPanel;
    private JLabel textExplain;
    private JLabel textSynonym;
    private JButton translateButton;
    private JButton apiButton;
    private JButton seleButton;
    private JButton voiceButton;
    private JButton starButton;
    private JLabel loadingLabel;
    private JPanel targetPanel;
    private JPanel mainmeanPanel;
    private JLabel mainmeanLabel;
    private JButton adminButton;
    private JLabel i1;
    private JLabel i2;
    private JLabel i3;
    private JLabel i4;
    private JLabel i5;
    private JLabel i6;
    private JLabel i7;
    private JLabel i8;
    private JLabel i9;

    private String password = "password";
    private boolean isShowingImage = false;

    public WindowsApp() {
        textInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {changeSearchWord(getTextInput());}
            public void removeUpdate(DocumentEvent e) {changeSearchWord(getTextInput());}
            public void changedUpdate(DocumentEvent e) {}
        });
        textInput.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    translateWord(getTextInput());
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
                translateWord(getTextInput());
            }
        });
        apiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                apiTranslate(getTextInput());
            }
        });
        seleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleTranslate(getTextInput());
            }
        });
        voiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ttsTranslate(targetLabel.getText());
            }
        });
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adminAccess();
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
            loadingLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) { }
        tabExplain.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (Application.imageScrapter.isReady && tabExplain.getSelectedIndex() == 2) {
                    if (!isShowingImage) {
                        imageScrapt(targetLabel.getText());
                        isShowingImage = true;
                    }
                }
            }
        });

        listPanel.getVerticalScrollBar().setUnitIncrement(20);
        listPanel.getHorizontalScrollBar().setUnitIncrement(20);
        scrollExplain.getVerticalScrollBar().setUnitIncrement(20);
        scrollExplain.getHorizontalScrollBar().setUnitIncrement(20);

        changeSearchWord("");

    }


    public String getTextInput() {
        return textInput.getText().toLowerCase();
    }

    public void changeSearchWord(String word) {
        DefaultListModel<String> model = new DefaultListModel<>();
        Set<String> wordHint = Application.manager.getWordHint(word);
        model.addAll(wordHint);
        wordList.setModel(model);
    }

    public void translateWord(String word) {
        if (!targetLabel.getText().equals(word)) {
            targetLabel.setText(word);
            tabExplain.setSelectedIndex(0);
            if (Application.dictionary.word.containsKey(word)) {
                Word target = Application.dictionary.word.get(word);
                mainmeanLabel.setText(target.word_mainmean);
                textExplain.setText(ConsoleC.textToHtml(target.word_explain, 768));
                textSynonym.setText(ConsoleC.textToHtml(target.word_synonyms, 768));
                isShowingImage = false;
            } else {
                textExplain.setText("There no word \"" + word + "\" in diktionary");
                textSynonym.setText("There no word \"" + word + "\" in diktionary");
            }
        }
    }

    public void apiTranslate(String word) {
        new Thread() {
            public void run() {
                loadingLabel.setVisible(true);
                String result = Application.apitranslator.apiTranslate(word);
                targetLabel.setText(word);
                textExplain.setText(result);
                loadingLabel.setVisible(false);
            }
        }.start();
    }

    public void seleTranslate(String word) {
        new Thread() {
            public void run() {
                loadingLabel.setVisible(true);
                Application.seletranslator.seleTranslate(word);
                loadingLabel.setVisible(false);
            }
        }.start();
    }

    public void ttsTranslate(String word) {
        new Thread() {
            public void run() {
                loadingLabel.setVisible(true);
                Application.ttstranslator.ttsSpeak(word);
                loadingLabel.setVisible(false);
            }
        }.start();
    }

    public void imageScrapt(String word) {
        Thread thread = Application.imageThread;
        if (thread != null) {
            thread.interrupt();
        }
        thread = new Thread() {
            public void run() {
                Component[] components = imageTab.getComponents();
                for (Component component : components) {
                    ((JLabel)component).setIcon(null);
                }
                BufferedImage[] image = Application.imageScrapter.getImage(word, 9);
                int componentCount = 1;
                for (Component component : components) {
                    try {
                        JLabel label = (JLabel)component;
                        label.setIcon(new ImageIcon(image[componentCount]));
                        componentCount += 1;
                    } catch (Exception e) { }
                }
            }
        };
        thread.start();
    }

    public void adminAccess() {
        boolean isAccess = false;
        while (true) {
            String input = JOptionPane.showInputDialog(mainPanel, "Type password to enter Admin Mode");
            if (input == null) {
                break;
            }
            if (input.equals(password)) {
                JOptionPane.showMessageDialog(mainPanel, "You're on!");
                isAccess = true;
                break;
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Wrong password");
            }
        }
        if (isAccess) {
            Application.mainFrame.dispose();
            Application.startApplication(false);
        }
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}
