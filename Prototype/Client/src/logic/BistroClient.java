package logic;

import clientserver.Message;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

import ocsf.client.*;

public class BistroClient extends AbstractClient {

	public BistroClient(String host, int port) {
		super(host, port);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		
	}
	
	public void switchScreen(FXMLLoader loader,Parent root,Event event, Stage stage) {
	    Stage  = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	}
	
	
}
