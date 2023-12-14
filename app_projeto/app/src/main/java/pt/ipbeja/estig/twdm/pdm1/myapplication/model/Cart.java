package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cart {
    @PrimaryKey(autoGenerate = true)
    private int itemId;
    private String itemName;
    private double price;
    private int amount;

    public Cart(int itemId, String itemName, double price, int amount) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() { return price; }

    public int getAmount(){ return amount; }

    public int getItemId(){ return itemId; }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}


