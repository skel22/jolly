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
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Crepe;
import pt.ipbeja.estig.twdm.pdm1.myapplication.view.CrepeAdapter;
import pt.ipbeja.estig.twdm.pdm1.myapplication.view.MainPage;
import pt.ipbeja.estig.twdm.pdm1.myapplication.view.WaffleAdapter;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.CrepeViewModel;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.WaffleViewModel;

public class CrepePageActivity extends AppCompatActivity implements CrepeAdapter.CrepeAdapterEventListener {
    private CrepeViewModel viewModel;
    private CrepeAdapter crepeAdapter;
    ImageView imageViewGoHome;
    ImageView imageViewCart;
    Button buttonToppings3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crepe);

        // Initialize CrepeViewModel instance
        viewModel = new ViewModelProvider(this).get(CrepeViewModel.class);

        // Observe changes in the CrepeViewModel for all Crepe items
        viewModel.getAll().observe(this, crepeItems -> {
            // Create a new CrepeAdapter and refresh the list of Crepe items
            this.crepeAdapter = new CrepeAdapter(crepeItems, this);
            this.crepeAdapter.refreshList(crepeItems);
        });

        RecyclerView recyclerViewCrepe = findViewById(R.id.recyclerViewCrepeItem);

        // Set up RecyclerView with a LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCrepe.setAdapter(this.crepeAdapter);
        recyclerViewCrepe.setLayoutManager(layoutManager);

        // Observe changes in the CrepeViewModel for all Crepe items
        viewModel.getAll().observe(this, crepeItems -> {
            // Create a new CrepeAdapter and set it to the RecyclerView
            this.crepeAdapter = new CrepeAdapter(crepeItems, this);
            recyclerViewCrepe.setAdapter(this.crepeAdapter);
            recyclerViewCrepe.setLayoutManager(layoutManager);
        });

        // Set click listener for the "Go Home" button
        imageViewGoHome = findViewById(R.id.imageViewGoBack3);
        imageViewGoHome.setOnClickListener(view -> {
            Intent intent = new Intent(CrepePageActivity.this, MainPage.class);
            finish();
            startActivity(intent);
        });

        // Set click listener for the "Cart" button
        imageViewCart = findViewById(R.id.imageViewCart3);
        imageViewCart.setOnClickListener(view -> {
            Intent int2 = new Intent(CrepePageActivity.this, CartActivity.class);
            finish();
            startActivity(int2);
        });

        // Set click listener for the "Go to Toppings" button
        buttonToppings3 = findViewById(R.id.buttonGoToToppings3);
        buttonToppings3.setOnClickListener(view -> {
            Intent int4 = new Intent(CrepePageActivity.this, ToppingsPageActivity.class);
            finish();
            startActivity(int4);
        });
    }

    // Handle click events on Crepe items
    @Override
    public void onCrepeClicked(long crepeID) {
        CrepeItemActivity.startActivity(this, crepeID);
    }
}
