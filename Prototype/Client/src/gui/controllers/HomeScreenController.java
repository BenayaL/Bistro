package gui.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.BistroClientGUI;
import clientserver.Message;
import java.io.IOException;

public class HomeScreenController {
	
	@FXML
	private Button btnViewOrders; //Button to view orders
	
	@FXML
	private Button btnUpdateOrder; //Button to update orders
	
	@FXML
	private Button btnConnectionStatus; //Button to check connection status
	
	@FXML
	private ImageView logoImage; //ImageView for logo
	
	
	/*
	 * Method to load the logo image
	 */
	public void loadLogo() {
		logoImage.setImage(new Image("/images/bistroLogo.png"));
	}
	
	/*
	 * Method to handle View Orders button click and load the update order screen
	 */
	public void viewOrders(Event event) {
		try {
			Parent loadViewOrders= FXMLLoader.load(getClass().getResource("/gui/fxml/ViewOrderScreen.fxml"));
			Scene viewOrdersScene = new Scene(loadViewOrders);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			BistroClientGUI.client.swichScreen(loadViewOrders, viewOrdersScene, event, window);
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}
	
}
