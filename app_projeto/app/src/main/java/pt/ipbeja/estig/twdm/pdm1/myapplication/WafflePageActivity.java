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

import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Waffle;
import pt.ipbeja.estig.twdm.pdm1.myapplication.view.MainPage;
import pt.ipbeja.estig.twdm.pdm1.myapplication.view.WaffleAdapter;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.WaffleViewModel;

public class WafflePageActivity extends AppCompatActivity implements WaffleAdapter.WaffleAdapterEventListener {
    private WaffleViewModel viewModel;
    private WaffleAdapter waffleAdapter;
    ImageView imageViewGoHome;
    ImageView imageViewCart;
    Button buttonToppings2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waffle);

        // Initialize ViewModel instance
        viewModel = new ViewModelProvider(this).get(WaffleViewModel.class);

        // Observe changes in the WaffleViewModel for all Waffle items
        viewModel.getAll().observe(this, waffleItems -> {
            // Create a new WaffleAdapter and set it as the adapter for the RecyclerView
            this.waffleAdapter = new WaffleAdapter(waffleItems, this);
            RecyclerView recyclerViewWaffle = findViewById(R.id.recyclerViewWaffleItem);
            recyclerViewWaffle.setAdapter(this.waffleAdapter);

            // Set the layout manager for the RecyclerView
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerViewWaffle.setLayoutManager(layoutManager);

            // Refresh the list in the adapter
            this.waffleAdapter.refreshList(waffleItems);
        });

        // Set click listeners for the go home, cart, and toppings buttons
        setButtonClickListeners();
    }

    // Handle Waffle item click events
    @Override
    public void onWaffleClicked(long waffleId) {
        // Start the WaffleItemActivity with the selected Waffle item
        WaffleItemActivity.startActivity(this, waffleId);
    }

    // Set click listeners for the go home, cart, and toppings buttons
    private void setButtonClickListeners() {
        imageViewGoHome = findViewById(R.id.imageViewGoBack2);
        imageViewGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the MainPage activity
                Intent intent = new Intent(WafflePageActivity.this, MainPage.class);
                finish(); // Finish the current activity
                startActivity(intent);
            }
        });

        imageViewCart = findViewById(R.id.imageViewCart2);
        imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the CartActivity
                Intent intent = new Intent(WafflePageActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        buttonToppings2 = findViewById(R.id.buttonGoToToppings2);
        buttonToppings2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the ToppingsPageActivity
                Intent intent = new Intent(WafflePageActivity.this, ToppingsPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
