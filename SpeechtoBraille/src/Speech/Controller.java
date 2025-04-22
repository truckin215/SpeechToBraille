// Make sure to add com.assemblyai:assemblyai-java to your dependencies

package speech;
import java.util.List;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.*;

public final class Controller {

    public static void main(String... args) throws Exception {
    	TextProcessor tp = new TextProcessor();
    	//starts recorder
    	tp.record();
    	//running trancriber
    	System.out.println("running transcriber.");
    	List<BrailleChar> brailleList = tp.toBraille(tp.transcribe());
    	//input gathered
    	ActuatorController out = new ActuatorController(brailleList);
    	out.output();
    	
    	System.out.println("program stopped.");
    }
}




