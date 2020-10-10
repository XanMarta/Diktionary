import com.sun.speech.freetts.*;

public class ttsTranslator {

    private Voice voice = null;

    public ttsTranslator() {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            VoiceManager manager = VoiceManager.getInstance();
            voice = manager.getVoice("kevin16");
            voice.allocate();
        } catch (Exception e) {
            System.out.println("Cannot init TTS. Error: " + e.toString());
        }
    }

    public void ttsSpeak(String word) {
        if (voice != null) {
            voice.speak(word);
        }
    }
}
