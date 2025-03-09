package speech;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class TextProcessorTest {

	@Test
	void testTranscribe() throws Exception {
		TextProcessor tp = new TextProcessor();
		File outputFile = new File("TextProcessorTestAudio.mp3");
		tp.transcribe(outputFile);
	}
	

}
