    package Model;

import DAO.DAO_interface;
import DAO.ProductDAO;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class Product_BUS {

    private DAO_interface<Product_Ett> productDAO = new ProductDAO();

    public Response getAllProducts() {
        try {
            List<Product_Ett> productList = productDAO.getAllProduct();
            return Response.ok(productList).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching products").build();
        }
    }

    public Response addProduct(Product_Ett product) {
        try {
            productDAO.addProduct(product);
            return Response.ok().build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding product").build();
        }
    }

    public Response deleteProduct(int id) {
        try {
            productDAO.deleteProduct(id);
            return Response.ok().build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting product").build();
        }
    }

    public Response updateProduct(int id, Product_Ett product) {
        try {
            // Set the product id from the path parameter
            product.setId(id);
            productDAO.updateProduct(product);
            return Response.ok().build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating product").build();
        }
    }
}
