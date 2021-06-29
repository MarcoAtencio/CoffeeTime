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

public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.ViewHolder> {
    private List<Product> mData;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;
    LCart LCart = new LCart();

    public ProductCartAdapter(List<Product> itemList, Context context ){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){ return mData.size();}

    @Override
    public ProductCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = mInflater.inflate(R.layout.product_cart_element, null);

        return new ProductCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductCartAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItem(List<Product> items){ mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView name, price;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.productImgCart);
            name = itemView.findViewById(R.id.productNameCart);
            price = itemView.findViewById(R.id.productPriceCart);
        }

        public void bindData(final Product item){
            Picasso.get().load(item.getPhotoURI()).into(iconImage);
            name.setText(item.getName());
            price.setText("S/: " + item.getPrice());
        }
    }

}