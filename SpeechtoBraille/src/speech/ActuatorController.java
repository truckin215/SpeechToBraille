package speech;
import java.util.Arrays;
import java.util.List;

//import for Pi GPIO controller
import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.*;

public class ActuatorController {
	List<BrailleChar> brailleList;
	
	ActuatorController(List<BrailleChar> braille){
		this.brailleList=braille;
	}
	
	public void output() {
		ActuatorOUT out = new ActuatorOUT();
		for (int brailleChar=0; brailleChar<brailleList.size();brailleChar++) {
			int[][] value = brailleList.get(brailleChar).getValue();
			//splits character into raw values
			for (int row = 0; row < 3; row++) {
			    for (int col = 0; col < 2; col++) {
			    	if (value[row][col]==1) {
	                    int index = col * 3 + row;
	                    out.SendSignal(index); 
			    	}
			    }
			}
		}
	}
}
