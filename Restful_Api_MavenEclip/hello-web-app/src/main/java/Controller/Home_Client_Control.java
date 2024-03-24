package Controller;

import DAO.DAO_interface;
import Model.Product_Ett;

import DAO.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/trangchu")
public class Home_Client_Control extends HttpServlet implements Controller_interface {
    private static final long serialVersionUID = 1L;

    public Home_Client_Control() {
        super();
    }
    
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO_interface<Product_Ett> productDAO_interface = new ProductDAO();
        List<Product_Ett> list = new ArrayList<>();
        try {
            list = productDAO_interface.getAllProduct() ;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("products", list);

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement the doPost method if needed
    }




    
    // TEST DỮ LIỆU

//    // Added main method for testing
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//    	Home_Client_Control controller = new Home_Client_Control();
//        List<Product_Ett> productList = controller.getAllProductsForTesting();
//
//        for (Product_Ett product : productList) {
//            System.out.println(product.getId() + " | " + product.getName() + " | " + product.getMaterial()
//                    + " | " + product.getOrigin() + " | " + product.getImageUrl() + " | " + product.getPrice());
//        }
//    }
//
//    // Method for testing without servlet environment
//    private List<Product_Ett> getAllProductsForTesting() throws ClassNotFoundException, SQLException {
//        ProductDAO productDAO = new ProductDAO();
//        return productDAO.getAll();
//    }
//    


}

