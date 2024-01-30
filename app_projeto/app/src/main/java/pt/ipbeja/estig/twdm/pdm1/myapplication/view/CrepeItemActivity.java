package pt.ipbeja.estig.twdm.pdm1.myapplication.view;

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

import pt.ipbeja.estig.twdm.pdm1.myapplication.R;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Cart;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.CartViewModel;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Crepe;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.CrepeViewModel;

public class CrepeItemActivity extends AppCompatActivity {
    // Key for passing data via Intent
    private static final String KEY_CREPE_ID = "crepeId";

    // ViewModel instances
    private CrepeViewModel viewModel;
    private CartViewModel cartViewModel;

    // Variable to store the crepeId
    private long crepeId;

    // Static method to start this activity with necessary data
    public static void startActivity(Context context, long crepeId) {
        Intent intent = new Intent(context, CrepeItemActivity.class);
        intent.putExtra(CrepeItemActivity.KEY_CREPE_ID, crepeId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        // Initialize ViewModel instances
        viewModel = new ViewModelProvider(this).get(CrepeViewModel.class);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        // Get data from the intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            // Extract crepeId from the intent extras
            crepeId = bundle.getLong(KEY_CREPE_ID, -1);

            // Observe changes in the CrepeViewModel for the specified crepeId
            viewModel.getCrepeById(crepeId).observe(this, crepe -> {
                if (crepe != null) {
                    // Update UI with Crepe data
                    updateUI(crepe);
                }
            });

            // Set click listener for the "Add to Cart" button
            Button buttonAddCart = findViewById(R.id.buttonAddCart);
            buttonAddCart.setOnClickListener(view -> {
                // Add the selected Crepe item to the Cart
                addToCart();
            });

        } else {
            // Finish the activity if no valid data is received
            finish();
        }
    }

    // Update UI with Crepe data
    private void updateUI(Crepe crepe) {
        ImageView imageViewItemImg = findViewById(R.id.imageViewItemImg);
        TextView textViewItemPrice = findViewById(R.id.textViewPrice);
        TextView textViewItemName = findViewById(R.id.textViewName);

        // Use Glide to load the Crepe image into the ImageView
        Glide.with(this).load(crepe.getCrepeImg()).into(imageViewItemImg);
        textViewItemName.setText(crepe.getCrepeName());
        textViewItemPrice.setText(String.valueOf(crepe.getCrepePrice()) + " €");
    }

    // Add the selected Crepe item to the Cart
    private void addToCart() {
        // Observe changes in the CrepeViewModel for the specified crepeId
        LiveData<Crepe> crepeLiveData = viewModel.getCrepeById(crepeId);

        // Execute the code inside the observer when the LiveData changes
        crepeLiveData.observe(this, crepe -> {
            if (crepe != null) {
                // Create a Cart object with Crepe data and insert it using the CartViewModel
                String itemName = crepe.getCrepeName();
                double price = crepe.getCrepePrice();
                int amount = 1;

                Cart cart = new Cart(0, itemName, price, amount);
                cartViewModel.insert(cart);

                // Log that the item is added to the cart
                Log.i("Click", "Added to cart");

                // Navigate to the CrepePageActivity after adding to the cart
                Intent intent = new Intent(CrepeItemActivity.this, CrepePageActivity.class);
                startActivity(intent);
            }
        });
    }
}
