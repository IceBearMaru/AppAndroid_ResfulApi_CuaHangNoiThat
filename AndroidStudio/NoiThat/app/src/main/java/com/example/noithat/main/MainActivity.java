package com.example.noithat.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.noithat.Model.Product;
import com.example.noithat.R;
import com.example.noithat.Server.ProductService;
import com.example.noithat.Retrofit.RetrofitClient;
import com.example.noithat.Adapter.ProductAdapter;
import com.squareup.picasso.Picasso;

import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements ProductAdapter.ProductClickListener, ProductAdapter.ProductLongClickListener {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    private Button addButton;

    private List<Product> productList;


    // Trong MainActivity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProductPopup();
            }
        });



        // Khởi tạo danh sách sản phẩm ở mức lớp
        productList = new ArrayList<>();

        // Khởi tạo RecyclerView và ProductAdapter
        recyclerView = findViewById(R.id.recyclerView);
        productAdapter = new ProductAdapter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);


        // Load danh sách sản phẩm
        loadProducts();
    }

    private void loadProducts() {
        ProductService productService = RetrofitClient.getRetrofitInstance().create(ProductService.class);
        Call<List<Product>> call = productService.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    // Gán dữ liệu vào productList ở mức lớp
                    productList = response.body();
                    // Cập nhật dữ liệu cho adapter
                    productAdapter.setProducts(productList);
                } else {
                    Toast.makeText(MainActivity.this, "Không thể tải danh sách sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Đã xảy ra lỗi khi tải danh sách sản phẩm", Toast.LENGTH_SHORT).show();
                Log.e("Load Products Error", "Đã xảy ra lỗi khi tải danh sách sản phẩm", t);
            }
        });
    }





    @Override
    public void onProductLongClick(Product product) {
        showOptionsDialog(product);
    }

    private void showOptionsDialog(final Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn hành động");
        builder.setItems(new CharSequence[]{"Xóa", "Cập nhật"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        // Log ID của sản phẩm
                        Log.d("Product ID", "ID của sản phẩm: " + product.getId());
                        // Gọi phương thức xóa sản phẩm với ID đã lấy
                        deleteProduct(product.getId());
                        break;
                    case 1:
                        // Thực hiện cập nhật sản phẩm ở đây
                        showUpdateProductPopup(product);
                        break;
                }
            }
        });
        builder.create().show();
    }

    private void deleteProduct(int productId) {
        ProductService productService = RetrofitClient.getRetrofitInstance().create(ProductService.class);
        Call<Void> call = productService.deleteProduct(productId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Product successfully deleted
                    Toast.makeText(MainActivity.this, "Sản phẩm đã được xóa", Toast.LENGTH_SHORT).show();
                    // Remove the deleted product from the list and notify the adapter
                    productAdapter.removeProduct(productId); // Gọi phương thức removeProduct trong Adapter để xóa sản phẩm
                } else {
                    // Unable to delete product
                    Toast.makeText(MainActivity.this, "Không thể xóa sản phẩm", Toast.LENGTH_SHORT).show();
                    // Log the error message
                    Log.e("Delete Product Error", "Không thể xóa sản phẩm. Mã trạng thái: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Error occurred while deleting product
                Toast.makeText(MainActivity.this, "Đã xảy ra lỗi khi xóa sản phẩm", Toast.LENGTH_SHORT).show();
                // Log the error message
                Log.e("Delete Product Error", "Đã xảy ra lỗi khi xóa sản phẩm", t);
            }
        });
    }


    private void showAddProductPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm sản phẩm");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.popup_add_product, null);

        final EditText editTextName = viewInflated.findViewById(R.id.editTextName);
        final EditText editTextMaterial = viewInflated.findViewById(R.id.editTextMaterial);
        final EditText editTextOrigin = viewInflated.findViewById(R.id.editTextOrigin);
        final EditText editTextImageUrl = viewInflated.findViewById(R.id.editTextImageUrl);
        final EditText editTextPrice = viewInflated.findViewById(R.id.editTextPrice);

        Button buttonAdd = viewInflated.findViewById(R.id.buttonAdd);
        Button buttonCancel = viewInflated.findViewById(R.id.buttonCancel);

        builder.setView(viewInflated);

        final AlertDialog dialog = builder.create();
        dialog.show();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String material = editTextMaterial.getText().toString();
                String origin = editTextOrigin.getText().toString();
                String imageUrl = editTextImageUrl.getText().toString();
                double price = Double.parseDouble(editTextPrice.getText().toString());

                Product newProduct = new Product(0, name, material, origin, imageUrl, price);
                addProduct(newProduct);

                dialog.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void addProduct(Product product) {
        RetrofitClient retrofitClient = new RetrofitClient();
        ProductService productService = retrofitClient.getRetrofitInstance().create(ProductService.class);

        Call<Void> call = productService.addProduct(product);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                // Load danh sách sản phẩm
                    loadProducts();
                    Toast.makeText(MainActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("AddProduct", "Không thể thêm sản phẩm: " + response.message());
                    Toast.makeText(MainActivity.this, "Không thể thêm sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // In lỗi kết nối ra log
                Log.e("AddProduct", "Lỗi kết nối", t);
                Toast.makeText(MainActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showUpdateProductPopup(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cập nhật sản phẩm");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.popup_update_product, null);

        final EditText editTextName = viewInflated.findViewById(R.id.editTextName);
        final EditText editTextMaterial = viewInflated.findViewById(R.id.editTextMaterial);
        final EditText editTextOrigin = viewInflated.findViewById(R.id.editTextOrigin);
        final EditText editTextImageUrl = viewInflated.findViewById(R.id.editTextImageUrl);
        final EditText editTextPrice = viewInflated.findViewById(R.id.editTextPrice);

        // Set initial values to EditText fields
        editTextName.setText(product.getName());
        editTextMaterial.setText(product.getMaterial());
        editTextOrigin.setText(product.getOrigin());
        editTextImageUrl.setText(product.getImageUrl());
        editTextPrice.setText(String.valueOf(product.getPrice()));

        Button buttonUpdate = viewInflated.findViewById(R.id.buttonUpdate);
        Button buttonCancel = viewInflated.findViewById(R.id.buttonCancel);

        builder.setView(viewInflated);

        final AlertDialog dialog = builder.create();
        dialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update product with new values
                // Get ID of the product
                int productId = product.getId();
                String name = editTextName.getText().toString();
                String material = editTextMaterial.getText().toString();
                String origin = editTextOrigin.getText().toString();
                String imageUrl = editTextImageUrl.getText().toString();
                double price = Double.parseDouble(editTextPrice.getText().toString());


                Product newProduct = new Product(productId, name, material, origin, imageUrl, price);
                updateProduct(newProduct);

                dialog.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    private void updateProduct(Product product) {
        Log.d("Update Product", "Updating product:");
        Log.d("Update Product", "ID: " + product.getId());
        Log.d("Update Product", "Name: " + product.getName());
        Log.d("Update Product", "Material: " + product.getMaterial());
        Log.d("Update Product", "Origin: " + product.getOrigin());
        Log.d("Update Product", "Image URL: " + product.getImageUrl());
        Log.d("Update Product", "Price: " + product.getPrice());
        RetrofitClient retrofitClient = new RetrofitClient();
        ProductService productService = retrofitClient.getRetrofitInstance().create(ProductService.class);

        Call<Void> call = productService.updateProduct(product.getId(), product);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Product successfully updated
                    loadProducts();
                    Toast.makeText(MainActivity.this, "Sản phẩm đã được cập nhật", Toast.LENGTH_SHORT).show();
                    // You may want to reload the products list after updating

                } else {
                    // Unable to update product
                    Toast.makeText(MainActivity.this, "Không thể cập nhật sản phẩm", Toast.LENGTH_SHORT).show();
                    // Log the error message
                    Log.e("Update Product Error", "Không thể cập nhật sản phẩm. Mã trạng thái: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Error occurred while updating product
                Toast.makeText(MainActivity.this, "Đã xảy ra lỗi khi cập nhật sản phẩm", Toast.LENGTH_SHORT).show();
                // Log the error message
                Log.e("Update Product Error", "Đã xảy ra lỗi khi cập nhật sản phẩm", t);
            }
        });
    }

    // Trong MainActivity
    @Override
    public void onProductClick(Product product) {
        showProductDetailDialog(product);
    }

    private void showProductDetailDialog(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin sản phẩm");

        // Inflate layout for dialog
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.single_product_item, null);

        // Initialize views
        TextView textViewName = viewInflated.findViewById(R.id.textViewProductName);
        TextView textViewPrice = viewInflated.findViewById(R.id.textViewProductPrice);
        TextView textViewMaterial = viewInflated.findViewById(R.id.textViewProductMaterial);
        TextView textViewOrigin = viewInflated.findViewById(R.id.textViewProductOrigin);
        ImageView imageViewProduct = viewInflated.findViewById(R.id.imageViewProduct);

        // Set product details to views
        textViewName.setText(product.getName());
        textViewPrice.setText(String.valueOf(product.getPrice()));
        textViewMaterial.setText("Vật liệu: " + product.getMaterial());
        textViewOrigin.setText("Xuất xứ: " + product.getOrigin());

        // Load image using Picasso
        Picasso.get()
                .load(product.getImageUrl())
                .placeholder(R.drawable.product_image_1) // Hình ảnh tạm thời
                .error(R.drawable.product_image_2) // Hình ảnh hiển thị nếu có lỗi
                .into(imageViewProduct);

        builder.setView(viewInflated);

        // Add a button to close the dialog
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }






}





