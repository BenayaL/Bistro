package gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import logic.BistroClientGUI;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import entities.*;

public class ViewOrdersScreenController {
	
	@FXML
	private Button btnHome; // Button to return to home screen
	
	@FXML
	private Label lblError; // Label for displaying errors
	
	@FXML
	private TableView<Order> ordersTableView; // TableView to display orders
	
	@FXML
	private TableColumn<Order,Integer> orderNumberCol; // Column for order numbers
	
	@FXML
	private TableColumn<Order,LocalDate> OrderDateCol; // Column for order dates
	
	@FXML
	private TableColumn<Order, Integer> guestsAmountCol; // Column for number of guests
	
	@FXML
	private TableColumn<Order, LocalDate> placingOrderDateCol ; // Column for place order dates
	
	@FXML
	private TableColumn<Order, Integer> memberIDCol; // Column for member IDs
	
	@FXML
	private TableColumn<Order, Integer> confirmCodeCol; // Column for confirmation codes
	
	
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
	 * Method to load orders data into the TableView from the server
	 */
	@FXML
	public void loadOrdersTable() {
		ObservableList<Order> ordersDataFromServer;
		List<Order> ordersList = BistroClientGUI.client.getOrdersListFromServer();
		if(ordersList!=null) {
			ordersDataFromServer = FXCollections.observableArrayList(); // Initialize the observable list
			//loop to add orders to the observable list
			for(Order o: ordersList) {
				ordersDataFromServer.add(o);
			}
			// Set cell value factories for each column 
			orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
			OrderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
			guestsAmountCol.setCellValueFactory(new PropertyValueFactory<>("dinersAmount"));
			placingOrderDateCol.setCellValueFactory(new PropertyValueFactory<>("placingOrderDate"));
			memberIDCol.setCellValueFactory(new PropertyValueFactory<>("memberID"));
			confirmCodeCol.setCellValueFactory(new PropertyValueFactory<>("confimationCode"));
			// Set the items for the TableView
			ordersTableView.setItems(ordersDataFromServer);
			} else {
				BistroClientGUI.client.display(lblError, "Could not retrieve orders list from server.", Color.RED);
		}
	}
	
	
}
