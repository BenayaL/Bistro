package gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ConnectionStatusWindowController {

    
    private HomeScreenController parent; // Reference to the parent controller

    @FXML
    private Label hostLabel; // Label to display host IP

    @FXML
    private Label portLabel; // Label to display port number

    @FXML
    private Label statusLabel; // Label to display connection status
    
    @FXML
    private Button closeBtn; // Button to close the window
    
    /*
     * Sets the parent controller for this window.
     */
    public void setParent(HomeScreenController parent) { 
        this.parent = parent;
    }

    /*
	 * Handles the close button click event.
	 */
    @FXML
    private void closeConnectionStatus(ActionEvent event) {
        if (parent != null) {
            parent.closeConnectionStatus();
        }
    }
    
    /*
	 * Sets the connection details in the labels.
	 * 
	 * @param host The host IP address.
	 * @param port The port number.
	 * @param statusText The connection status text.
	 */
    public void setConnectionDetails(String host, int port, String statusText,Color color) {
        hostLabel.setText("Host IP : " + host);
        portLabel.setText("Port: " + port);
        statusLabel.setText("Connection status: " + statusText);
        statusLabel.setTextFill(color);
    }
}
