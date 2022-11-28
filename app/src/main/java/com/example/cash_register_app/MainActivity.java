package com.example.cash_register_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String quant = "";
    String prod = "";
    double price;
    int index;
    ItemsListBaseAdapter adapter;

    TextView prodTypeText;
    TextView quantText;
    TextView resultText;
    Button n0;
    Button n1;
    Button n2;
    Button n3;
    Button n4;
    Button n5;
    Button n6;
    Button n7;
    Button n8;
    Button n9;
    Button clearBut;
    Button buyBut;
    ListView itemsList;
    Button manag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Cash Register App", "Activity Created");

        prodTypeText = findViewById(R.id.showProductType);
        quantText = findViewById(R.id.showQuantity);
        resultText = findViewById(R.id.showTotalHint);
        n0 = findViewById(R.id.num0);
        n1 = findViewById(R.id.num1);
        n2 = findViewById(R.id.num2);
        n3 = findViewById(R.id.num3);
        n4 = findViewById(R.id.num4);
        n5 = findViewById(R.id.num5);
        n6 = findViewById(R.id.num6);
        n7 = findViewById(R.id.num7);
        n8 = findViewById(R.id.num8);
        n9 = findViewById(R.id.num9);
        clearBut = findViewById(R.id.clearBut);
        buyBut = findViewById(R.id.buyButton);
        itemsList = findViewById(R.id.list_items_view);
        manag = findViewById(R.id.manager);

        n0.setOnClickListener(this);
        n1.setOnClickListener(this);
        n2.setOnClickListener(this);
        n3.setOnClickListener(this);
        n4.setOnClickListener(this);
        n5.setOnClickListener(this);
        n6.setOnClickListener(this);
        n7.setOnClickListener(this);
        n8.setOnClickListener(this);
        n9.setOnClickListener(this);
        clearBut.setOnClickListener(this);
        buyBut.setOnClickListener(this);
        manag.setOnClickListener(this);

        adapter = new ItemsListBaseAdapter(((MyApp) this.getApplication()).storeObject.getInventory(), this);
        itemsList.setAdapter(adapter);
        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, "The selected Item is " + ((MyApp) getApplication()).storeObject.getInventory().get(i).getDescription(), Toast.LENGTH_LONG).show();
                prodTypeText.setText(((MyApp) getApplication()).storeObject.getInventory().get(i).getDescription());
                price = ((MyApp) getApplication()).storeObject.getInventory().get(i).getPrice();
                index = i;
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clearBut:
                quant = "";
                prodTypeText.setText("");
                quantText.setText(quant);
                resultText.setText("");
                break;
            case R.id.buyButton:
                //get product and quantity from layout
                prod = prodTypeText.getText().toString();
                quant = quantText.getText().toString();
                //check if all fields is not empty
                if (!quant.isEmpty() && !prod.isEmpty()) {
                    InventoryItem item = new InventoryItem();
                    double cost = ((MyApp) getApplication()).storeObject.purchase(index, Integer.parseInt(quant));
                    if (cost != -1) {
                        resultText.setText("It costs " + cost + "$");
                    } else {
                        Toast.makeText(MainActivity.this, "Not enough quantity in the stock! ", Toast.LENGTH_LONG).show();
                        quant = "";
                    }
                } else {
                    Toast.makeText(MainActivity.this, "All fields are required! ", Toast.LENGTH_LONG).show();
                }
                //update ArrayList in BaseAdapter after purchase
                adapter.notifyDataSetChanged();
                //get current purchase and show alert about it
                HistoryItem currentHistory = ((MyApp) getApplication()).storeObject.getHistory().get(index);
                showTheAlert(currentHistory);


                //
                quant = "";
                prodTypeText.setText("");
                quantText.setText(quant);
                resultText.setText("");
                //


                break;
            case R.id.manager:
                Intent firstIntent = new Intent(MainActivity.this, ManagerActivity.class);
                startActivity(firstIntent);
                break;
            default:
                quant += ((Button) view).getText().toString();
                quantText.setText(quant);
        }
    }

    void showTheAlert(HistoryItem history) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Thank You for your purchase:" + "\n" + history.getQuantity() + " " + history.getDescription() + " for total amount " + history.getPrice())
                .setTitle("Congratulations!!");
        builder.setNegativeButton("OK", null);
        builder.create().show();
    }
}