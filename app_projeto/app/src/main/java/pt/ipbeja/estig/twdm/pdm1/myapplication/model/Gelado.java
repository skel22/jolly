package pt.ipbeja.estig.twdm.pdm1.myapplication.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity
    public class Gelado {
        @PrimaryKey
        private long gelId;
        private String gelName;
        private double gelPrice;
        private String gelImg;


        public Gelado(long gelId, String gelName, double gelPrice, String gelImg){
            this.gelId = gelId;
            this.gelName = gelName;
            this.gelPrice = gelPrice;
            this.gelImg = gelImg;
        }

        public long getGelId(){
            return gelId;
        }

        public String getGelName() { return gelName; }

        public double getGelPrice() {
            return gelPrice;
        }

        public String getGelImg() {
            return gelImg;
        }
    }
