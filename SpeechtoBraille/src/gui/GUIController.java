package gui;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class GUIController {
    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    public void updateTranscript(String text, String braille) {
        inputTextArea.setText(text);
    }
    public void updateOutput(String text, String braille) {
        outputTextArea.setText(text);
    }
}
