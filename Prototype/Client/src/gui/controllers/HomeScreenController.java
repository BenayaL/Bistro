package gui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import logic.BistroClientGUI;
import clientserver.Message;
import java.io.IOException;

public class HomeScreenController {

	@FXML
	private Button btnViewOrders; // Button to view orders

	@FXML
	private Button btnUpdateOrder; // Button to update orders

	@FXML
	private Button btnConnectionStatus; // Button to check connection status

	@FXML
	private Button btnExit; // Button to exit the application

	@FXML
	private ImageView logoImage; // ImageView for logo

	@FXML
	private Label lblError; // Label for displaying errors

	@FXML
	private BorderPane mainPane; // HomeScreen main pane

	@FXML
	private StackPane modalOverlay; // Overlay pane for modals
	
	@FXML
	private Rectangle overlayBackground;

	private Parent connectionStatusRoot; // Root node for connection status window

	private ConnectionStatusWindowController connectionStatusCTRL; // Controller for connection status window
	

	/*
	 * Method to handle View Orders button click and load the update order screen
	 * 
	 * @param event The action event triggered by clicking the button.
	 */
	@FXML
	public void btnViewOrders(Event event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/" + "ViewOrdersScreen" + ".fxml"));
		try {
			Parent root = loader.load();
			
			BistroClientGUI.client.switchScreen(loader, root, event, "ViewOrders Screen");
			
		} catch (IOException e) {
			System.out.println("Error: Can't load ViewOrders Screen: ");
			e.printStackTrace();
			BistroClientGUI.client.display(lblError, "Can't load Screen!", Color.RED); // Displays an error message.
		}
	}

	/*
	 * Method to handle Update Order button click and load the update order screen
	 * 
	 * @param event The action event triggered by clicking the button.
	 */
	@FXML
	public void btnUpdateOrder(Event event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/" + "UpdateOrderScreen" + ".fxml"));
		try {
			Parent root = loader.load();
			Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			stage.centerOnScreen();
			BistroClientGUI.client.switchScreen(loader, root, event, "Update Order");
			
			
		} catch (IOException e) {
			System.out.println("Error: Can't load UpdateOrder Screen");
			BistroClientGUI.client.display(lblError, "Can't load Screen!", Color.RED); // Displays an error message.
		}
	}

	/*
	 * Method to handle Connection Status button click and show connection status
	 * 
	 * @param event The action event triggered by clicking the button.
	 */
	@FXML
	public void btnConnectionStatus(ActionEvent event) {
		String host = BistroClientGUI.client.getHost(); // Get the host IP
		int port = BistroClientGUI.client.getPort(); // Get the port number

		// if the connection status window is not already loaded, load it
		if (connectionStatusRoot == null) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/ConnectionStatusWindow.fxml"));
			try {
				connectionStatusRoot = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
				BistroClientGUI.client.display(lblError, "Can't open Connection Status", Color.RED);
				return;
			}
			connectionStatusCTRL = loader.getController();
			connectionStatusCTRL.setParent(this);
			// Add the connection status root to the modal overlay for blur effect
			modalOverlay.getChildren().add(connectionStatusRoot);
		}
			modalOverlay.setVisible(true);
			modalOverlay.setManaged(true);
			mainPane.setEffect(new GaussianBlur(15));
			if (host == null) {
				connectionStatusCTRL.setConnectionDetails("Not connected", 0, "Disconnected", Color.RED);
			} else {
				connectionStatusCTRL.setConnectionDetails(host, port, "Connected", Color.GREEN);
			}
	}

	/*
	 * Method to close the connection status modal and remove blur effect
	 */
	public void closeConnectionStatus() {
		modalOverlay.setVisible(false);
		modalOverlay.setManaged(false);
		mainPane.setEffect(null);
	}

	/*
	 * Method to handle Exit button click and terminate the client connection
	 * 
	 * @param event The action event triggered by clicking the button.
	 */
	public void btnExit(Event event) {
		System.out.println("Exit Bistro Client successfully");
		BistroClientGUI.client.notifyServerOnExit();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		BistroClientGUI.client.quit();
		Platform.exit();
		System.exit(0);
	}

}
