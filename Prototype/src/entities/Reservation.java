package entities;

public class Reservation {

    private String reservationID;
    private String confimationCode;
    private LocalDate reservationDate;
    private Time reservationTime;
    private int dinersAmount;
    private boolean reserved;
    private boolean inWaitList;
    
    //Constructor:
    public Reservation(){
        
    }
    
    //ReservationID getter and setter:
    public String getReservationID() {
        return reservationID;
    }
    
    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }
    
    //ConfimationCode getter and setter:
    public String getConfimationCode() {
        return confimationCode;
    }

    public void setConfimationCode(String confimationCode) {
        this.confimationCode = confimationCode;
    }
    
    //reservationDate getter and setter:
    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
    
    //reservationTime getter and setter:
    public Time getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Time reservationTime) {
        this.reservationTime = reservationTime;
    }
    //dinersAmount getter and setter:
    public int getDinersAmount() {
        return dinersAmount;
    }
    public void setDinersAmount(int dinersAmount) {
        this.dinersAmount = dinersAmount;
    }
    
	//reserved getter and setter:
    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
    
    //inWaitList getter and setter:
    public boolean getInWaitList() {
		return inWaitList;
	}
    
    public void setInWaitList(boolean inWaitList) {
    	this.inWaitList = inWaitList;
	}

}