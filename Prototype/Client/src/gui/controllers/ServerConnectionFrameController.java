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
	private Button btnExit;
	@FXML
	private Button btnSend;
	@FXML 
	private Button btnReset;
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
				BistroClientGUI.client.switchScreen(loader, root, event, "Home Screen");
				stage.centerOnScreen();
				
				
			} catch (Exception e) {
				// Handles connection errors
				System.out.println("Error: Can't setup connection! \nThe error message: ");
				e.printStackTrace();
				display(lblError,"Can't setup connection", Color.RED); // Displays an error message.
				
			}
		}
	}
	
	@FXML
	public void btnReset(Event event) {
	    txtHost.clear();
	    txtPort.clear();
	    lblError.setText("");
	}
	
	@FXML
	public void btnExit(Event event) throws Exception {
		System.out.println("Exit Bistro successfully");
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		System.exit(0);
	}
	
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
	
	public void display(Label lblError, String message, Color color) {
		lblError.setText(message); // Sets the error message in the label
		lblError.setTextFill(color); // Sets the text color for the error message
	}
}