package entities;

import java.io.Serializable;

public class Member implements Serializable {
	/**
	 * A restaurant member class
	 */
	private static final long serialVersionUID = 1L;
	private int memberID;

	public Member(int memberID) {
		this.memberID = memberID;
	}

	public int getMemberID() {
		return memberID;
	}
}
