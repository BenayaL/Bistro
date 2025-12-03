package gui.controllers;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.BistroServerGUI;

public class ServerPortFrameController {
	
	final public static int DEFAULT_PORT = 5555;
	
	@FXML
	private Button btnDone;
	
	@FXML
	private Button btnExit;
	
	@FXML
	private Label lblError;
	
	@FXML
	private TextField txtPort;
	
	
	@FXML
	public void btnDone(Event event) {
		String port = txtPort.getText();
		int intPort;
		try {
			intPort = Integer.parseInt(txtPort.getText());
		} catch (NumberFormatException e) {
			BistroServerGUI.display(lblError," Invalid port number. Please enter a valid integer.", Color.RED);
			return;
		}
		if(port.trim().isEmpty()) {
			BistroServerGUI.display(lblError," Port number cannot be empty.", Color.RED);
			return;
		}
		else if(intPort != DEFAULT_PORT) {
			BistroServerGUI.display(lblError,"Port incorrect!", Color.ORANGE);
		}
		else {
			BistroServerGUI.display(lblError," Port accepted.", Color.GREEN);
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/ServerConsole.fxml"));
				Parent root = loader.load();
				BistroServerGUI.switchScreen(loader, root, event, "Bistro Server Console");
				} catch (IOException e) {
					e.printStackTrace();
			}
		}
	}	
	
	
	public void start(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/ServerPortFrame.fxml"));
		try {
			primaryStage.setTitle("Bistro Server - Port Selection");
			primaryStage.setScene(new Scene(loader.load()));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
