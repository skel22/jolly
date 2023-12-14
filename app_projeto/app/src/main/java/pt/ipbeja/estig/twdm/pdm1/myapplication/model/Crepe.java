package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.os.Bundle;

@Entity
public class Crepe {
    @PrimaryKey
    private long crepeId;
    private String crepeName;
    private double crepePrice;
    private String crepeImg;

    public Crepe(long crepeId, String crepeName, double crepePrice, String crepeImg){
        this.crepeId = crepeId;
        this.crepeName = crepeName;
        this.crepePrice = crepePrice;
        this.crepeImg = crepeImg;
    }

    public long getCrepeId(){
        return crepeId;
    }

    public String getCrepeName() {
        return crepeName;
    }

    public double getCrepePrice() {
        return crepePrice;
    }

    public String getCrepeImg() {
        return crepeImg;
    }

}