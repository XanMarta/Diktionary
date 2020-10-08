import com.sun.speech.freetts.*;

public class translateTTS {

    private Voice voice = null;

    public translateTTS() {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            VoiceManager manager = VoiceManager.getInstance();
            voice = manager.getVoice("kevin16");
            voice.allocate();
        } catch (Exception e) {
            System.out.println("Cannot init TTS. Error: " + e.toString());
        }
    }

    public void speakTTS(String word) {
        if (voice != null) {
            voice.speak(word);
        }
    }
}
