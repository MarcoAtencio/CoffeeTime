package com.example.coffeetime.logic;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetime.R;
import com.example.coffeetime.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.coffeetime.common.Format.CurrencyFormat.roundMoney;


public class ProductCartAdapter extends RecyclerView.Adapter<ProductCartAdapter.ViewHolder> {
    private List<Product> mData;
    private LayoutInflater mInflater;
    private Context context;
    TextView[] textViewForProductCart;
    LCart lCart = new LCart();

    public ProductCartAdapter(List<Product> itemList, Context context, TextView[] textViewForProductCar_) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        textViewForProductCart = textViewForProductCar_;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ProductCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.product_cart_element, null);
        return new ProductCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductCartAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItem(List<Product> items) {
        mData = items;
    }

    public void updateFieldToCart() {
        textViewForProductCart[0].setText(roundMoney(lCart.subTotal()));
        textViewForProductCart[1].setText(roundMoney(lCart.igv()));
        textViewForProductCart[2].setText(roundMoney(lCart.Total()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView name, price, cant;
        ImageButton decreaseCant, increaseCant;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.productImgCart);
            name = itemView.findViewById(R.id.productNameCart);
            price = itemView.findViewById(R.id.productPriceCart);
            cant = itemView.findViewById(R.id.productCantCart);
            decreaseCant = itemView.findViewById(R.id.productDecreaseCantCart);
            increaseCant = itemView.findViewById(R.id.productIncreaseCantCart);
        }

        public void bindData(final Product item) {
            Picasso.get().load(item.getPhotoURI()).into(iconImage);
            updateFieldsToProductCart(item);
            decreaseCant.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    item.decreaseCantProductCart();
                    updateFieldsToProductCart(item);
                    if (item.cantProduct() <= 0){
                        lCart.cart.remove(item);
                    }
                }
            });
            increaseCant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.increaseCantProductCart();
                    updateFieldsToProductCart(item);
                }
            });
        }

        public void updateFieldsToProductCart(final Product item) {
            name.setText(item.getName());
            price.setText("S/. " + item.getPrice());
            cant.setText("" + item.cantProduct());
            updateFieldToCart();
        }
    }

}