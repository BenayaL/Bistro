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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.BistroClient;

public class ServerConnectionFrameController {
	
	// Static client instance for handling server communication.
	public static BistroClient client;
	
	@FXML
	private Button btnExit = null;
	@FXML
	private Button btnSend = null;
	@FXML 
	private Button btnReset = null;
	@FXML
	private Label lblError;
	@FXML
	private TextField txtHost;
	@FXML
	private TextField txtPort;
	
	public ServerConnectionFrameController() {
		super();
	}
	
	public void btnSend(Event event){
		String ip; // holds the entered IP address
		String port; // holds the entered Port
		int intPort; // holds the validated Port as an integer
		ip = txtHost.getText(); // gets the entered IP address
		port = txtPort.getText(); // gets the entered Port
		
		// Validate the input fields
		if (ip.trim().isEmpty()) {
			ServerConnectionFrameController.client.display(lblError, "You must enter an IP Address", Color.RED); // input is empty
		}
		else if (port.trim().isEmpty()) {
			ServerConnectionFrameController.client.display(lblError,"You must enter a port", Color.RED); // input is empty
		}
		else {
			try {
				intPort = Integer.parseInt(txtPort.getText()); // validates that the port only contains digits
			} catch (Exception e) {
				ServerConnectionFrameController.client.display(lblError,"Port must have only digits", Color.RED); // invalid port
				return;
			}
			try {
				// Attempts to create a client instance and connect to the server.
				client = new BistroClient(ip, intPort);
				System.out.println("IP Entered Successfully");

				// Load the home screen if the connection is successful.
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui.fxml/"+ "HomeScreen" +".fxml"));
				Parent root = loader.load();
				HomeScreenController homeScreenController = loader.getController();
				homeScreenController.loadLogo();
				ServerConnectionFrameController.client.switchScreen(loader, root, event, "Home Screen");
				
			} catch (Exception e) {
				// Handles connection errors.
				System.out.println("Error: Can't setup connection! Terminating client.");
				display(lblError,"Can't setup connection", Color.RED); // Displays an error message.
			}
		}
	}
	public void start(Stage primaryStage) {	
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui.fxml/ServerConnectionScreen.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/gui.css/styles.css").toExternalForm());
		primaryStage.setTitle("Server Connection");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	public void exitBtn(Event event) throws Exception {
		System.out.println("Exit Bistro successfully");
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		System.exit(0);
	}
	
	public void display(Label lblError, String message, Color color) {
		lblError.setText(message); // Sets the error message in the label
		lblError.setTextFill(color); // Sets the text color for the error message
	}
}