package logic;

import java.io.IOException;
import Server.EchoServer;
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
		
	}
	
	protected void serverStarted() {
		System.out.println("Server started");
		serverConsole.displayMessageToConsole("Server started\n");
		boolean isConnectToDB = BistroDataBase_Controller.openConnection();
		
	}
	
	protected void serverStopped() {
		System.out.println("Server stopped");
	}
	
	

}