package com.example.cash_register_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ItemsListBaseAdapter extends BaseAdapter {
    //    Store s = new Store();
    ArrayList<InventoryItem> list = Store.getInventory();
    Context context;

    ItemsListBaseAdapter(ArrayList<InventoryItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        System.out.println("inside of public View getView(int i, View view, ViewGroup viewGroup)");
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.list_base_adapter_row, viewGroup, false);

        TextView prName = v.findViewById(R.id.row_prod_name);
        prName.setText(String.valueOf(list.get(i).getDescription()));

        TextView pric = v.findViewById(R.id.row_price);
        pric.setText(list.get(i).getPrice() + "");

        TextView qnt = v.findViewById(R.id.row_quantity);
        qnt.setText(list.get(i).getQuantity() + "");

        return v;
    }
}