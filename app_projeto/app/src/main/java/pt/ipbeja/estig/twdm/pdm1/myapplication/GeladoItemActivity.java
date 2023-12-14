package pt.ipbeja.estig.twdm.pdm1.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Cart;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.CartViewModel;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Gelado;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.GeladoViewModel;

public class GeladoItemActivity extends AppCompatActivity {
    // Key for passing data via Intent
    private static final String KEY_GELADO_ID = "gelId";
    private GeladoViewModel viewModel;
    private CartViewModel cartViewModel;
    private long geladoId;
    private ImageView imageViewGoBack;

    // Static method to start this activity with necessary data
    public static void startActivity(Context context, long geladoId) {
        Intent intent = new Intent(context, GeladoItemActivity.class);
        intent.putExtra(KEY_GELADO_ID, geladoId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        // Initialize ViewModel instances
        viewModel = new ViewModelProvider(this).get(GeladoViewModel.class);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        // Get data from the intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            // Extract geladoId from the intent extras
            geladoId = bundle.getLong(KEY_GELADO_ID, -1);

            // Observe changes in the GeladoViewModel for the specified geladoId
            viewModel.getGelById(geladoId).observe(this, gelado -> {
                if (gelado != null) {
                    // Update UI with Gelado data
                    updateUI(gelado);
                }
            });

            // Set click listener for the "Add to Cart" button
            Button buttonAddCart = findViewById(R.id.buttonAddCart);
            buttonAddCart.setOnClickListener(view -> {
                addToCart();
            });

            // Set click listener for the "Go Back" button
            imageViewGoBack = findViewById(R.id.imageViewGoBack5);
            imageViewGoBack.setOnClickListener(view -> {
                Intent goBackIntent = new Intent(GeladoItemActivity.this, GeladoPageActivity.class);
                startActivity(goBackIntent);
            });

        } else {
            // Finish the activity if no valid data is received
            finish();
        }
    }

    // Update UI with Gelado data
    private void updateUI(Gelado gelado) {
        ImageView imageViewGelImg = findViewById(R.id.imageViewItemImg);
        TextView textViewItemPrice = findViewById(R.id.textViewPrice);
        TextView textViewItemName = findViewById(R.id.textViewName);

        // Use Glide to load the Gelado image into the ImageView
        Glide.with(this).load(gelado.getGelImg()).into(imageViewGelImg);
        textViewItemName.setText(gelado.getGelName());
        textViewItemPrice.setText(String.valueOf(gelado.getGelPrice()) + " €");
    }

    // Add the selected Gelado item to the Cart
    private void addToCart() {
        // Observe changes in the GeladoViewModel for the specified geladoId
        LiveData<Gelado> geladoLiveData = viewModel.getGelById(geladoId);

        // Execute the code inside the observer when the LiveData changes
        geladoLiveData.observe(this, gelado -> {
            if (gelado != null) {
                // Create a Cart object with Gelado data and insert it using the CartViewModel
                String itemName = gelado.getGelName();
                double price = gelado.getGelPrice();
                int amount = 1;

                Cart cart = new Cart(0, itemName, price, amount);
                cartViewModel.insert(cart);

                // Log that the item is added to the cart
                Log.i("Click", "Added to cart");

                // Navigate to the GeladoPageActivity after adding to the cart
                Intent intent = new Intent(GeladoItemActivity.this, GeladoPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
