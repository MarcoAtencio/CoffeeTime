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
import com.example.coffeetime.model.Sale;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.coffeetime.logic.LCart.cart;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.ViewHolder>{
    private List<Sale> mData;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;

    public SaleAdapter(List<Sale> itemList, Context context ){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){ return mData.size();}

    @Override
    public SaleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.product_history_element, null);
        return new SaleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SaleAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItem(List<Sale> items){ mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView codeSale, dateSale, amountSale, totalSale;


        ViewHolder(View itemView){
            super(itemView);

            codeSale = itemView.findViewById(R.id.nPedido_);
            dateSale = itemView.findViewById(R.id.fechaPedido_);
            amountSale = itemView.findViewById(R.id.cantidad_);
            totalSale = itemView.findViewById(R.id.total_);
        }

        public void bindData(final Sale item){

            //codeSale.setText("N° Pedido" + item.getUid());
            //totalSale.setText("Total" + item.getAmountTotal());

        }
    }
}
