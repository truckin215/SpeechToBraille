package speech;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//This will communicate with a flask server on the PI
public class ActuatorOUT {
	
	private static final String PI_HOST = "http://ConnorBerrypi.local:5000";
	
	//will send individual signals on the server to raise a pin
	public void signalOut(int pin){
	    try {
	        String endpoint = "/fire/" + pin;
	        @SuppressWarnings("deprecation")
			URL url = new URL(PI_HOST + endpoint);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("GET");

	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuilder content = new StringBuilder();

	        while ((inputLine = in.readLine()) != null)
	            content.append(inputLine);

	        in.close();
	        con.disconnect();

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("failed to send");
	    }
	}

//will lower all pins
	public void resetAll() {
	    try {
	        @SuppressWarnings("deprecation")
			URL url = new URL(PI_HOST + "/reset");
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("GET");
	        con.getInputStream().close();
	        con.disconnect();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("failed to reset");
	    }
	}
}
