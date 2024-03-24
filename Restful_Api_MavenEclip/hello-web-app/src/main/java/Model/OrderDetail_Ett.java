package Model;

public class OrderDetail_Ett {
    private int orderDetailID;
    private int orderID;
    private int productID;
    private int quantity;
    private double subtotal;

    public OrderDetail_Ett() {
        // Default constructor
    }

    public OrderDetail_Ett(int orderDetailID, int orderID, int productID, int quantity, double subtotal) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

	public int getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
    
    

  
}
