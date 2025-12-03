package logic;

import clientserver.Message;
import entities.Order;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ocsf.client.*;

/*
 * This class represents a client that connects to a Bistro server.
 */

public class BistroClient extends AbstractClient {

	public static Message messageFromServer;
	public static boolean awaitResponse = false;

	/*
	 * Constructor to initialize the BistroClient with the server's host and port
	 * 
	 * @param host The server's hostname or IP address
	 * @param port The server's port number
	 * @throws Exception If there is an error connecting to the server
	 */
	public BistroClient(String host, int port) throws Exception {
	    super(host, port);
	    try {
	        openConnection(); // Attempt to open a connection
	    } catch (IOException e) {
	        throw new Exception("Could not connect to server at " + host + ":" + port, e);
	    }
	}
	
	/*
	 * Method to handle messages received from the server.
	 * 
	 * @param msg The message received from the server.
	 */
	@Override
	protected void handleMessageFromServer(Object msg) {
		BistroClient.messageFromServer = (Message) msg; // Update static message variable
		awaitResponse = false; // Set response status to false
	}
	
	/*
	 * Method to handle messages sent from the client UI to the server.
	 * 
	 * @param message The message to be sent to the server.
	 */
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
			System.exit(0);
		}
	}
	
	/*
	 * Method to switch the current screen to a new screen.
	 * 
	 * @param loader The FXMLLoader for the new screen.
	 * @param root The root node of the new screen.
	 * @param event The event that triggered the screen switch.
	 * @param string The title for the new screen.
	 */
	public void switchScreen(FXMLLoader loader, Parent root, Event event, String string) {
		Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
		Scene newScene = new Scene(root);
		currentStage.setTitle(string);
		currentStage.setScene(newScene);
		currentStage.show();
		
	}
	
	/*
	 * Method to retrieve the list of orders from the server.
	 * 
	 * @return A list of Order objects retrieved from the server.
	 */
	
	@SuppressWarnings("unchecked")
	public List<Order> getOrdersListFromServer() {
		messageFromServer = new Message("getOrdersList", null);
		handleMessageFromClientUI(messageFromServer);
		if ("ordersList".equals(messageFromServer.getId())) {
	        return (List<Order>) messageFromServer.getData();
		}
		return null;
		
	}
	
	/*
	 * Method to send an order update request to the server.
	 * 
	 * @param orderUpdateData The data for updating the order.
	 * @return The response ID from the server after processing the update request.
	 */
	public String sendOrderUpdateRequest(ArrayList<Object> orderUpdateData) {
		handleMessageFromClientUI(new Message("updateOrderStatus",orderUpdateData));
		return messageFromServer.getId();
	}

	/*
	 * Method to display an error message in a label with a specified color.
	 * 
	 * @param lblError The label to display the error message.
	 * @param message The error message to be displayed.
	 * @param color The color of the error message text.
	 */
	public void display(Label lblError, String message, Color color) {
		lblError.setText(message); // Sets the error message in the label.
		lblError.setTextFill(color); // Sets the text color for the error message.
	}
	
	/*
	 * Method to terminate the client and close the connection.
	 */
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
