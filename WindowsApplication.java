import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WindowsApplication {
    
    static final int WINDOW_WIDTH = 1024;
    static final int WINDOW_HEIGHT = 768;
    static JFrame frame;

    public void createWindow() {
        // Window
        frame = new JFrame("DIKTIONARY");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Ground Panel
        JPanel groundPanel = new JPanel();
        groundPanel.setLayout(new BorderLayout());
        groundPanel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.add(groundPanel, BorderLayout.CENTER);

        // Ground Panel / Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        GridBagConstraints c = new GridBagConstraints();

        // Ground Panel / Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setSize(768, WINDOW_HEIGHT);
        leftPanel.setBackground(new Color(230, 102, 132));
        c.fill = GridBagConstraints.HORIZONTAL;
        controlPanel.add(leftPanel, c);

        // Ground Panel / Right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(256, WINDOW_HEIGHT));
        rightPanel.setBackground(new Color(235, 145, 187));
        controlPanel.add(rightPanel, c);

        // Ground Panel / Right Panel / Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setPreferredSize(new Dimension(100, 50));
        rightPanel.add(inputPanel, BorderLayout.NORTH);

        // Ground Panel / Right Panel / Input Panel / Text Input
        JTextField textInput = new JTextField();
        inputPanel.add(textInput, BorderLayout.CENTER);

        // Ground Panel / Right Panel / Choose Panel

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        WindowsApplication app = new WindowsApplication();
        app.createWindow();
    }

}