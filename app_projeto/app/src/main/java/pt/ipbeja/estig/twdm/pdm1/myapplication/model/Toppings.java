package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Toppings {
    @PrimaryKey
    private long topId;
    private String topName;
    private double topPrice;
    private String topImg;

    public Toppings(long topId, String topName, double topPrice, String topImg){
        this.topId = topId;
        this.topName = topName;
        this.topPrice = topPrice;
        this.topImg = topImg;
    }

    public long getTopId(){
        return topId;
    }

    public String getTopName() {
        return topName;
    }

    public double getTopPrice() {
        return topPrice;
    }

    public String getTopImg() {
        return topImg;
    }
}
