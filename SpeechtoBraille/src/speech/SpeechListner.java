package speech;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SpeechListner {
	//this var is being used by a thread that runs a timer
	public boolean stopped = false;
	//Explains most the code in this class:
	//https://docs.oracle.com/javase/tutorial/sound/capturing.html 
	public void rawData() {
		//set record length currently 10 seconds
		int durationMs = 10000;
		//defining audio format
		AudioFormat format = new AudioFormat(
				//feel free to mess with quality settings if it sounds off
			    AudioFormat.Encoding.PCM_SIGNED, // Encoding: Signed PCM
			    16000.0f,  // Sample Rate: 16 kHz
			    16,        // Sample Size: 16-bit
			    1,         // Channels: Mono
			    2,         // Frame Size: 16-bit / 8 = 2 bytes per frame
			    16000.0f,  // Frame Rate: Matches sample rate
			    false      
			);
		TargetDataLine line = null; // might need to create fix later
		DataLine.Info info = new DataLine.Info(TargetDataLine.class,
		                format); // format is an AudioFormat object
		if (!AudioSystem.isLineSupported(info)) {
		    // Handle the error

		}
		// Obtain and open the line.
		try {
		    line = (TargetDataLine) AudioSystem.getLine(info);
		    line.open(format);
		} catch (LineUnavailableException ex) {
		    // Handle the error
		}

		// Assume that the TargetDataLine, line, has already
		// been obtained and opened.
		ByteArrayOutputStream out  = new ByteArrayOutputStream();
		int numBytesRead;
		byte[] data = new byte[line.getBufferSize() / 5];
		
		startTimer(durationMs);

		// Begin audio capture. add null check later ;p
		line.start();

		System.out.println("Recording started.");
		// Here, stopped is a global boolean set by another thread.
		while (!stopped) {
		    // Read the next chunk of data from the TargetDataLine.
		    numBytesRead =  line.read(data, 0, data.length);
		    // Save this chunk of data.
		    out.write(data, 0, numBytesRead);
		}
        System.out.println("Recording stopped.");
        line.stop();
        line.close();
        
        saveAudio(out, format);
	}
	
	//this a timer thread that will flip the stopped variable after 10 seconds
    public void startTimer(int durationMs) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                stopped = true; // Stops recording
            }
        }, durationMs );
    }
    // save audio for testing purposes
    private void saveAudio(ByteArrayOutputStream out, AudioFormat format) {
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(out.toByteArray());
        AudioInputStream audioInputStream = new AudioInputStream(byteInputStream, format, out.size());
        //can change were it outputs here
        File outputDir = new File("output");
        File outputFile = new File(outputDir,"recorded_audio.wav");
        if (!outputDir.exists()) {
            outputDir.mkdirs(); // creates the directory and any missing parent dirs
        }
        try {
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, outputFile);
            System.out.println("Recording saved as " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save audio file: " + e.getMessage());
        }
    }
}
