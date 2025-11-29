package gui.controllers;

import java.io.*;
import logic.ChatIF;

public class ClientGUI implements ChatIF {
    
    /**
     * This method prints the message received from the server to the console.
     * 
     * @param msg The message received from the server.
     */
    @Override
    public void display(String msg) {
        System.out.println(msg);
    }
	
}
