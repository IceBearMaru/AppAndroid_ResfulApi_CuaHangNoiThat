package DAO;

import Model.Order_Ett;
import Model.Product_Ett;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Order_DAO extends DAO implements DAO_interface<Order_Ett> {
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private static final String INSERT_ORDER = "INSERT INTO orders (customerID, orderDate, totalAmount) VALUES (?, ?, ?)";
    private static final String UPDATE_ORDER = "UPDATE orders SET customerID=?, orderDate=?, totalAmount=? WHERE orderID=?";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE orderID=?";

    private void closeResources(Connection connection, PreparedStatement statement) {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order_Ett> getAllOrder() throws ClassNotFoundException, SQLException {
        List<Order_Ett> orders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SELECT_ALL_ORDERS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order_Ett order = new Order_Ett();
                order.setOrderID(resultSet.getInt("orderID"));
                order.setCustomerID(resultSet.getInt("customerID"));
                order.setOrderDate(resultSet.getDate("orderDate"));
                order.setTotalAmount(resultSet.getDouble("totalAmount"));
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, statement);
        }

        return orders;
    }

    @Override
    public void addOrder(Order_Ett order) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_ORDER);

            statement.setInt(1, order.getCustomerID());
            statement.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            statement.setDouble(3, order.getTotalAmount());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, statement);
        }
    }

    @Override
    public void updateOrder(Order_Ett order) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE_ORDER);

            statement.setInt(1, order.getCustomerID());
            statement.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            statement.setDouble(3, order.getTotalAmount());
            statement.setInt(4, order.getOrderID());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, statement);
        }
    }

    @Override
    public void deleteOrder(int orderID) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_ORDER);
            statement.setInt(1, orderID);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, statement);
        }
    }

	@Override
	public List<Order_Ett> getAllProduct() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void updateProduct(Order_Ett entity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order_Ett> getAllOrderDetail() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOrderDetail(Order_Ett entity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrderDetail(Order_Ett entity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrderDetail(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProduct(Order_Ett entity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
	
	}


}
