package com.example.cash_register_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HistoryDetailActivity extends AppCompatActivity {

    TextView text;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        Log.d("Cash Register App", "HistoryDetailActivity Created");
        this.setTitle("History detail");

        text = findViewById(R.id.detailsHistory);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            //The key argument here must match that used in the other activity
            text.setText(value);
        }

    }
}