package com.example.cash_register_app;


import android.os.Parcel;
import android.os.Parcelable;

public class InventoryItem implements Parcelable{
    private double price;
    private int quantity;
    private String description;

    //constructors
    public InventoryItem() {
    }

    public InventoryItem(double price, int quantity, String description) {
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    protected InventoryItem(Parcel in) {
        price = in.readDouble();
        quantity = in.readInt();
        description = in.readString();
    }

    public static final Creator<InventoryItem> CREATOR = new Creator<InventoryItem>() {
        @Override
        public InventoryItem createFromParcel(Parcel in) {
            return new InventoryItem(in);
        }

        @Override
        public InventoryItem[] newArray(int size) {
            return new InventoryItem[size];
        }
    };

    //methods
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("You entered wrong price");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            System.out.println("You entered wrong quantity");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "InventoryItem {" +
                "price=" + price +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }

    public boolean isEnoughQuantity(int UserChoiceHowMany) {
        boolean result = false;
        if (quantity >= UserChoiceHowMany) {
            quantity = quantity - UserChoiceHowMany;
            result = true;
        }
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(price);
        parcel.writeInt(quantity);
        parcel.writeString(description);
    }
}

