package logic;

import java.io.*;

import javafx.application.Application;
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
		client = new BistroClient("localhost", 5555); // Initialize the client with server details
		try {
			client.openConnection(); // Attempt to open a connection
		} catch (IOException e) {
			System.out.println("Error: Can't setup connection! Terminating client.");
			System.exit(1);
		}
	}
}
