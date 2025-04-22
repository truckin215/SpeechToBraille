package speech;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
import com.pi4j.io.gpio.digital.DigitalState;

public class ActuatorOUT {
	public void SendSignal(int index) {
		Context pi4j = Pi4J.newAutoContext();

		// Step 1: Build the config
		DigitalOutputConfig outputConfig = DigitalOutput.newConfigBuilder(pi4j)            
		    .id("led")
		    .name("LED Blinker")
		    .address(17) // BCM 17 = pin 11 on header
		    .shutdown(DigitalState.LOW)
		    .initial(DigitalState.LOW)
		    .provider("pigpio-digital-output")
		    .build();

		// Step 2: Create the DigitalOutput from the config
		DigitalOutput led = pi4j.create(outputConfig);
	}
}
