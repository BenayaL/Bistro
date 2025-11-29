package logic;

import java.io.IOException;
import Server.EchoServer;

public class Server_controller {
    
    // Reference to the running server instance
    private static EchoServer server;
    
    /**
     * Starts the server on the given port.
     * @param portStr The port number as a String
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
    
    
    //Method to print messages from server-side onto the GUI
    //currently has temp names for the "guiController" and the "updateConsole" (until guiController gets implemented)
    public static void appendConsoleMessage(String message) {
        // This following line is left here for debugging without the full GUI
        //System.out.println(message); 
        
        if (guiController != null) {
        	guiController.updateConsole(message);
        }
    }
}