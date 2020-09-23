import java.awt.Color;

import javax.swing.*;

public class WindowsApplication {
    
    static final int WINDOW_WIDTH = 1024;
    static final int WINDOW_HEIGHT = 768;
    static JFrame frame;

    public void createWindow() {
        // Window
        frame = new JFrame("DIKTIONARY");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Panel
        JPanel groundPanel = new JPanel();
        groundPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        groundPanel.setBackground(Color.cyan);
        frame.add(groundPanel);

        // Left Panel: Explain words
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 768, WINDOW_HEIGHT);
        leftPanel.setBackground(Color.white);
        groundPanel.add(leftPanel);

        // Right Panel: Search words
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(768, 0, 256, WINDOW_HEIGHT);
        rightPanel.setBackground(Color.blue);
        groundPanel.add(rightPanel);
    }

    public static void main(String[] args) {
        WindowsApplication app = new WindowsApplication();
        app.createWindow();
    }

}