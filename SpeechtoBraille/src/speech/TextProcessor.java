package speech;

// Make sure to add com.assemblyai:assemblyai-java to your dependencies

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.requests.TranscriptParams;
import com.assemblyai.api.resources.transcripts.types.*;
public final class TextProcessor {

   // public TextProcessor() throws Exception {
    	/*
        AssemblyAI client = AssemblyAI.builder()
                .apiKey("4531fb0bfce74443ab637fe7a8e73c28")
                .build();

        Transcript transcript = client.transcripts().transcribe("https://assembly.ai/news.mp4");

        if (transcript.getStatus() == TranscriptStatus.ERROR) {
            throw new Exception("Transcript failed with error: " + transcript.getError().get());
        }

        System.out.println("Transcript: " + transcript);
    }
    */
    
    public boolean record() {
    	SpeechListner recorder = new SpeechListner();
    	recorder.rawData();
    	return false;
    }
}


