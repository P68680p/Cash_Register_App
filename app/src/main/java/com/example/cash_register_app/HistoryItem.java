package com.example.cash_register_app;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class HistoryItem extends InventoryItem implements Parcelable{
    private Date datePurchase;

    //constructors
//    HistoryItem() {}
    public HistoryItem(double price, int quantity, String description, Date datePurchase) {
        super(price, quantity, description);
        this.datePurchase = datePurchase;
    }

    protected HistoryItem(Parcel in) {
    }

    public static final Creator<HistoryItem> CREATOR = new Creator<HistoryItem>() {
        @Override
        public HistoryItem createFromParcel(Parcel in) {
            return new HistoryItem(in);
        }

        @Override
        public HistoryItem[] newArray(int size) {
            return new HistoryItem[size];
        }
    };

    //methods
    @NonNull
    @Override
    public String toString() {
        return "Product: "+ getDescription()+",\n"+
                "price: " + getPrice() +",\n"+
                "quantity: " + getQuantity() +",\n"+
                "date: " + datePurchase; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
