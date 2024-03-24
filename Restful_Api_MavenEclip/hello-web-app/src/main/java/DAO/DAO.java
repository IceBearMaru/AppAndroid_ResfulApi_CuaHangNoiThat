package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
    private static final String URL = "jdbc:mysql://localhost:3306/db_noithat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "MyNewPass";

    protected Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // Xử lý ngoại lệ kết nối
            e.printStackTrace();
            throw e;
        }

        return connection;
    }

    protected void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Xử lý ngoại lệ khi đóng kết nối
                e.printStackTrace();
            }
        }
    }

////Test
//    public static void main(String[] args) {
//        DAO dao = new DAO();
//
//        try {
//            Connection connection = dao.getConnection();
//            System.out.println("Kết nối thành công!");
//
//            // Thực hiện các thao tác với cơ sở dữ liệu ở đây (nếu cần)
//
//            // Sau khi hoàn thành, đóng kết nối
//            dao.closeConnection(connection);
//            System.out.println("Đã đóng kết nối.");
//        } catch (SQLException e) {
//            System.err.println("Không thể kết nối đến cơ sở dữ liệu.");
//            e.printStackTrace();
//        }
//    }
//    
    
}