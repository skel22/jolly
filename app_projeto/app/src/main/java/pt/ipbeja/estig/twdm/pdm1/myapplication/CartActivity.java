package pt.ipbeja.estig.twdm.pdm1.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pt.ipbeja.estig.twdm.pdm1.myapplication.model.AppDatabase;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Cart;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.CartDao;
import pt.ipbeja.estig.twdm.pdm1.myapplication.view.CartAdapter;
import pt.ipbeja.estig.twdm.pdm1.myapplication.view.MainPage;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.CartViewModel;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnCartItemInteractionListener {
    private CartViewModel viewModel;
    private CartAdapter cartAdapter;
    ImageView imageViewGoHome;
    Button buttonGoToPayment;
    TextView textViewTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize CartViewModel
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);

        // Get RecyclerView reference
        RecyclerView recyclerViewCart = findViewById(R.id.recyclerViewCartItem);

        // Create LinearLayoutManager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // Observe changes in the Cart items and update the RecyclerView
        viewModel.getAll().observe(this, cartItems -> {
            this.cartAdapter = new CartAdapter(cartItems, this, CartActivity.this);
            recyclerViewCart.setAdapter(this.cartAdapter);
            recyclerViewCart.setLayoutManager(layoutManager);
        });

        // Set click listener for the "Go Home" button
        imageViewGoHome = findViewById(R.id.imageViewGoBack6);
        imageViewGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the MainPage
                Intent intent = new Intent(CartActivity.this, MainPage.class);
                startActivity(intent);
            }
        });

        // Set click listener for the "Go to Payment" button
        buttonGoToPayment = findViewById(R.id.buttonGoToPayment);
        buttonGoToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the PagamentoActivity
                Intent intent = new Intent(CartActivity.this, PagamentoActivity.class);
                startActivity(intent);
            }
        });

        // Get TextView reference for displaying total price
        textViewTotalPrice = findViewById(R.id.textViewShowTotal);

        // Update the total price display
        updateTotalPrice();
    }

    // Handle add, take, and delete button clicks in the CartAdapter
    @Override
    public void onAddAmountClicked(Cart cart) {
        updateTotalPrice();
    }

    @Override
    public void onTakeAmountClicked(Cart cart) {
        updateTotalPrice();
    }

    @Override
    public void onDeleteClicked(Cart cart) {
        updateTotalPrice();
    }

    // Update the total price display by querying the CartDao
    public void updateTotalPrice() {
        AppDatabase db = AppDatabase.getInstance(CartActivity.this);
        CartDao cartDao = db.getCartDao();

        // Calculate and display the total price
        double totalPrice = cartDao.getTotalPrice();
        textViewTotalPrice.setText(String.valueOf(totalPrice) + " €");
    }
}
