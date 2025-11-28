package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Order {

    private String OrderID;
    private String confimationCode;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private int dinersAmount;
    
    //Constructor:
    public Order(String reservationID, String confimationCode, LocalDate reservationDate,
					   LocalTime reservationTime, int dinersAmount, boolean reserved, boolean inWaitList) {
		this.OrderID = reservationID;
		this.confimationCode = confimationCode;
		this.orderDate = reservationDate;
		this.orderTime = reservationTime;
		this.dinersAmount = dinersAmount;
    }
    
    //ReservationID getter and setter:
    public String getReservationID() {
        return OrderID;
    }
    
    public void setReservationID(String reservationID) {
        this.OrderID = reservationID;
    }
    
    //ConfimationCode getter and setter:
    public String getConfimationCode() {
        return confimationCode;
    }

    public void setConfimationCode(String confimationCode) {
        this.confimationCode = confimationCode;
    }
    
    //reservationDate getter and setter:
    public LocalDate getReservationDate() {
        return orderDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.orderDate = reservationDate;
    }
    
    //reservationTime getter and setter:
    public LocalTime getReservationTime() {
        return orderTime;
    }

    public void setReservationTime(LocalTime reservationTime) {
        this.orderTime = reservationTime;
    }
    //dinersAmount getter and setter:
    public int getDinersAmount() {
        return dinersAmount;
    }
    public void setDinersAmount(int dinersAmount) {
        this.dinersAmount = dinersAmount;
    }
}