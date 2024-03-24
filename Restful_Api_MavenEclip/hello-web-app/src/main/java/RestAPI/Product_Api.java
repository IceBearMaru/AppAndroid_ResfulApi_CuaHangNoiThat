//package RestAPI;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import Model.Product_BUS;
//import Model.Product_Ett;
//
//@Path("/products")
//public class Product_Api {
//
//    private Product_BUS productBUS = new Product_BUS();
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllProducts() {
//        return productBUS.getAllProducts();
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addProduct(Product_Ett requestBody) {
//        Response response = productBUS.addProduct(requestBody);
//
//        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
//            System.out.println("Product added successfully: " + requestBody.toString());
//        } else {
//            System.err.println("Error adding product: " + response.getEntity());
//        }
//
//        // Kiểm tra nếu response.isSuccessful() là true, bạn có thể in ra màn hình
//        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
//            System.out.println("Response is successful: " + response.getStatus());
//        } else {
//            System.out.println("Response is not successful: " + response.getStatus());
//        }
//
//        return response;
//    }
//
//
//
//    @DELETE
//    @Path("/{id}")
//    public Response deleteProduct(@PathParam("id") int id) {
//    	Response response = productBUS.deleteProduct(id);
//    	System.out.println("Response from productBUS: " + response.getEntity());
//    	return response;
//    }
//
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateProduct(@PathParam("id") int id, Product_Ett requestBody) {
//        return productBUS.updateProduct(id, requestBody);
//    }
//}

package RestAPI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Model.Product_BUS;
import Model.Product_Ett;

@Path("/products")
public class Product_Api {

    private Product_BUS productBUS = new Product_BUS();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        return productBUS.getAllProducts();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product_Ett requestBody) {
        return productBUS.addProduct(requestBody);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        return productBUS.deleteProduct(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, Product_Ett requestBody) {
        return productBUS.updateProduct(id, requestBody);
    }
}

