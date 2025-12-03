package logic;
import gui.controllers.ServerPortFrameController;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ServerGUI extends Application {
	
	public static BistroServer server; // Static server instance for client communication
	
	
	public static void main(String[] args) {
		launch(args); // Launch the JavaFX application
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ServerPortFrameController portFrame = new ServerPortFrameController(); // Create server port frame
		portFrame.start(primaryStage); // Start the port frame
		
	}
	
	public void display(Label lblError, String message, Color color) {
		lblError.setText(message); // Sets the error message in the label
		lblError.setTextFill(color); // Sets the text color for the error message
	}
	
	
}
