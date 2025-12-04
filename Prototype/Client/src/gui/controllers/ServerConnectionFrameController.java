package gui.controllers;


import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.BistroClient;
import logic.BistroClientGUI;

public class ServerConnectionFrameController {
	
	@FXML
	private Button btnExit; // Exit button
	@FXML
	private Button btnSend; // Send button
	@FXML 
	private Button btnReset; // Reset button
	@FXML
	private Label lblError; // Label for displaying error messages
	@FXML
	private TextField txtHost; // TextField for entering the host IP address
	@FXML
	private TextField txtPort; // TextField for entering the port number
	
	// Constructor
	public ServerConnectionFrameController() {
		super();
	}
	
	/*
	 * Method to handle the Send button click event.
	 * Validates the input fields and attempts to connect to the server.
	 */
	@FXML
	public void btnSend(Event event){
		String ip; // holds the entered IP address
		String port; // holds the entered Port
		int intPort; // holds the validated Port as an integer
		ip = txtHost.getText(); // gets the entered IP address
		port = txtPort.getText(); // gets the entered Port
		
		// Validate the input fields
		if (ip.trim().isEmpty()) {
			display(lblError, "You must enter an IP Address", Color.RED); // input is empty
		}
		else if (port.trim().isEmpty()) {
			display(lblError,"You must enter a port", Color.RED); // input is empty
		}
		else {
			try {
				intPort = Integer.parseInt(txtPort.getText()); // validates that the port only contains digits
			} catch (Exception e) {
				display(lblError,"Port must have only digits", Color.RED); // invalid port
				return;
			}
			try {
				// Attempts to create a client instance and connect to the server.
				BistroClientGUI.client = new BistroClient(ip, intPort);
				
				System.out.println("IP Entered Successfully");

				// Load the home screen if the connection is successful.
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/"+ "HomeScreen" +".fxml"));
				Parent root = loader.load();
				Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
				stage.centerOnScreen();
				BistroClientGUI.client.switchScreen(loader, root, event, "Home Screen");
				
				
				
			} catch (Exception e) {
				// Handles connection errors
				System.out.println("Error: Can't setup connection! \nThe error message: ");
				e.printStackTrace();
				display(lblError,"Can't setup connection", Color.RED); // Displays an error message.
				
			}
		}
	}
	
	/*
	 * Method to handle the Reset button click event.
	 * Clears the input fields and error messages.
	 * 
	 * @param event The event that triggered the method.
	 */
	@FXML
	public void btnReset(Event event) {
	    txtHost.clear();
	    txtPort.clear();
	    lblError.setText("");
	}
	
	/*
	 * Method to handle the Exit button click event.
	 * Closes the application.
	 * 
	 * @param event The event that triggered the method.
	 * @throws Exception If there is an error during exit.
	 */
	@FXML
	public void btnExit(Event event) throws Exception {
		System.out.println("Exit Bistro successfully");
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		System.exit(0);
	}
	
	/*
	 * Method to start the Server Connection screen.
	 * 
	 * @param primaryStage The primary stage for the application.
	 */
	public void start(Stage primaryStage) {	
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/fxml/ServerConnectionScreen.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		Scene scene = new Scene(root);
		primaryStage.setTitle("Server Connection");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();		
	}
	
	/*
	 * Method to display an error message or messages in a label with a specified color.
	 * 
	 * @param lblError The label to display the error message / message .
	 * @param message The error message to be displayed.
	 * @param color The color of the error message text.
	 */
	public void display(Label lblError, String message, Color color) {
		lblError.setText(message); // Sets the error message in the label
		lblError.setTextFill(color); // Sets the text color for the error message
	}
}