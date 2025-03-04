// Make sure to add com.assemblyai:assemblyai-java to your dependencies

package speech;
import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.*;

public final class Controller {

    public static void main(String... args) throws Exception {
    	TextProcessor tp = new TextProcessor();
    	//starts recorder
    	tp.record();
    	System.out.println("program stopped.");
    }
}




