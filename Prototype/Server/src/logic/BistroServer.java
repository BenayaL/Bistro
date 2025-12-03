package logic;

import java.io.IOException;

import clientserver.Message;
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
		String message = ((Message) msg).getId();
		System.out.print("message received: " + message + "from: " + client);
		
		switch(message)
		{
			case "getOrdersList":
				BistroDataBase_Controller.getAllOrders();
				return;
			case "updateOrderStatus":
				
				return;
			default:
				
				break;
		
		}
		/*
		 (message.equals("getOrdersList")) {
			String sid = message.substring(7).trim();
			Student s = mysqlConnection.getStudentById(sid);
			if (s != null) {
				System.out.println("Sending student: " + s.toString() + " to " + client);
				 this.sendToAllClients(s.toString());
			 } else {
				 System.out.println("Student not found for id: " + sid);
				 this.sendToAllClients("Error");
			 }
		 } else if (message.startsWith("UPDATE:")) {
		 
			 String data = message.substring(7).trim();
			 String[] parts = data.split("\\|");
			 if (parts.length == 4) {
				 String sid = parts[0];
				 String pName = parts[1];
				 String lName = parts[2];
				 String fName = parts[3];
				 
				 boolean success = mysqlConnection.updateStudent(sid, pName, lName, fName);
				 if (success) {
					 System.out.println("Student updated: " + sid);
					 this.sendToAllClients("UPDATE_SUCCESS");
				 } else {
					 System.out.println("Failed to update student: " + sid);
					 this.sendToAllClients("UPDATE_FAILED");
				 }
			 }
		 }*/
				
	}
	
	protected void serverStarted() {
		System.out.println("Server started");
		serverConsole.displayMessageToConsole("Server started");
		boolean isConnectToDB = BistroDataBase_Controller.openConnection();
		if(isConnectToDB) {
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