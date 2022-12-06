package com.example.cash_register_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryRecyclerList extends AppCompatActivity implements HistoryRecyclerListAdapter.itemCallBackInterface{

    RecyclerView recyclerView;
    ArrayList<HistoryItem> listHis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_recycler_list);
        Log.d("Cash Register App", "HistoryRecyclerList Created");
        this.setTitle("History List");

        recyclerView = findViewById(R.id.historyRecyclerList);

        HistoryRecyclerListAdapter adapter = new HistoryRecyclerListAdapter(((MyApp) getApplication()).storeObject.getHistory(), this);
        adapter.listener = this;
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // show a toast when one row clicked
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HistoryRecyclerList.this,"Table clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClicked(int pos) {
        HistoryItem invItem =  ((MyApp)getApplication()).storeObject.getHistory().get(pos);
//        String s = "Product: "+invItem.getDescription() + "\n" + "Price: "+invItem.getPrice()+ "\n" + "Purchase date: ";
        String s = invItem.toString();
        Intent hisToDetailIntent = new Intent(HistoryRecyclerList.this, HistoryDetailActivity.class);
        hisToDetailIntent.putExtra("key",s);
        startActivity(hisToDetailIntent);
//        Toast.makeText(this,"Product: "+invItem.getDescription() + "\n" + "Price: "+invItem.getPrice() ,Toast.LENGTH_SHORT).show();

    }
}