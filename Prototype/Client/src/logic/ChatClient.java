package logic;

import java.io.IOException;

import ocsf.client.*;

public class ChatClient extends AbstractClient {

	// Instance variables:
	
	/* The interface type variable. It allows the implementation of the display
	 * method in the client.*/
	private ClientController clientCTRL;

	// Constructor:
	
	/* Constructs an instance of the chat client:
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientCTRL The interface type variable.*/
	public ChatClient(String host, int port, ClientController clientCTRL) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientCTRL = clientCTRL;
	}
	
	
	
	
	//Message from server:
	/*
	 * This method handles all data coming from the server.
	 * @param message The message from the server.*/
	@Override
	public void handleMessageFromServer(Object msg) {
		if(clientCTRL != null ) {
			clientCTRL.handleMessageFromServer(msg);
		}
	}
	
	//Message to server:
	/* This method handles all data coming from the ClientController.
	 * @param message The message from the ClientController.*/
	public void handleMessageFromClientCTRL(Object msg) {
		 try
		    {
		    	sendToServer(msg);
		    }
		    catch(IOException e)
		    {
		    	//need to decide what to do here
		    	e.printStackTrace();
		    	clientCTRL.display("Could not send message to server.  Terminating client.");
		    	quit();
		    	
		    }
	}
	
	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

}
