package com.example.cash_register_app;


import androidx.annotation.NonNull;

import java.util.Date;

public class HistoryItem extends InventoryItem{
    private final Date datePurchase;

    //constructors
//    HistoryItem() {}
    public HistoryItem(double price, int quantity, String description, Date datePurchase) {
        super(price, quantity, description);
        this.datePurchase = datePurchase;
    }

    //methods
    @NonNull
    @Override
    public String toString() {
        return "HistoryOfPurchasing {" +
                " price=" + getPrice() +
                ", quantity=" + getQuantity() +
                ", description='" + getDescription() + '\'' +
                ", date=" + datePurchase +
                '}';    }
}
