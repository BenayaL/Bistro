package logic;

public class ClientController implements ChatIF {

	
	
	public void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(String message) {
		System.out.println(">" + message);
	}
}
