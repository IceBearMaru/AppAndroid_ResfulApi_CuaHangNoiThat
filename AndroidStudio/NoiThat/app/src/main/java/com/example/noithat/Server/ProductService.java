package com.example.noithat.Server;

import com.example.noithat.Model.Product;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Body;


public interface ProductService {

    @GET("api/products")
    Call<List<Product>> getProducts();

    @DELETE("api/products/{id}")
    Call<Void> deleteProduct(@Path("id") int id);

    @POST("api/products")
    Call<Void> addProduct(@Body Product product);

    @PUT("api/products/{id}")
    Call<Void> updateProduct(@Path("id") int productId, @Body Product product);





}

