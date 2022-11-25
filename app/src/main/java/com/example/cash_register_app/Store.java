package com.example.cash_register_app;


import android.app.Application;

import java.util.ArrayList;
import java.util.Date;

public class Store extends Application {

    private static ArrayList<InventoryItem> inventory;
    private static ArrayList<HistoryItem> history;     // history array

    public Store() {
    }

    public Store(ArrayList<InventoryItem> inventory, ArrayList<HistoryItem> history) {
        this.inventory = inventory;
        this.history = history;
    }

    public static ArrayList<InventoryItem> getInventory() {
        if (inventory == null) {
            inventory = new ArrayList<>();
            inventory.add(new InventoryItem(100.0, 10, "pants"));
            inventory.add(new InventoryItem(400.0, 40, "shoes"));
            inventory.add(new InventoryItem(99.0, 50, "shirts"));
            inventory.add(new InventoryItem(30.0, 10, "skirts"));
        }
        return inventory;
    }

    public static ArrayList<HistoryItem> getHistory() {
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
    public static double purchase(int index, int howMany) {
//        System.out.println("-------------> inside of purchase");
//        System.out.println("index = " + index + ",   howMany = " + howMany);
        InventoryItem objectSelect = inventory.get(index);
        if (objectSelect.isEnoughQuantity(howMany)) {
            double totalAmount = objectSelect.getPrice() * howMany;
            HistoryItem hObj = new HistoryItem(totalAmount, howMany, objectSelect.getDescription(), new Date());
//            System.out.println("history object = "+ hObj.toString());
            getHistory().add(hObj);
            return totalAmount;
        }
        return -1;
    }


    //for manager's menu for restock and report
//    public InventoryItem restock(int index, int qntToAdd) {
//        InventoryItem objectSelect = inventory.get(index);
//        selected.setQuantity(selected.getQuantity() + qntToAdd);
//        System.out.println(selected);
//        return selected;
//    }

    public void report() {
        System.out.println("Here is a report about purchasing: ");
        if (history.size() == 0) {
            System.out.println("    Unfortunately, it was no purchasing today.");
        }
        for (HistoryItem xI : history) {
            System.out.println(xI.toString());
        }
        System.out.println();
    }
}
