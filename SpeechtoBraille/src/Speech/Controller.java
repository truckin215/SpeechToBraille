// Make sure to add com.assemblyai:assemblyai-java to your dependencies

package speech;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Controller extends Application {
	//instance of GUI
	public static GUIController controller;

    public static void main(String... args) throws Exception {
		launch(args);
		/*
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
    	*/
    }

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/gui.fxml"));
		System.out.println("FXML location: " + getClass().getResource("/gui/gui.fxml"));
		Parent root = loader.load();

		controller = loader.getController();

		stage.setScene(new Scene(root));
		stage.setTitle("Speech to Braille");
		stage.show();
		// Run speech logic in background
	}
}




