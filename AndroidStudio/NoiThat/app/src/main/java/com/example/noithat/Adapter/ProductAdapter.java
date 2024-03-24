
package com.example.noithat.Adapter;

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
            Picasso.get().load(product.getImageUrl()).placeholder(R.drawable.product_image_2).into(imageViewProduct);
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