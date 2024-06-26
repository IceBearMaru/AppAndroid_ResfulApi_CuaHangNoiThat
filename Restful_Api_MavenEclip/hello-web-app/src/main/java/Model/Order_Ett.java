package Model;
import java.util.Date;

public class Order_Ett {
	    private int orderID;
	    private int customerID;
	    private Date orderDate;
	    private double totalAmount;
	    
	    public Order_Ett() {
	        // Default constructor
	    }

	    public Order_Ett(int orderID, int customerID, Date orderDate, double totalAmount) {
	        this.orderID = orderID;
	        this.customerID = customerID;
	        this.orderDate = orderDate;
	        this.totalAmount = totalAmount;
	    }

		public int getOrderID() {
			return orderID;
		}

		public void setOrderID(int orderID) {
			this.orderID = orderID;
		}

		public int getCustomerID() {
			return customerID;
		}

		public void setCustomerID(int customerID) {
			this.customerID = customerID;
		}

		public Date getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(Date orderDate) {
			this.orderDate = orderDate;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}
	    
	    

	    
	    // getters, setters, and other methods

	}



