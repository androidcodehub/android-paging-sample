package com.androidcodehub.androidpagingsample.ui;


import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcodehub.androidpagingsample.R;
import com.androidcodehub.androidpagingsample.model.Product;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ProductAdapter extends PagedListAdapter<Product, ProductAdapter.ProductViewHolder> {

    private Context mContext;


    public ProductAdapter(Context context, boolean twoPane) {
        super(DIFF_CALLBACK);
        mContext = context;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_content, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = getItem(position);
        if (product != null) {
            holder.bindTo(product);
        } else {

            holder.clear();
        }
    }
    public static final DiffCallback<Product> DIFF_CALLBACK = new DiffCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldProduct, @NonNull Product newProduct) {

            return oldProduct.productId == newProduct.productId;
        }
        @Override
        public boolean areContentsTheSame(@NonNull Product oldProduct, @NonNull Product newProduct) {

            return oldProduct.equals(newProduct);
        }
    };

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private ImageView productImage;
        private TextView title;
        private TextView price;


        public ProductViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.productImage = (ImageView) itemView.findViewById(R.id.product);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.price = (TextView) itemView.findViewById(R.id.price);


        }

        public void bindTo(Product product) {

            productImage.setImageURI(Uri.parse(product.productImage));
            title.setText(product.productName);
            price.setText(product.price);


            Glide.with(mContext).load(product.productImage)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(productImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(mContext,product.productName,Toast.LENGTH_LONG).show();

                }


            });
        }

        public void clear() {
            //TODO

            productImage.setImageURI(null);
            title.setText(null);
            price.setText(null);


        }
    }



}
