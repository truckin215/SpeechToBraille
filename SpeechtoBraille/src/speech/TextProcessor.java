package speech;

import java.io.File;

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

	public void transcribe() throws Exception {
		//Initializes AssemblyAi
        AssemblyAI client = AssemblyAI.builder()
                .apiKey("c790508a0e474bda8ca373af27bae024")
                .build();
        //set params
        var params = TranscriptOptionalParams.builder()
                .speakerLabels(true)
                .build();
        //get audio file
        File audioFile = new File("output/recorded_audio.wav");
        if (!audioFile.exists()) {
            System.err.println("Error: Audio file not found.");
            return;
        }
        Transcript transcript = client.transcripts().transcribe(audioFile, params);

        if (transcript.getStatus() == TranscriptStatus.ERROR) {
            throw new Exception("Transcript failed with error: " + transcript.getError().get());
        }

        System.out.println("Transcript: " + transcript.getText());
	}
}


