import javax.swing.*;
import java.awt.event.*;
import com.sun.speech.freetts.*;
import javax.speech.*;

public class TextToSpeech extends JFrame {
    //private JPanel mainPanel;
    private JTextField speakTextField;
    private JPanel panel1;
    private JButton button1;
    //private static final String VoiceName = "mbrola_us1";
    private static final String VoiceName1 = "kevin16";
    public TextToSpeech(String title) {
        //@Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "con card");
//            }

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///home/jim/mbrola
                //C:\TextToSpeechJava\freetts-1.2.2-bin\freetts-1.2\lib\mbrola
                System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
                //TextToSpeech tts = new TextToSpeech("mbrola_us1");
                VoiceManager vm = VoiceManager.getInstance();
                Voice voice = vm.getVoice(VoiceName1);
                voice.allocate();
                try {
                    voice.speak(speakTextField.getText());
                } catch(Exception error) {
                    System.out.println("NOOOO");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new TextToSpeech("BLah");
        frame.setContentPane(new TextToSpeech("Blah").panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
