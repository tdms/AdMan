import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.JavaClipAudioPlayer;
import com.sun.speech.freetts.Utterance;
import com.sun.speech.freetts.Relation;
import com.sun.speech.freetts.FreeTTSSpeakable;



public class T2SConverter {
	Voice helloVoice;
	Voice[] voices;
	
	T2SConverter()
	{
	       
        /* The VoiceManager manages all the voices for FreeTTS.
         */
        VoiceManager voiceManager = VoiceManager.getInstance();
        System.out.println(voiceManager.toString());
        
        /*
        voices = voiceManager.getVoices();
        System.out.println("Total Voices: " + voices.length);
        
        helloVoice = voices[0];
        */
        
        helloVoice = voiceManager.getVoice("kevin");

        if (helloVoice == null) {
            System.err.println("Cannot find this voice. Please specify a different voice.");
            System.exit(1);
        }
        
        /* Allocates the resources for the voice.
         */
        helloVoice.allocate();
        //speak("hello tanima and taniya. enamul is eating and nirjon is sleeping.");
	} 
	
	public void speak(String s)
	{	
		/* Synthesize speech.
		*/
		helloVoice.speak(s);
	}
	public void clean()
	{       
		helloVoice.deallocate();
	}

}



