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
	@FXML
	private ImageView logoImage; // ImageView for logo
	
	public ServerConnectionFrameController() {
		super();
	}
	
	/*
	 * Method to load the logo image
	 */
	public void loadLogo() {
		logoImage.setImage(new Image("/resources/bistroLogo.png"));
	}
	
	public void btnSend(Event event){
		String ip; // holds the entered IP address
		String port; // holds the entered Port
		int intPort; // holds the validated Port as an integer
		ip = txtHost.getText(); // gets the entered IP address
		port = txtPort.getText(); // gets the entered Port
		
		// Validate the input fields
		if (ip.trim().isEmpty()) {
			BistroClientGUI.client.display(lblError, "You must enter an IP Address", Color.RED); // input is empty
		}
		else if (port.trim().isEmpty()) {
			BistroClientGUI.client.display(lblError,"You must enter a port", Color.RED); // input is empty
		}
		else {
			try {
				intPort = Integer.parseInt(txtPort.getText()); // validates that the port only contains digits
			} catch (Exception e) {
				BistroClientGUI.client.display(lblError,"Port must have only digits", Color.RED); // invalid port
				return;
			}
			try {
				// Attempts to create a client instance and connect to the server.
				BistroClientGUI.client = new BistroClient(ip, intPort);
				
				System.out.println("IP Entered Successfully");

				// Load the home screen if the connection is successful.
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/"+ "HomeScreen" +".fxml"));
				Parent root = loader.load();
				HomeScreenController homeScreenController = loader.getController();
				homeScreenController.loadLogo();
				BistroClientGUI.client.switchScreen(loader, root, event, "Home Screen");
				
			} catch (Exception e) {
				// Handles connection errors
				System.out.println("Error: Can't setup connection! Terminating client. \nThe error message: " + e.getMessage());
				display(lblError,"Can't setup connection", Color.RED); // Displays an error message.
			}
		}
	}
	
	public void start(Stage primaryStage) {	
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/fxml/ServerConnectionScreen.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/gui/css/styles.css").toExternalForm());
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