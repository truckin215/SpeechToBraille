package speech;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Make sure to add com.assemblyai:assemblyai-java to your dependencies

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.requests.TranscriptParams;
import com.assemblyai.api.resources.transcripts.types.*;
public final class TextProcessor {

    
    public boolean record() {
    	SpeechListner recorder = new SpeechListner();
    	recorder.rawData();
    	return false;
    }
    public Transcript transcribe() throws Exception {
    	File audioFile = new File("output/recorded_audio.wav");
    	Transcript transcript=transcribe(audioFile);
    	return(transcript);
    }
	public Transcript transcribe(File audioFile) throws Exception {
		//Initializes AssemblyAi
        AssemblyAI client = AssemblyAI.builder()
                .apiKey("c790508a0e474bda8ca373af27bae024")
                .build();
        //set params
        var params = TranscriptOptionalParams.builder()
                .speakerLabels(false)
                .build();
        //get audio file
        if (!audioFile.exists()) {
            System.err.println("Error: Audio file not found.");
            return null;
        }
        Transcript transcript = client.transcripts().transcribe(audioFile, params);

        if (transcript.getStatus() == TranscriptStatus.ERROR) {
            throw new Exception("Transcript failed with error: " + transcript.getError().get());
        }
        System.out.println("Transcript: " + transcript.getText());
        return(transcript);
	}
	//Convert Transcript To braille
	public void toBraille(Transcript transcript){
		//error handling
	    if (transcript == null || transcript.getText() == null) {
	        System.err.println("Transcript broke.");
	        return;
	    }
	    //save transcript
	    char[] script = transcript.getText().get().toLowerCase().toCharArray();
	    //array of BrailleChar Objects
	    List<BrailleChar> brailleList = new ArrayList<>();
	    //runs through each character
	    for (int i=0; i<script.length;i++){
	    	brailleList.add(new BrailleChar(script[i]));
	    }
	}
}


