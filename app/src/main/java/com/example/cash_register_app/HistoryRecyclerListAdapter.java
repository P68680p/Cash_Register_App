package com.example.cash_register_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryRecyclerListAdapter extends RecyclerView.Adapter<HistoryRecyclerListAdapter.HistoryListViewHolder> {

    ArrayList<HistoryItem> list;
    Context context;
    itemCallBackInterface listener;

    interface itemCallBackInterface{
        void onItemClicked(int pos);
    }
    public HistoryRecyclerListAdapter(ArrayList<HistoryItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_recycle_adapter_row, parent, false);
        return new HistoryListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListViewHolder holder, int position) {
        String s1 = list.get(position).getDescription();
//        String s2= String.valueOf(list.get(position).getQuantity());
        String s3 = String.valueOf(list.get(position).getPrice());
        holder.nameProd.setText(s1);
        holder.amount.setText(String.valueOf(list.get(position).getQuantity()));
        holder.totalPrice.setText(s3);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //INNER class
    class HistoryListViewHolder extends RecyclerView.ViewHolder {
        TextView nameProd;
        TextView amount;
        TextView totalPrice;

        public HistoryListViewHolder(@NonNull View itemView) {
            super(itemView);
            nameProd = itemView.findViewById(R.id.rec_row_prod_name);
            amount = itemView.findViewById(R.id.rec_row_quantity);
            totalPrice = itemView.findViewById(R.id.rec_row_total_price);

            itemView.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAdapterPosition());
                }
            }));
        }
    }
}
