package speech;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.assemblyai.api.resources.transcripts.types.Transcript;

class TextProcessorTest {

	@Test
	void testTranscribe() throws Exception {
		File outputFile = new File("test/TextProcessorTestAudio.wav");
		TextProcessor tp = new TextProcessor();
		Transcript out = tp.transcribe(outputFile);
		System.out.println("Expected Output: Transcript: Optional[Hello. This is a Junit test case for speech to Braille.]");
	}
	

}
