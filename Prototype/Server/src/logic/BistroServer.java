package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import clientserver.Message;
import entities.Order;
import gui.controllers.ServerConsoleController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class BistroServer extends AbstractServer {

	private ServerConsoleController serverConsole;

	public BistroServer(int port, ServerConsoleController serverConsoleController) {
		super(port);
		this.serverConsole = serverConsoleController;
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// TODO Auto-generated method stub
		if (msg instanceof Message) {

			String message = ((Message) msg).getId();
			System.out.print("message received: " + message + "from: " + client);
			try {

				switch (message) {
				case "getOrdersList":
					List<Order> allOrders = new ArrayList<>();
					allOrders = BistroDataBase_Controller.getAllOrders();
					client.sendToClient(new Message("ordersList", allOrders));
					return;
				case "updateOrderStatus":
					List<Object> orderUpdateData = new ArrayList<>();
					boolean UpdateStatus;
					UpdateStatus = BistroDataBase_Controller.updateOrder(orderUpdateData);
					client.sendToClient(new Message("updateOrderStatusResponse", UpdateStatus));
					return;
				default:
					client.sendToClient(new Message("unknownCommand: " + ((Message) msg).getId(), null));
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		 * (message.equals("getOrdersList")) { String sid = message.substring(7).trim();
		 * Student s = mysqlConnection.getStudentById(sid); if (s != null) {
		 * System.out.println("Sending student: " + s.toString() + " to " + client);
		 * this.sendToAllClients(s.toString()); } else {
		 * System.out.println("Student not found for id: " + sid);
		 * this.sendToAllClients("Error"); } } else if (message.startsWith("UPDATE:")) {
		 * 
		 * String data = message.substring(7).trim(); String[] parts =
		 * data.split("\\|"); if (parts.length == 4) { String sid = parts[0]; String
		 * pName = parts[1]; String lName = parts[2]; String fName = parts[3];
		 * 
		 * boolean success = mysqlConnection.updateStudent(sid, pName, lName, fName); if
		 * (success) { System.out.println("Student updated: " + sid);
		 * this.sendToAllClients("UPDATE_SUCCESS"); } else {
		 * System.out.println("Failed to update student: " + sid);
		 * this.sendToAllClients("UPDATE_FAILED"); } } }
		 */

	}

	protected void serverStarted() {
		System.out.println("Server started");
		serverConsole.displayMessageToConsole("Server started");
		boolean isConnectToDB = BistroDataBase_Controller.openConnection();
		if (isConnectToDB) {
			serverConsole.displayMessageToConsole("Connected to database successfully");
		} else {
			serverConsole.displayMessageToConsole("Failed to connect to database");
		}
	}

	protected void serverStopped() {
		System.out.println("Server stopped");
		serverConsole.displayMessageToConsole("Server stopped");
		BistroDataBase_Controller.closeConnection();
	}

	public void showAllConnections() {
		// TODO Auto-generated method stub
	}

}