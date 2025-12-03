package gui.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ServerPortFrameController {
	
	final public static int DEFAULT_PORT = 5555;
	
	@FXML
	private Button btnDone;
	
	
	
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
