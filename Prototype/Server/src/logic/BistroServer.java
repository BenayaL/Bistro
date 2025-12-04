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
	    if (msg instanceof Message) {

	        Message messageObj = (Message) msg;
	        String messageId = messageObj.getId();

	        System.out.print("message received: " + messageId + " from: " + client + "\n");

	        try {
	            switch (messageId) {

	            case "getOrdersList":
	                List<Order> allOrders = BistroDataBase_Controller.getAllOrders();
	                client.sendToClient(new Message("ordersList", allOrders));
	                return;

	            case "updateOrderStatus":
	                // The client sends an Order object as the data field
	                Order orderToUpdate = (Order) messageObj.getData();

	                boolean updateStatus = BistroDataBase_Controller.updateOrder(orderToUpdate);

	                if (updateStatus) {
	                    // Match the string your controller already expects
	                    client.sendToClient(new Message("updateOrderSuccess", null));
	                } else {
	                    // Currently we only know "update failed", treat it as invalid code
	                    client.sendToClient(new Message("invalidConfirmCode", null));
	                }
	                return;

	            case "getOrderByConfirmationCode":
	                // The client sends an integer confirmation code as the data field
	                int confirmationCode = (int) messageObj.getData();
	                Order order = BistroDataBase_Controller.getOrderByConfirmationCode(confirmationCode);
	                client.sendToClient(new Message("orderByConfirmationCode", order));
	                return;

	            default:
	                client.sendToClient(new Message("unknownCommand",
	                        "Unknown command: " + messageId));
	                return;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}



	protected void serverStarted() {
		System.out.println("Server started");
		serverConsole.displayMessageToConsole("Server started, listening for connections on port " + getPort());
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