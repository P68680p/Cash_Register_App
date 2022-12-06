package com.example.cash_register_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener {
    Button toHistoryB;
    Button toRestockB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Log.d("Cash Register App", "ManagerActivity Created");
        this.setTitle("Manager Menu");

        toHistoryB = findViewById(R.id.toHistoryBut);
        toRestockB = findViewById(R.id.toRestockBut);

        toHistoryB.setOnClickListener(this);
        toRestockB.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toHistoryBut:
                Intent ManToHisIntent = new Intent(ManagerActivity.this, HistoryRecyclerList.class);
                startActivity(ManToHisIntent);
//                int s = ((MyApp) getApplication()).storeObject.getHistory().size();
//                System.out.println("----------------------------------------> size history = "+ s);
//                System.out.println("----------------------------------------> array history = "+ ((MyApp) getApplication()).storeObject.getHistory());
                break;

                case R.id.toRestockBut:
                Intent ManToResIntent = new Intent(ManagerActivity.this, RestockActivity.class);
                startActivity(ManToResIntent);
                break;
        }
    }
}