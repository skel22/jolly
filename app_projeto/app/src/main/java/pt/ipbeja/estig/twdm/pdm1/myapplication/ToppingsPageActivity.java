package pt.ipbeja.estig.twdm.pdm1.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.ipbeja.estig.twdm.pdm1.myapplication.model.AppDatabase;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Toppings;
import pt.ipbeja.estig.twdm.pdm1.myapplication.view.GeladoAdapter;
import pt.ipbeja.estig.twdm.pdm1.myapplication.view.MainPage;
import pt.ipbeja.estig.twdm.pdm1.myapplication.view.ToppingsAdapter;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.GeladoViewModel;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.ToppingsViewModel;

public class ToppingsPageActivity extends AppCompatActivity implements ToppingsAdapter.ToppingsAdapterEventListener{
    private ToppingsViewModel viewModel;
    private ToppingsAdapter toppingsAdapter;
    ImageView imageViewGoHome;
    ImageView imageViewCart;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toppings);

        // Initialize ViewModel instance for Toppings
        viewModel = new ViewModelProvider(this).get(ToppingsViewModel.class);

        // Observe changes in the ToppingsViewModel and update the UI when data changes
        viewModel.getAll().observe(this, toppingsItems -> {
            // Initialize the ToppingsAdapter and set it on the RecyclerView
            this.toppingsAdapter = new ToppingsAdapter(toppingsItems, this);
            this.toppingsAdapter.refreshList(toppingsItems);
        });

        // Set up RecyclerView to display Toppings items
        RecyclerView recyclerViewToppings = findViewById(R.id.recyclerViewToppings);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

         recyclerViewToppings.setAdapter(this.toppingsAdapter);
         recyclerViewToppings.setLayoutManager(layoutManager);

        viewModel.getAll().observe(this, toppingsItems -> {
            this.toppingsAdapter = new ToppingsAdapter(toppingsItems, this);
            recyclerViewToppings.setAdapter(this.toppingsAdapter);
            recyclerViewToppings.setLayoutManager(layoutManager);
        });

         // Set up click listeners for home, cart, and skip buttons
         imageViewGoHome = (ImageView) findViewById(R.id.imageViewGoBack4);
         imageViewGoHome.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 // Navigate to the MainPage
                 Intent intent = new Intent(ToppingsPageActivity.this, MainPage.class);
                    startActivity(intent);
                 }
            });

         Button buttonSkip = findViewById(R.id.buttonSkip);
         buttonSkip.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 // Navigate to the MainPage
                 Intent intent = new Intent(ToppingsPageActivity.this, MainPage.class);
                    startActivity(intent);
                }
            });

         imageViewCart = (ImageView) findViewById(R.id.imageViewCart4);
         imageViewCart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 // Navigate to the CartActivity
                 Intent int2 = new Intent(ToppingsPageActivity.this, CartActivity.class);
                 startActivity(int2);
             }
         });
    }

    // Handle the click event when a Toppings item is clicked
    @Override
    public void onToppingsClicked(long topId){
        // Start ToppingsItemActivity with the selected Toppings item ID
        ToppingsItemActivity.startActivity(this, topId);
    }
}