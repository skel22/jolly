package pt.ipbeja.estig.twdm.pdm1.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import pt.ipbeja.estig.twdm.pdm1.myapplication.CartActivity;
import pt.ipbeja.estig.twdm.pdm1.myapplication.CrepePageActivity;
import pt.ipbeja.estig.twdm.pdm1.myapplication.GeladoPageActivity;
import pt.ipbeja.estig.twdm.pdm1.myapplication.R;
import pt.ipbeja.estig.twdm.pdm1.myapplication.WafflePageActivity;

public class MainPage extends AppCompatActivity {
    ImageView imageViewGelado;
    ImageView imageViewCrepe;
    ImageView imageViewWaffle;
    ImageView imageViewGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        imageViewGelado = (ImageView) findViewById(R.id.sendToGelado);
        imageViewGelado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Click", "Gelado");
                Intent intent = new Intent(MainPage.this, GeladoPageActivity.class);
                finish();
                startActivity(intent);
            }
        });

        imageViewCrepe = (ImageView) findViewById(R.id.sendToCrepe);
        imageViewCrepe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Click", "Crepe");
                Intent int2 = new Intent(MainPage.this, CrepePageActivity.class);
                finish();
                startActivity(int2);
            }
        });

        imageViewWaffle = (ImageView) findViewById(R.id.sendToWaffle);
        imageViewWaffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Click", "Waffle");
                Intent int3 = new Intent(MainPage.this, WafflePageActivity.class);
                finish();
                startActivity(int3);
            }
        });

        imageViewGoToCart = (ImageView) findViewById(R.id.imageViewCart5);
        imageViewGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int4 = new Intent(MainPage.this, CartActivity.class);
                finish();
                startActivity(int4);
            }
        });
    }
}