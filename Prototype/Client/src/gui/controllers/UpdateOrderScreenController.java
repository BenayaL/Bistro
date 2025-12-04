package gui.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

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
	private Button btnCheckCode; // Button to check confirmation code
	
	@FXML
	private Label lblError; // Label for displaying errors
	
	@FXML
	private TextField txtConfirmCode; // TextField for confirmation code
	
	@FXML
	private DatePicker dpOrderDate; // DatePicker for order date
	
	@FXML
	private TextField txtNumberOfGuests; // TextField for number of guests
	
	@FXML
	private TextField txtMemberId; // TextField for member ID (read-only)
	
	@FXML
	private TextField txtOrderDatePlaced; // TextField for date order was placed (read-only)

	// The currently loaded order (after a successful check)
	private Order currentOrder;
	
	
	/**
	 * Initialize UI state.
	 * Disable fields that should only be used after a valid confirmation code.
	 */
	@FXML
	public void initialize() {
		// These are only editable after a successful confirmation code check
		dpOrderDate.setDisable(true);
		txtNumberOfGuests.setDisable(true);
		btnUpdate.setDisable(true);

		// Always read-only fields (display only)
		if (txtMemberId != null) {
			txtMemberId.setEditable(false);
			txtMemberId.setFocusTraversable(false);
		}
		if (txtOrderDatePlaced != null) {
			txtOrderDatePlaced.setEditable(false);
			txtOrderDatePlaced.setFocusTraversable(false);
		}
	}
	
	/*
	 * Method to handle Home button click and load the home screen
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
	 */
	@FXML
	public void btnUpdate(Event event) {
		if (currentOrder == null) {
			lblError.setText("You must load an order first.");
			return;
		}

		int confirmCode;
		LocalDate orderDateLocal = dpOrderDate.getValue();
		int numberOfGuests;

		// Validate confirmation code input
		try {
			confirmCode = Integer.parseInt(txtConfirmCode.getText().trim());
		} catch (NumberFormatException e) {
			lblError.setText("Confirmation code must be a number.");
			return;
		}

		// Validate number of guests input
		try {
			numberOfGuests = Integer.parseInt(txtNumberOfGuests.getText().trim());
		} catch (NumberFormatException e) {
			lblError.setText("Number of guests must be a number.");
			return;
		}

		if (orderDateLocal == null) {
			lblError.setText("You must select a date.");
			return;
		}

		// Convert LocalDate to java.sql.Date for entity
		Date orderDate = Date.valueOf(orderDateLocal);

		// Keep original order information from currentOrder
		int orderNumber   = currentOrder.getOrderID();
		int memberId      = currentOrder.getMemberID();
		Date placingOrder = currentOrder.getPlacingOrderDate();

		// Prepare data for order update request
		Order orderUpdateData = new Order(
				orderNumber,     // orderNumber
				orderDate,       // new orderDate
				numberOfGuests,  // dinersAmount
				confirmCode,     // confimationCode
				memberId,        // memberId
				placingOrder     // placingOrderDate
		);
		
		// Send order update request to server and handle response
		String response = BistroClientGUI.client.sendOrderUpdateRequest(orderUpdateData);

		switch (response) {
			case "updateOrderSuccess":
				System.out.println("Order updated successfully on server.");
				BistroClientGUI.client.display(lblError, "Order updated successfully.", Color.GREEN);
				break;

			case "dateNotAvailable":
				System.out.println("The specified date is not available.");
				BistroClientGUI.client.display(lblError, "The specified date is not available.", Color.RED);
				break;

			case "invalidConfirmCode":
				System.out.println("Invalid confirmation code provided.");
				BistroClientGUI.client.display(lblError, "Invalid confirmation code.", Color.RED);
				break;

			default:
				System.out.println("Unexpected response from server: " + response);
				BistroClientGUI.client.display(lblError, "Unexpected error occurred.", Color.RED);
				break;
		}
	}
	
	/*
	 * Method to handle Reset button click and clear the form fields
	 */
	@FXML
	public void btnReset(Event event) {
	    txtConfirmCode.clear();
	    dpOrderDate.setValue(null);
	    txtNumberOfGuests.clear();
	    txtMemberId.clear();
	    txtOrderDatePlaced.clear();
	    lblError.setText("");
	    currentOrder = null;

	    // Return to initial disabled state
	    dpOrderDate.setDisable(true);
	    txtNumberOfGuests.setDisable(true);
	    btnUpdate.setDisable(true);
	    
	    // Allow user to type a new confirmation code
	    txtConfirmCode.setDisable(false);
	}
	
	/**
	 * Checks the confirmation code with the server and, if valid, populates fields
	 * and enables editing of date/guests + Update button.
	 */
	@FXML
	public void btnCheckCode(Event event) {
	    int confirmCode;

	    try {
	        confirmCode = Integer.parseInt(txtConfirmCode.getText().trim());
	    } catch (NumberFormatException e) {
	        lblError.setText("Confirmation code must be a number.");
	        disableEditableFields();
	        clearOrderDetails();
	        currentOrder = null;
	        return;
	    }

	    Order order = BistroClientGUI.client.getOrderByConfirmationCode(confirmCode);

	    if (order != null) {
	        currentOrder = order;

	        if (order.getOrderDate() != null) {
	            dpOrderDate.setValue(order.getOrderDate().toLocalDate());
	        } else {
	            dpOrderDate.setValue(null);
	        }

	        txtNumberOfGuests.setText(String.valueOf(order.getDinersAmount()));
	        txtMemberId.setText(String.valueOf(order.getMemberID()));

	        if (order.getPlacingOrderDate() != null) {
	            txtOrderDatePlaced.setText(order.getPlacingOrderDate().toString());
	        } else {
	            txtOrderDatePlaced.setText("");
	        }

	        // Disable confirmation code once a valid order is loaded
	        txtConfirmCode.setDisable(true);

	        dpOrderDate.setDisable(false);
	        txtNumberOfGuests.setDisable(false);
	        btnUpdate.setDisable(false);

	        BistroClientGUI.client.display(lblError, "Order found.", Color.GREEN);
	    } else {
	        BistroClientGUI.client.display(lblError, "No order found with the provided confirmation code.", Color.RED);
	        disableEditableFields();
	        clearOrderDetails();
	        currentOrder = null;
	        // keep txtConfirmCode enabled so user can try again
	    }
	}


	// Helper to disable fields that depend on a valid code
	private void disableEditableFields() {
		dpOrderDate.setDisable(true);
		txtNumberOfGuests.setDisable(true);
		btnUpdate.setDisable(true);
	}

	// Helper to clear order-related display fields (except confirmation code)
	private void clearOrderDetails() {
		dpOrderDate.setValue(null);
		txtNumberOfGuests.clear();
		txtMemberId.clear();
		txtOrderDatePlaced.clear();
	}
}
