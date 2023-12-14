package pt.ipbeja.estig.twdm.pdm1.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import pt.ipbeja.estig.twdm.pdm1.myapplication.view.MainActivity;

public class PagamentoActivity extends AppCompatActivity {
    ImageView imageViewMB;
    ImageView imageViewDinheiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metodo_pagamento);

        // Initialize views
        imageViewMB = findViewById(R.id.imageViewMBWay);
        imageViewDinheiro = findViewById(R.id.imageViewDinheiro);

        // Set click listener for the MB Way ImageView
        imageViewMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log that MB Way is clicked
                Log.i("Click", "MB Way");

                // Start MainActivity when MB Way is clicked
                Intent intent = new Intent(PagamentoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for the Dinheiro (Cash) ImageView
        imageViewDinheiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log that Dinheiro (Cash) is clicked
                Log.i("Click", "Dinheiro");

                // Start MainActivity when Dinheiro (Cash) is clicked
                Intent intent = new Intent(PagamentoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

