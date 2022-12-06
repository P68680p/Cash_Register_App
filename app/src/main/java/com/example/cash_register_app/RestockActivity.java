package com.example.cash_register_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity implements View.OnClickListener {

    Button okButton;
    Button cancelButton;
    ListView listRestock;
    EditText restockQuant;
    TextView selectedProduct;
    ArrayList<InventoryItem> list;
    ItemsListBaseAdapter adapter;

    int index;
    String prod;
    String quant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);
        Log.d("Cash Register App", "RestockActivity Created");
        this.setTitle("Restock Menu");

        okButton = findViewById(R.id.okBut);
        cancelButton = findViewById(R.id.cancelBut);
        listRestock = findViewById(R.id.restockList);
        restockQuant = findViewById(R.id.editQuantity);
        selectedProduct = findViewById(R.id.productName);

        okButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        list = ((MyApp) getApplication()).storeObject.getInventory();
        adapter = new ItemsListBaseAdapter(list, this);
        listRestock.setAdapter(adapter);
        listRestock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                selectedProduct.setText(((MyApp) getApplication()).storeObject.getInventory().get(i).getDescription());
                //price = ((MyApp) getApplication()).storeObject.getInventory().get(i).getPrice();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.okBut:
                //get product and quantity from layout
                prod = selectedProduct.getText().toString();
                quant = restockQuant.getText().toString();
                //check if all fields is not empty
                if (!quant.isEmpty() && !prod.isEmpty()) {
                    String qS = restockQuant.getText().toString();
                    ((MyApp) getApplication()).storeObject.restock(index, Integer.parseInt(qS));
                    adapter.notifyDataSetChanged();
                    Toast.makeText(RestockActivity.this, "You restocked your Inventory!", Toast.LENGTH_LONG).show();
                    restockQuant.setText("");
                    selectedProduct.setText("");
                } else {
                    Toast.makeText(RestockActivity.this, "All fields are required! ", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cancelBut:
                Intent back = new Intent(this, ManagerActivity.class);
                startActivity(back);
                break;
        }
    }
}