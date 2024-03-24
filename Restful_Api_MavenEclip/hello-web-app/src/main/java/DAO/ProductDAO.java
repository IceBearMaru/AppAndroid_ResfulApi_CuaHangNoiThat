/*
 * package DAO;
 * 
 * import Model.Product_Ett;
 * 
 * import java.sql.Connection; import java.sql.PreparedStatement; import
 * java.sql.ResultSet; import java.sql.SQLException; import java.util.ArrayList;
 * import java.util.List;
 * 
 * public class ProductDAO extends DAO implements DAO_interface<Product_Ett> {
 * private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product";
 * private static final String INSERT_PRODUCT =
 * "INSERT INTO product (name, material, origin, imageUrl, price) VALUES (?, ?, ?, ?, ?)"
 * ; private static final String UPDATE_PRODUCT =
 * "UPDATE product SET name=?, material=?, origin=?, imageUrl=?, price=? WHERE id=?"
 * ; private static final String DELETE_PRODUCT =
 * "DELETE FROM product WHERE id=?";
 * 
 * private void closeResources(Connection connection, PreparedStatement
 * statement, ResultSet resultSet) { try { if (resultSet != null)
 * resultSet.close(); if (statement != null) statement.close(); if (connection
 * != null) connection.close(); } catch (SQLException e) { // Log or handle the
 * exception as needed e.printStackTrace(); } }
 * 
 * @Override public List<Product_Ett> getAllProduct() throws
 * ClassNotFoundException, SQLException { List<Product_Ett> products = new
 * ArrayList<>(); Connection connection = null; PreparedStatement statement =
 * null; ResultSet resultSet = null;
 * 
 * try { connection = getConnection(); statement =
 * connection.prepareStatement(SELECT_ALL_PRODUCTS); resultSet =
 * statement.executeQuery();
 * 
 * while (resultSet.next()) { Product_Ett product = new Product_Ett();
 * product.setId(resultSet.getInt("id"));
 * product.setName(resultSet.getString("name"));
 * product.setMaterial(resultSet.getString("material"));
 * product.setOrigin(resultSet.getString("origin"));
 * product.setImageUrl(resultSet.getString("imageUrl"));
 * product.setPrice(resultSet.getDouble("price")); products.add(product); }
 * 
 * } catch (SQLException e) { // Log or handle the exception as needed
 * e.printStackTrace(); } finally { closeResources(connection, statement,
 * resultSet); }
 * 
 * return products; }
 * 
 * @Override public void addProduct(Product_Ett product) throws
 * ClassNotFoundException, SQLException { Connection connection = null;
 * PreparedStatement statement = null;
 * 
 * try { connection = getConnection(); statement =
 * connection.prepareStatement(INSERT_PRODUCT);
 * 
 * statement.setString(1, product.getName()); statement.setString(2,
 * product.getMaterial()); statement.setString(3, product.getOrigin());
 * statement.setString(4, product.getImageUrl()); statement.setDouble(5,
 * product.getPrice());
 * 
 * statement.executeUpdate();
 * 
 * } catch (SQLException e) { // Log or handle the exception as needed
 * e.printStackTrace(); } finally { closeResources(connection, statement, null);
 * // No ResultSet for insert operation } }
 * 
 * @Override public void updateProduct(Product_Ett product) throws
 * ClassNotFoundException, SQLException { Connection connection = null;
 * PreparedStatement statement = null;
 * 
 * try { connection = getConnection(); statement =
 * connection.prepareStatement(UPDATE_PRODUCT);
 * 
 * statement.setString(1, product.getName()); statement.setString(2,
 * product.getMaterial()); statement.setString(3, product.getOrigin());
 * statement.setString(4, product.getImageUrl()); statement.setDouble(5,
 * product.getPrice()); statement.setInt(6, product.getId());
 * 
 * statement.executeUpdate();
 * 
 * } catch (SQLException e) { // Log or handle the exception as needed
 * e.printStackTrace(); } finally { closeResources(connection, statement, null);
 * // No ResultSet for update operation } }
 * 
 * @Override public void deleteProduct(int productId) throws
 * ClassNotFoundException, SQLException { Connection connection = null;
 * PreparedStatement statement = null;
 * 
 * try { connection = getConnection(); statement =
 * connection.prepareStatement(DELETE_PRODUCT); statement.setInt(1, productId);
 * statement.executeUpdate();
 * 
 * } catch (SQLException e) { // Log or handle the exception as needed
 * e.printStackTrace(); } finally { closeResources(connection, statement, null);
 * // No ResultSet for delete operation } }
 * 
 * @Override public List<Product_Ett> getAllOrder() throws
 * ClassNotFoundException, SQLException { // TODO Auto-generated method stub
 * return null; }
 * 
 * @Override public void addOrder(Product_Ett entity) throws
 * ClassNotFoundException, SQLException { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public void updateOrder(Product_Ett entity) throws
 * ClassNotFoundException, SQLException { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public void deleteOrder(int id) throws ClassNotFoundException,
 * SQLException { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public List<Product_Ett> getAllOrderDetail() throws
 * ClassNotFoundException, SQLException { // TODO Auto-generated method stub
 * return null; }
 * 
 * @Override public void addOrderDetail(Product_Ett entity) throws
 * ClassNotFoundException, SQLException { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public void updateOrderDetail(Product_Ett entity) throws
 * ClassNotFoundException, SQLException { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override public void deleteOrderDetail(int id) throws
 * ClassNotFoundException, SQLException { // TODO Auto-generated method stub
 * 
 * } }
 */

package DAO;

import Model.Product_Ett;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DAO implements DAO_interface<Product_Ett> {
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product";
    private static final String INSERT_PRODUCT = "INSERT INTO product (name, material, origin, imageUrl, price) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE product SET name=?, material=?, origin=?, imageUrl=?, price=? WHERE id=?";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE id=?";

    private void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            // Log or handle the exception as needed
            e.printStackTrace();
        }
    }

    @Override
    public List<Product_Ett> getAllProduct() throws ClassNotFoundException, SQLException {
        List<Product_Ett> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product_Ett product = new Product_Ett();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setMaterial(resultSet.getString("material"));
                product.setOrigin(resultSet.getString("origin"));
                product.setImageUrl(resultSet.getString("imageUrl"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }

        } catch (SQLException e) {
            // Log or handle the exception as needed
            e.printStackTrace();
        } finally {
            closeResources(connection, statement, resultSet);
        }

        return products;
    }

    @Override
    public void addProduct(Product_Ett product) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_PRODUCT);

            statement.setString(1, product.getName());
            statement.setString(2, product.getMaterial());
            statement.setString(3, product.getOrigin());
            statement.setString(4, product.getImageUrl());
            statement.setDouble(5, product.getPrice());

            statement.executeUpdate();

        } catch (SQLException e) {
            // Log or handle the exception as needed
            e.printStackTrace();
        } finally {
            closeResources(connection, statement, null);  // No ResultSet for insert operation
        }
    }

    @Override
    public void updateProduct(Product_Ett product) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE_PRODUCT);

            statement.setString(1, product.getName());
            statement.setString(2, product.getMaterial());
            statement.setString(3, product.getOrigin());
            statement.setString(4, product.getImageUrl());
            statement.setDouble(5, product.getPrice());
            statement.setInt(6, product.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            // Log or handle the exception as needed
            e.printStackTrace();
        } finally {
            closeResources(connection, statement, null);  // No ResultSet for update operation
        }
    }

    @Override
    public void deleteProduct(int productId) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_PRODUCT);
            statement.setInt(1, productId);
            statement.executeUpdate();

        } catch (SQLException e) {
            // Log or handle the exception as needed
            e.printStackTrace();
        } finally {
            closeResources(connection, statement, null);  // No ResultSet for delete operation
        }
    }

	@Override
	public List<Product_Ett> getAllOrder() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOrder(Product_Ett entity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrder(Product_Ett entity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrder(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product_Ett> getAllOrderDetail() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOrderDetail(Product_Ett entity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrderDetail(Product_Ett entity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrderDetail(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
}
