package Server;

import java.io.IOException;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import logic.Server_controller;

public class EchoServer extends AbstractServer {

    
    public EchoServer(int port) {
        super(port);
    }
    
    
    
    //Helper method for consistent formatting
    private String getClientNameAndIP(ConnectionToClient client) {
    	String hostIP = client.getInetAddress().getHostAddress();
    	String hostName = client.getInetAddress().getHostName();
    	
    	return String.format("IP: %-15s | Name: %s", hostIP, hostName);
    }
    
    // Method that handles proper client connection
    @Override
    protected void clientConnected(ConnectionToClient client) {
    	//this following line is left here for use when testing or debugging with the console instead of the full GUI
    	//System.out.println("Client info: " + client.toString());
    	
    	String connectionMSG = ("Client connected with: " + getClientNameAndIP(client));
    	Server_controller.appendConsoleMessage(connectionMSG);
    	
    	// DONT FORGET: add the method that shows the connection on the GUI!!
    }
    

    // Method that handles proper client disconnection
    @Override
    synchronized protected void clientDisconnected(ConnectionToClient client) {
    	//this following line is left here for use when testing or debugging with the console instead of the full GUI
    	//System.out.println("Client " + client.toString() + "disconnected.");
    	
    	String disconnectionMSG = ("Client disconnected from: " + getClientNameAndIP(client));
    	Server_controller.appendConsoleMessage(disconnectionMSG);
    }
    
    // Method that handles improper client disconnection
    @Override
    synchronized protected void clientException(ConnectionToClient client, Throwable exception) {
    	//this following line is left here for use when testing or debugging with the console instead of the full GUI
    	//System.out.println("Client " + client.toString() + "crashed");
    	
    	String errorDetails = exception.getMessage();
    	
    	String disconnectionMSG = ("Client crashed from: " + getClientNameAndIP(client) + " with error: " +errorDetails);
    	Server_controller.appendConsoleMessage(disconnectionMSG);
    	
    	//Do we want to use this? should show the full "path" of issues if someone crashes
    	exception.printStackTrace();
    }
    
    //TODO change this method completely once we start working on the full project
    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
    	//this following line is left here for use when testing or debugging with the console instead of the full GUI
        System.out.println("Message received: " + msg + " from " + client);
        
        // TODO add the DB_Controller logic here 
        // For now, just echo the message back to the client
        try {
            client.sendToClient("Server received: " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Called when the server starts listening
    @Override
    protected void serverStarted() {
        System.out.println("Server listening for connections on port " + getPort());
    }

    // Called when the server stops listening
    @Override
    protected void serverStopped() {
        System.out.println("Server has stopped listening for connections.");
    }
}