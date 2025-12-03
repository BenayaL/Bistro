package logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import entities.Order;


public class BistroDataBase_Controller {
	
	  private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bistro?allowLoadLocalInfile=true&serverTimezone=Asia/Jerusalem&useSSL=false";
	  private static final String JDBC_USER = "root";
	  private static final String JDBC_PASS = "Aa123456";

	  private static Connection conn = null;


	  public static synchronized boolean openConnection() {
		  if (conn != null) return true; 
	      try {
	    	  conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	          System.out.println("SQL connection opened");
	          return true;
	      } catch (SQLException ex) {
	           System.out.println("Failed to open SQL connection: " + ex.getMessage());
	           conn = null;
	           return false;	       
	    }
	  }

	   public static synchronized void closeConnection() {
	       if (conn == null) return;
	       try {
	           conn.close();
	           System.out.println("SQL connection closed");
	        } catch (SQLException ex) {
	            System.out.println("Error closing SQL connection: " + ex.getMessage());
	        } finally {
	            conn = null;
	        }
	    }

	    public static synchronized Connection getConnection() {
	        return conn;
	    }
	    
	    //Method that bring an order information from  order DB.
	    public static Order getOrderByConfirmationCode(int ConfCode)
	    {
	    	String orderQuery = "SELECT order_number, order_date, number_of_guests, confirmation_code, member_id, date_of_placing_order FROM orders WHERE confirmation_code = ?";
	    	 try (PreparedStatement pst = conn.prepareStatement(orderQuery)) {
	             pst.setInt(1, ConfCode);
	             try (ResultSet rs = pst.executeQuery()) {
	                 if (!rs.next()) {
	                     return null;
	                 }

	                 int order_number =rs.getInt("order_number");
	                 Date order_date = rs.getDate("order_date");
	                 int number_of_guests = rs.getInt("number_of_guests");
	                 int confirmation_code =rs.getInt("confirmation_code");
	                 int member_id =rs.getInt("member_id");
	                 Date date_of_placing_order = rs.getDate("date_of_placing_order");
	                 
	                 
	                 return new Order(order_number, order_date, number_of_guests, confirmation_code, member_id, date_of_placing_order);
	               
	             }  
	    	 }	catch (SQLException ex) {
	            	 System.out.println("SQLException: " + ex.getMessage());
	            	 return null;
	             }
	        
	    }
	    
	    //Method that update an order information on order date and number of guests fields.
	    public static boolean updateOrder(int ConfCode, Date newOrderDate, int newNumOfGuests)
	    {
	    	 String updateQuery = "UPDATE orders SET order_date = ?, number_of_guests = ? WHERE confirmation_code = ?";
	    	 
	    	 try (PreparedStatement pst = conn.prepareStatement(updateQuery)) {
	             pst.setDate(1, newOrderDate);
	             pst.setInt(2, newNumOfGuests);  
	             pst.setInt(3, ConfCode);
	             
	             int rowsAffected = pst.executeUpdate();
	             if (rowsAffected > 0) {
	                 System.out.println("Order updated successfully");
	                 return true;
	             } else {
	                 System.out.println("No Order found with confirmation code: " + ConfCode);
	                 return false;
	             }
	         } catch (SQLException ex) {
	             System.out.println("SQLException in updateOrder: " + ex.getMessage());
	             return false;
	         }
	    	
	    }
	    
	    
}
