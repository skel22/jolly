package pt.ipbeja.estig.twdm.pdm1.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pt.ipbeja.estig.twdm.pdm1.myapplication.R;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.GeladoViewModel;

public class GeladoPageActivity extends AppCompatActivity implements GeladoAdapter.GeladoAdapterEventListener {
    private GeladoViewModel viewModel;
    private GeladoAdapter gelAdapter;
    ImageView imageViewGoHome;
    ImageView imageViewCart;
    Button buttonToppings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelado);

        // Initialize ViewModel instance
        viewModel = new ViewModelProvider(this).get(GeladoViewModel.class);

        // Observe changes in the GeladoViewModel
        viewModel.getAll().observe(this, geladoItems -> {
            // Create and set the GeladoAdapter with the updated geladoItems
            this.gelAdapter = new GeladoAdapter(geladoItems, this);
            this.gelAdapter.refreshList(geladoItems);
        });

        // Initialize RecyclerView and its layout manager
        RecyclerView recyclerViewGelado = findViewById(R.id.recyclerViewGeladoItem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // Set GeladoAdapter, RecyclerView, and layout manager
        recyclerViewGelado.setAdapter(this.gelAdapter);
        recyclerViewGelado.setLayoutManager(layoutManager);

        // Observe changes in the GeladoViewModel and update the adapter and layout manager
        viewModel.getAll().observe(this, geladoItems -> {
            this.gelAdapter = new GeladoAdapter(geladoItems, this);
            recyclerViewGelado.setAdapter(this.gelAdapter);
            recyclerViewGelado.setLayoutManager(layoutManager);
        });

        // Set click listeners for home, cart, and toppings buttons
        imageViewGoHome = findViewById(R.id.imageViewGoBack);
        imageViewGoHome.setOnClickListener(view -> {
            Intent intent = new Intent(GeladoPageActivity.this, MainPage.class);
            finish();
            startActivity(intent);
        });

        imageViewCart = findViewById(R.id.imageViewCart);
        imageViewCart.setOnClickListener(view -> {
            Intent int2 = new Intent(GeladoPageActivity.this, CartActivity.class);
            finish();
            startActivity(int2);
        });

        buttonToppings = findViewById(R.id.buttonGoToToppings);
        buttonToppings.setOnClickListener(view -> {
            Intent int3 = new Intent(GeladoPageActivity.this, ToppingsPageActivity.class);
            startActivity(int3);
        });
    }

    // Handle the click event for a Gelado item, start the GeladoItemActivity with the corresponding gelID
    @Override
    public void onGeladoClicked(long gelID) {
        GeladoItemActivity.startActivity(this, gelID);
    }
}
