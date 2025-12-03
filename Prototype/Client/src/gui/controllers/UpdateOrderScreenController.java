package gui.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import clientserver.Message;
import entities.Order;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import logic.BistroClientGUI;

public class UpdateOrderScreenController {
	
	@FXML
	private Button btnUpdate; // Button to update the order
	
	@FXML
	private Button btnReset; // Button to reset the form
	
	@FXML
	private Button btnHome; // Button to return to home screen
	
	@FXML
	private Label lblError; // Label for displaying errors
	
	@FXML
	private TextField txtConfirmCode; // TextField for confirmation code
	
	@FXML
	private DatePicker dpOrderDate; // DatePicker for order date
	
	@FXML
	private TextField txtNumberOfGuests; // TextField for number of guests
	
	/*
	 * Method to handle Home button click and load the home screen
	 * 
	 * @param event The event triggered by clicking the Home button
	 */
	@FXML
	public void btnHome(Event event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/HomeScreen.fxml"));
		try {
			loader.load();
			Parent root = loader.getRoot();
			BistroClientGUI.client.switchScreen(loader, root, event, "Home Screen");
		} catch (IOException e) {
			System.out.println("Error: Can't load Home Screen");
			BistroClientGUI.client.display(lblError, "Can't load Screen!", Color.RED);
		}
	}
	
	/*
	 * Method to handle Update button click and send order update request to server
	 * 
	 * @param event The event triggered by clicking the Update button
	 */
	@FXML
	public void btnUpdate(Event event) {
		int confirmCode;
		LocalDate orderDate = dpOrderDate.getValue();
		int numberOfGuests;
		
		// Validate confirmation code input
		try {
			confirmCode = Integer.parseInt(txtConfirmCode.getText());
		}catch (NumberFormatException e) {
			lblError.setText("Confirmation code must be a number.");
			return;
		} 
		try {
			numberOfGuests = Integer.parseInt(txtNumberOfGuests.getText());
			}catch (NumberFormatException e) {
				lblError.setText("Number of guests must be a number.");
				return;
			}
		// Prepare data for order update request
		ArrayList<Object> orderUpdateData = new ArrayList<>();
		orderUpdateData.add(confirmCode);
		orderUpdateData.add(orderDate);
		orderUpdateData.add(numberOfGuests);
		// Send order update request to server and handle response
		switch (BistroClientGUI.client.sendOrderUpdateRequest(orderUpdateData)) {
		case "updateOrderSuccess":
			System.out.println("Order updated successfully on server.");
			BistroClientGUI.client.display(lblError, "Order updated successfully.", Color.GREEN);
			break;
		case "dateNotAvailable":
			System.out.println("The specified date is not available.");
			BistroClientGUI.client.display(lblError, "The specified date is not available.", Color.RED);
		case "invalidConfirmCode":
			System.out.println("Invalid confirmation code provided.");
			BistroClientGUI.client.display(lblError, "Invalid confirmation code.", Color.RED);
			break;
		default:
			System.out.println("Unexpected response from server.");
			BistroClientGUI.client.display(lblError, "Unexpected error occurred.", Color.RED);
			break;
		}
	}
	
	/*
	 * Method to handle Reset button click and clear the form fields
	 * 
	 * @param event The event triggered by clicking the Reset button
	 */
	@FXML
	public void btnReset(Event event) {
		txtConfirmCode.clear();
		dpOrderDate.setValue(null);
		txtNumberOfGuests.clear();
	    lblError.setText("");
	}
	
}
