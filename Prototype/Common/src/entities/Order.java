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
    public Order(String orderID, String confimationCode, LocalDate orderDate,
					   LocalTime orderTime, int dinersAmount) {
		this.OrderID = orderID;
		this.confimationCode = confimationCode;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.dinersAmount = dinersAmount;
    }
    
    //Getters and Setters:
    public String getOrderID() {
		return OrderID;
	}
    
    public void setOrderID(String orderID) {
		this.OrderID = orderID;
	}
    	
    public String getConfimationCode() {
		return confimationCode;
	}
    	
    public void setConfimationCode(String confimationCode) {
		this.confimationCode = confimationCode;
	}
    	
	public LocalDate getOrderDate() {
		return orderDate;
	}
		
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
		
	public LocalTime getOrderTime() {
		return orderTime;
	}
		
	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}
		
	public int getDinersAmount() {
		return dinersAmount;
	}
		
	public void setDinersAmount(int dinersAmount) {
		this.dinersAmount = dinersAmount;
	}
		
}