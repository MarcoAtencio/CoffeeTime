package com.example.coffeetime.logic;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetime.R;
import com.example.coffeetime.model.Sale;

import java.text.SimpleDateFormat;
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
        TextView codeSale, stateSale, amountSale, totalSale, dateSale;
        String s;

        ViewHolder(View itemView) {
            super(itemView);
            codeSale = itemView.findViewById(R.id.nPedido_);
            stateSale = itemView.findViewById(R.id.stateSale_);
            dateSale = itemView.findViewById(R.id.date_);
            amountSale = itemView.findViewById(R.id.cantidad_);
            totalSale = itemView.findViewById(R.id.total_);
        }

        public void bindData(final Sale item) {

            if (item.getState()) {
                stateSale.setText("Entregado");
                stateSale.setTextColor(Color.parseColor("#59E325"));

            } else {
                stateSale.setText("Pendiente");
                stateSale.setTextColor(Color.parseColor("#FF0000"));

            }
            s = new SimpleDateFormat("dd--MM--yyyy").format(item.getDateSale());
            codeSale.setText(item.getUid());
            dateSale.setText(s);
            totalSale.setText("S/. " + item.getAmountTotal());
            Toast.makeText(context, item.getUid(), Toast.LENGTH_SHORT).show();

        }
    }
}
