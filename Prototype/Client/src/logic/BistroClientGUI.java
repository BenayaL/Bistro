package logic;

import java.io.*;

import gui.controllers.ServerConnectionFrameController;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
 * This class represents the GUI application for the Bistro client.
 */

public class BistroClientGUI extends Application {

	public static BistroClient client; // Static client instance for server communication

	/*
	 * Main method to launch the JavaFX application.
	 * 
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		launch(args); // Launch the JavaFX application
	}
	
	/*
	 * Method to start the JavaFX application and establish a connection to the server.
	 * 
	 * @param primaryStage The primary stage for the application.
	 * @throws Exception If there is an error during startup.
	 */
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ServerConnectionFrameController connectionFrame = new ServerConnectionFrameController(); // Create server connection frame
		// Load the icon image from resources
        Image appIcon = new Image(getClass().getResourceAsStream("/resources/bistroLogoIcon.png")); 
        // Set the icon for the primary stage
        primaryStage.getIcons().add(appIcon); // Create server port frame
		connectionFrame.start(primaryStage); // Start the connection fram
		primaryStage.centerOnScreen(); // Center the primary stage on the screen
		
	}
	
	public void display(Label lblError, String message, Color color) {
		lblError.setText(message); // Sets the error message in the label
		lblError.setTextFill(color); // Sets the text color for the error message
	}
}
