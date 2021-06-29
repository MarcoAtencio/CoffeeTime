package com.example.coffeetime.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.example.coffeetime.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> mData;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;
    Cart cart = new Cart();

    public ProductAdapter(List<Product> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){ return mData.size();}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.product_element, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItem(List<Product> items){ mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView name, stock, category, price;
        Button button;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.name_);
            stock = itemView.findViewById(R.id.stock_);
            category = itemView.findViewById(R.id.category_);
            price = itemView.findViewById(R.id.price_);
            button = itemView.findViewById(R.id.button);
        }

        public void bindData(final Product item){
            Picasso.get().load(item.getPhotoURI()).into(iconImage);
            name.setText(item.getName());
            stock.setText("Stock: " + item.getStock());
            category.setText("Categoria: " + item.getCategory());
            price.setText("Precio: " + item.getPrice());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context.getApplicationContext(), item.getName(), Toast.LENGTH_SHORT).show();
                    cart.addProduct(item);
                }
            });
        }
    }

}