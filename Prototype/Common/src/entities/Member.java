package entities;
import java.io.Serializable;

public class Member implements Serializable {
	private int memberID;
	
	public Member(int memberID) {
		this.memberID = memberID;
	}
	
	public int getMemberID() {
		return memberID;
	}
}
