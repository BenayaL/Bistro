package Server;

import java.io.IOException;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import logic.Server_controller; // Import your controller to log messages back to UI

public class EchoServer extends AbstractServer {

    // Constructor
    public EchoServer(int port) {
        super(port);
    }

    // This method handles any message received from a client
    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        System.out.println("Message received: " + msg + " from " + client);
        
        // TODO: Later, you will add your DB_Controller logic here 
        // Example: Object result = DB_Controller.parseData(msg);
        
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