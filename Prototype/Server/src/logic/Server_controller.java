package logic;

import java.io.IOException;
import Server.EchoServer; // Make sure this import matches where you put EchoServer

public class Server_controller {
    
    // Reference to the running server instance
    private static EchoServer server;
    
    /**
     * Starts the server on the given port.
     * @param portStr The port number as a String (usually from a text field)
     * @return true if started successfully, false otherwise
     */
    public static boolean runServer(String portStr) {
        int port = 0; // Default port
        
        try {
            port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Port format");
            return false;
        }

        server = new EchoServer(port);

        try {
            server.listen(); // Start listening for connections
            return true;
        } catch (IOException e) {
            System.out.println("Error: Could not listen on port " + port);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Stops the server if it is running.
     */
    public static void stopServer() {
        if (server != null) {
            try {
                server.close(); // Closes connections and stops listening
                System.out.println("Server Stopped.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}