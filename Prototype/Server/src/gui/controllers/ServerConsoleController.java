package gui.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.BistroServer;
import logic.BistroServerGUI;

public class ServerConsoleController {
	
	@FXML
	private Button btnStart;
	
	@FXML
	private Button btnStop;
	
	@FXML
	private Button btnClear;
	
	@FXML
	private Button btnSend;
	
	@FXML
	private Label lblError;
	
	@FXML
	private TextArea txtLog;
	
	@FXML
	private TextField txtCommand;
	
	
	@FXML
	public void btnStart(Event event) {
		displayMessageToConsole("Starting server...");
		try {
			BistroServerGUI.server = new BistroServer(ServerPortFrameController.DEFAULT_PORT,this);
		} catch (Exception e) {
			e.printStackTrace();
			displayMessageToConsole("Error starting server: " + e.getMessage() + "\n");
		}
		try {
			BistroServerGUI.server.listen();
			displayMessageToConsole("Server started and listening on port " + ServerPortFrameController.DEFAULT_PORT);
		} catch (Exception e) {
			e.printStackTrace();
			displayMessageToConsole("Error: Could not listen on port " + ServerPortFrameController.DEFAULT_PORT );
		}
	}
	
	@FXML
	public void btnStop(Event event) {
		displayMessageToConsole("Stopping server...");
		try {
			BistroServerGUI.server.close();
		} catch (Exception e) {
			e.printStackTrace();
			displayMessageToConsole("Error stopping server: " + e.getMessage() + "\n");
		}
	}
	
	@FXML
	public void btnClear(Event event) {
		txtLog.clear();
	}
	
	public void displayMessageToConsole(String message) {
		txtLog.appendText(">"+ message + "\n");
	}
	
	
	
	
	
	
}
