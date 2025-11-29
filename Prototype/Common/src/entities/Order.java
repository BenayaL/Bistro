package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = -7312192944532984714L;
	private int orderNumber;
    private int confimationCode;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private int dinersAmount;
    
    /** 
     * Constructor for Order class
     * @param orderNumber the order number
     * @param confimationCode the confirmation code
     * @param orderDate the date of the order
     * @param orderTime the time of the order
     * @param dinersAmount the amount of diners
     */
    
    public Order(int orderNumber, int confimationCode, LocalDate orderDate, LocalTime orderTime, int dinersAmount) {
		this.orderNumber = orderNumber;
		this.confimationCode = confimationCode;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.dinersAmount = dinersAmount;
    }
    
    // Getters and Setters:
    public int getOrderID() {
		return orderNumber;
	}
    
    public void setOrderID(int orderNumber) {
		this.orderNumber = orderNumber;
	}
    	
    public int getConfimationCode() {
		return confimationCode;
	}
    	
    public void setConfimationCode(int confimationCode) {
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