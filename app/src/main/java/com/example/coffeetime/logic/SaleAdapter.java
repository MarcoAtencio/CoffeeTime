package com.example.coffeetime.logic;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetime.R;
import com.example.coffeetime.model.Sale;

import java.util.List;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.ViewHolder> {
    private List<Sale> mData;
    private LayoutInflater mInflater;
    private Context context;

    public SaleAdapter(List<Sale> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public SaleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.product_history_element, null);
        return new SaleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SaleAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItem(List<Sale> items) {
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView codeSale, dateSale, amountSale, totalSale;

        ViewHolder(View itemView) {
            super(itemView);
            codeSale = itemView.findViewById(R.id.nPedido_);
            dateSale = itemView.findViewById(R.id.fechaPedido_);
            amountSale = itemView.findViewById(R.id.cantidad_);
            totalSale = itemView.findViewById(R.id.total_);
        }

        public void bindData(final Sale item) {

            if (item.getState()) {

                dateSale.setTextColor(Color.parseColor("#FF5733"));
            } else {
                dateSale.setTextColor(Color.parseColor("#FFC733"));

            }
            dateSale.setText("Estado :" + item.getState());
            codeSale.setText("N°. Pedido : " + item.getUid());
            //dateSale.setText(item.getD);
            totalSale.setText("Total: " + item.getAmountTotal());
            Toast.makeText(context, item.getUid(), Toast.LENGTH_SHORT).show();

        }
    }
}
