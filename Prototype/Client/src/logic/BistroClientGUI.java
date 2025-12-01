package logic;

import java.io.*;

import javafx.application.Application;
import javafx.stage.Stage;

public class BistroClientGUI extends Application {

	public static BistroClient client;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		client = new BistroClient("localhost", 5555);
		try {
			client.openConnection();
		} catch (IOException e) {
			System.out.println("Error: Can't setup connection! Terminating client.");
			System.exit(1);
		}
	}
}
