/*
package com.example.noithat.Model;

 {
    private int id;
    private String name;
    private String material;
    private String origin;
    private String imageUrl;
    private double price;

    public Product(int id, String name, String material, String origin, String imageUrl, double price) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.origin = origin;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

package com.example.noithat.Adapter     ;


        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.noithat.Model.Product;
        import com.example.noithat.R;
        import com.squareup.picasso.Picasso;

        import java.util.ArrayList;
        import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;
    private ProductClickListener clickListener;
    private ProductLongClickListener longClickListener;
    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    // Constructor không tham số
    public ProductAdapter() {
        this.products = new ArrayList<>(); // Khởi tạo danh sách sản phẩm trống
    }

    public ProductAdapter(ProductClickListener clickListener, ProductLongClickListener longClickListener) {
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void setProductList(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewProduct;
        private TextView textViewProductName;
        private TextView textViewProductPrice;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onProductClick(products.get(getAdapterPosition()));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (longClickListener != null) {
                        longClickListener.onProductLongClick(products.get(getAdapterPosition()));
                        return true;
                    }
                    return false;
                }
            });
        }

        void bind(Product product) {
            Picasso.get().load(product.getImageUrl()).placeholder(R.drawable.product_image_1).into(imageViewProduct);
            textViewProductName.setText(product.getName());
            textViewProductPrice.setText(String.valueOf(product.getPrice()));
        }
    }

    public interface ProductClickListener {
        void onProductClick(Product product);
    }

    public interface ProductLongClickListener {
        void onProductLongClick(Product product);
    }

    // Method to remove a product from the list
    public void removeProduct(int productId) {
        // Find the index of the product with the given ID in the list
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productId) {
                index = i;
                break;
            }
        }

        // If the product is found, remove it from the list and notify the adapter
        if (index != -1) {
            products.remove(index);
            notifyItemRemoved(index);
        }
    }

    public void addProduct(Product product) {
        // Thêm sản phẩm vào danh sách
        products.add(product);
        // Thông báo cho adapter biết rằng có sự thay đổi trong danh sách sản phẩm
        notifyDataSetChanged();
    }


}

package com.example.noithat.main;

        import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import com.example.noithat.Model.Product;
        import com.example.noithat.R;
        import com.example.noithat.Server.ProductService;
        import com.example.noithat.Retrofit.RetrofitClient;
        import com.example.noithat.Adapter.ProductAdapter;

        import android.os.Parcelable;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import android.content.DialogInterface;
        import androidx.appcompat.app.AlertDialog;


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
    public void onProductClick(Product product) {
        // Xử lý khi người dùng click vào sản phẩm
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

        Call<Product> call = productService.addProduct(product);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Product newProduct = response.body();
                    productList.add(newProduct);
                    productAdapter.setProductList(productList);
                    Toast.makeText(MainActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Không thể thêm sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
*/
