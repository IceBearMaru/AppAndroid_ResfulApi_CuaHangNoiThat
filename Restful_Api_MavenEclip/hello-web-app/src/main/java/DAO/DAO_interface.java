package DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO_interface<T> {

    List<T> getAllProduct() throws ClassNotFoundException, SQLException;

    void addProduct(T entity) throws ClassNotFoundException, SQLException;

    void updateProduct(T entity) throws ClassNotFoundException, SQLException;

    void deleteProduct(int id) throws ClassNotFoundException, SQLException;
    
    List<T> getAllOrder() throws ClassNotFoundException, SQLException;

    void addOrder(T entity) throws ClassNotFoundException, SQLException;

    void updateOrder(T entity) throws ClassNotFoundException, SQLException;

    void deleteOrder(int id) throws ClassNotFoundException, SQLException;
    
    List<T> getAllOrderDetail() throws ClassNotFoundException, SQLException;

    void addOrderDetail(T entity) throws ClassNotFoundException, SQLException;

    void updateOrderDetail(T entity) throws ClassNotFoundException, SQLException;

    void deleteOrderDetail(int id) throws ClassNotFoundException, SQLException;
}
