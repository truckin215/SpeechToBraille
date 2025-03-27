package speech;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.assemblyai.api.resources.transcripts.types.Transcript;

class AlphaTest {

	@Test
	void test() throws Exception {
		File outputFile = new File("test/AlphaTest.wav");
		TextProcessor tp = new TextProcessor();
		Transcript out = tp.transcribe(outputFile);
		tp.toBraille(out);
		System.out.println("Expected output\n[1, 0]\n[1, 1]\n[0, 0]\n" + "\n[0, 1]\n[1, 0]\n[0, 0]\n " + "\n[0, 0]\n[1, 1]\n[0, 1]" );
		assertEquals('1','1');
	}
		
}


