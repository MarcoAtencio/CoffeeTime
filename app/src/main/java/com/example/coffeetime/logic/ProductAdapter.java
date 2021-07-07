package com.example.coffeetime.logic;

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
import com.example.coffeetime.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.coffeetime.logic.LCart.cart;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ProductAdapter(List<Product> itemList, Context context ){
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
            /*
            stock = itemView.findViewById(R.id.stock_);
            category = itemView.findViewById(R.id.category_);

             */
            price = itemView.findViewById(R.id.price_);
            button = itemView.findViewById(R.id.button);
        }

        public void bindData(final Product item){
            Picasso.get().load(item.getPhotoURI()).into(iconImage);
            name.setText(item.getName());
            /*
            stock.setText("Disponible: " + item.getStock());

            category.setText("Categoría: " + item.getCategory());
             */
            price.setText("S/. " + item.getPrice());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.increaseCantProductCart();
                    cart.add(item);
                }
            });
        }
    }

}