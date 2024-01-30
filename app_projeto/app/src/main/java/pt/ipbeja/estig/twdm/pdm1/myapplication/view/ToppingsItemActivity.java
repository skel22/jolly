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
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Toppings;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.ToppingsViewModel;

public class ToppingsItemActivity extends AppCompatActivity {
    private static final String KEY_TOPPING_ID = "toppingId";
    private ToppingsViewModel viewModel;
    private CartViewModel cartViewModel;
    private long toppingId;

    // Static method to start this activity with necessary data
    public static void startActivity(Context context, long toppingId) {
        Intent intent = new Intent(context, ToppingsItemActivity.class);
        intent.putExtra(KEY_TOPPING_ID, toppingId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        // Initialize ViewModel instances
        viewModel = new ViewModelProvider(this).get(ToppingsViewModel.class);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        // Get data from the intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            // Extract toppingId from the intent extras
            toppingId = bundle.getLong(KEY_TOPPING_ID, -1);

            // Observe changes in the ToppingsViewModel for the specified toppingId
            viewModel.getTopById(toppingId).observe(this, topping -> {
                if (topping != null) {
                    // Update UI with Toppings data
                    updateUI(topping);
                }
            });

            // Set click listener for the "Add to Cart" button
            Button buttonAddToCart = findViewById(R.id.buttonAddCart);
            buttonAddToCart.setOnClickListener(view -> {
                addToCart();
            });

        } else {
            // Finish the activity if no valid data is received
            finish();
        }
    }

    // Update UI with Toppings data
    private void updateUI(Toppings topping) {
        ImageView imageViewItemImg = findViewById(R.id.imageViewItemImg);
        TextView textViewItemPrice = findViewById(R.id.textViewPrice);
        TextView textViewItemName = findViewById(R.id.textViewName);

        // Use Glide to load the Toppings image into the ImageView
        Glide.with(this).load(topping.getTopImg()).into(imageViewItemImg);
        textViewItemName.setText(topping.getTopName());
        textViewItemPrice.setText(String.valueOf(topping.getTopPrice()) + " €");
    }

    // Add the selected Toppings item to the Cart
    private void addToCart() {
        // Observe changes in the ToppingsViewModel for the specified toppingId
        LiveData<Toppings> toppingLiveData = viewModel.getTopById(toppingId);

        // Execute the code inside the observer when the LiveData changes
        toppingLiveData.observe(this, topping -> {
            if (topping != null) {
                // Create a Cart object with Toppings data and insert it using the CartViewModel
                String itemName = topping.getTopName();
                double price = topping.getTopPrice();
                int amount = 1;

                Cart cart = new Cart(0, itemName, price, amount);
                cartViewModel.insert(cart);

                // Log that the item is added to the cart
                Log.i("Click", "Added to cart");

                // Navigate to the ToppingsPageActivity after adding to the cart
                Intent intent = new Intent(ToppingsItemActivity.this, ToppingsPageActivity.class);
                startActivity(intent);
            }
        });
    }
}
