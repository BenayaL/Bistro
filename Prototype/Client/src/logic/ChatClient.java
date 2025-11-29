package logic;

import ocsf.client.*;



public class ChatClient extends AbstractClient{
	
	public ChatClient(String host, int port) {
		super(host, port);
	}

	@Override
	public void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void connectionEstablished() {
		
	}
	
	@Override
	public void connectionClosed() {
		
	}
	
	@Override
	public void connectionException(Exception exception) {
		
	}


}
