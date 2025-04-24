package speech;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
			    		//17 is a offset to use right pins on the pi
	                    int index = col * 3 + row;
	                    out.signalOut(index);
			    	}
			    }
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//resets pins
			out.resetAll();
		}
	}
}
