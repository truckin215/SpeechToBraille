package speech;
import com.assemblyai.api.resources.transcripts.types.Transcript;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import java.util.List;
import javafx.application.Platform;

public class GUIController {
    boolean isRecording = false;
    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private CheckBox matrixToggle;


    @FXML
    private void handleRecord() throws Exception {
        if (isRecording){
            return;
        }
        isRecording = true;

        inputTextArea.setText("Recording...");

        new Thread(() -> {
            try{
                TextProcessor tp = new TextProcessor();
                //starts recorder
                tp.record();
                //running trancriber
                System.out.println("running transcriber.");
                Transcript script = tp.transcribe();
                Platform.runLater(() -> {
                    inputTextArea.setText(script.getText().orElse(""));
                });
                List<BrailleChar> brailleList = tp.toBraille(script);

                StringBuilder sb = new StringBuilder();

                boolean showMatrix = matrixToggle.isSelected();

                assert brailleList != null;
                for (BrailleChar b : brailleList) {
                    if (showMatrix) {
                        int[][] val = b.getValue();
                        for (int row = 0; row < 3; row++) {
                            sb.append("[")
                                    .append(val[row][0])
                                    .append(", ")
                                    .append(val[row][1])
                                    .append("]\n");
                        }
                        sb.append("\n"); // separate characters
                    } else {
                        sb.append(b.toUnicode());
                    }
                }
                Platform.runLater(() -> {
                    outputTextArea.setText(sb.toString());
                });
                //sending to server
                ActuatorController out = new ActuatorController(brailleList);
                out.output();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                Platform.runLater(() -> isRecording = false);
            }
        }).start();

    }

    public void updateTranscript(String text) {
        inputTextArea.setText(text);
    }
    public void updateOutput(String braille) {
        outputTextArea.setText(braille);
    }
    public void reset() {
        outputTextArea.setText("");
        inputTextArea.setText("");
    }
}
