package logic;

import clientserver.Message;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import ocsf.client.*;

public class BistroClient extends AbstractClient {

	public static Message messageFromServer;
	public static boolean awaitResponse = false;

	public BistroClient(String host, int port) throws Exception {
		super(host, port);
		try {
			openConnection(); // Attempt to open a connection
		} catch (IOException exception) {
			throw new Exception(); // Handle connection errors
		}
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		BistroClient.messageFromServer = (Message) msg; // Update static message variable
		awaitResponse = false; // Set response status to false
	}

	public void handleMessageFromClientUI(Object message) {
		try {
			awaitResponse = true; // Indicate that a response is awaited
			sendToServer(message); // Send the message to the server
			// Wait for a response in a loop
			while (awaitResponse) {
				try {
					Thread.sleep(100); // Avoid busy-waiting
				} catch (InterruptedException e) {
					e.printStackTrace(); // Handle interruptions
				}
			}
		} catch (IOException e) {
			e.printStackTrace(); // Handle errors during message sending
			System.out.println("Could not send message to server: Terminating client." + e);
			quit(); // Terminate the client
		}
	}

	public void switchScreen(FXMLLoader loader, Parent root, Event event, String string) {
		Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
		Scene newScene = new Scene(root);
		newScene.getStylesheets().add(getClass().getResource("/gui.css/styles.css").toExternalForm());
		currentStage.setTitle(string);
		currentStage.setScene(newScene);
		currentStage.show();
	}

	public void display(Label lblError, String message, Color color) {
		lblError.setText(message); // Sets the error message in the label.
		lblError.setTextFill(color); // Sets the text color for the error message.
	}

	public void quit() {
		try {
			closeConnection(); // Close the connection
		} catch (IOException e) {
			e.printStackTrace(); // Handle errors during disconnection
			System.out.println("Error: Could not close connection properly." + e);
		}
		System.exit(0); // Exit the program
	}
}