package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {

	private int orderNumber;
	private int confimationCode;
	private Date orderDate;
	private Date placingOrderDate;
	private LocalTime orderTime;
	private int dinersAmount;
	private Member member;
	

	/**
	 * Constructor for Order class
	 * 
	 * @param orderNumber      		the order number					
	 * @param orderDate        		the date of the order			
	 * @param dinersAmount     		the amount of diners in the order
	 * @param confimationCode  		the order's confirmation code			
	 * @param memberId         		the ID of the member who made the order
	 * @param placingOrderDate 		the date of placing the order
	 */

	public Order(int orderNumber, Date orderDate, int dinersAmount, int confimationCode, int memberId, Date placeingOrderDate) {
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.dinersAmount = dinersAmount;
		this.confimationCode = confimationCode;
		this.member = new Member(memberId);
		this.placingOrderDate = placeingOrderDate;
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

	public Date getPlacingOrderDate() {
		return placingOrderDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
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

	public Member getMember() {
		return this.member;
	}

	public int getMemberID() {
		return this.member.getMemberID();
	}

}