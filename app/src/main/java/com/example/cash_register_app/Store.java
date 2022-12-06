package com.example.cash_register_app;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Store {

    private ArrayList<InventoryItem> inventory;
    private ArrayList<HistoryItem> history;     // history array

    public Store() {
    }

    public ArrayList<InventoryItem> getInventory() {
        if (inventory == null) {
            inventory = new ArrayList<>();
            inventory.add(new InventoryItem(100.0, 10, "pants"));
            inventory.add(new InventoryItem(400.0, 40, "shoes"));
            inventory.add(new InventoryItem(99.0, 50, "shirts"));
            inventory.add(new InventoryItem(30.0, 10, "skirts"));
        }
        return inventory;
    }

    public ArrayList<HistoryItem> getHistory() {
        if (history == null) {
            history = new ArrayList<>();
        }
        return history;
    }


    //methods
    public void printInventory() {
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + "-" + inventory.get(i).toString());
        }
    }

    //this is a buying items and update history of purchasing
    public double purchase(int index, int howMany) {
        InventoryItem objectSelect = this.getInventory().get(index);
        if (objectSelect.isEnoughQuantity(howMany)) {
            double totalAmount = objectSelect.getPrice() * howMany;
            HistoryItem hObj = new HistoryItem(totalAmount, howMany, objectSelect.getDescription(), new Date());
            this.getHistory().add(hObj);
            return totalAmount;
        }
        return -1;
    }


    //for manager's menu for restock and report
    public InventoryItem restock(int index, int qntToAdd) {
        InventoryItem objectSelect = this.getInventory().get(index);
        objectSelect.setQuantity(objectSelect.getQuantity() + qntToAdd);
        System.out.println(objectSelect);
        return objectSelect;
    }

    public void report() {
        System.out.println("Here is a report about purchasing: ");
        if (history.size() == 0) {
            System.out.println("Unfortunately, it was no purchasing today.");
        }
        for (HistoryItem xI : history) {
            System.out.println(xI.toString());
        }
        System.out.println();
    }

}
