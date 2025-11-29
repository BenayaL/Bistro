package gui.controllers;

<<<<<<< Updated upstream
public class MainMenuGUI_Controller {

}
=======

import logic.ClientGUI;
import logic.ChatClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import clientserver.ObjectType;
import java.io.IOException;

public class MainMenuGUIController {

	public static Stage mainWindow;
	
	@FXML
	private Button btnShowConnection;
	
	@FXML
	private Button btnShowOrder;
	
	@FXML
	private Button btnUpdateOrder;

	/** 
	 * Method to show connection status when the button is clicked
	 * 
	 * @param event the button click event
	 * @throws IOException if the FXML file cannot be loaded
	 */

	@FXML
		void showConnectionStatus(javafx.event.ActionEvent event) throws IOException {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Connection Status");
		if (ChatClient.isConnected()) {
			alert.setHeaderText("Connected to the server.");
			alert.setContentText("You are currently connected to the server.");
		} else {
			alert.setHeaderText("Not connected to the server.");
			alert.setContentText("You are currently not connected to the server.");
		}
		alert.showAndWait();
	}


	
}
>>>>>>> Stashed changes
