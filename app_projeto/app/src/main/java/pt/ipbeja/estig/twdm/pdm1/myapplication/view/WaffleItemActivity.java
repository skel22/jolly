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
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Waffle;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.WaffleViewModel;

public class WaffleItemActivity extends AppCompatActivity {
    private static final String KEY_WAFFLE_ID = "waffleId";
    private WaffleViewModel viewModel;
    private CartViewModel cartViewModel;
    private long waffleId;

    // Static method to start this activity with necessary data
    public static void startActivity(Context context, long waffleId) {
        Intent intent = new Intent(context, WaffleItemActivity.class);
        intent.putExtra(KEY_WAFFLE_ID, waffleId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details); // Assuming the layout is the same as in CrepeItemActivity

        // Initialize ViewModel instances
        viewModel = new ViewModelProvider(this).get(WaffleViewModel.class);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        // Get data from the intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            // Extract waffleId from the intent extras
            waffleId = bundle.getLong(KEY_WAFFLE_ID, -1);

            // Observe changes in the WaffleViewModel for the specified waffleId
            viewModel.getWaffleById(waffleId).observe(this, waffle -> {
                if (waffle != null) {
                    // Update UI with Waffle data
                    updateUI(waffle);
                }
            });

            // Set click listener for the "Add to Cart" button
            Button buttonAddToCart = findViewById(R.id.buttonAddCart); // Adjust the button ID as per your layout
            buttonAddToCart.setOnClickListener(view -> {
                addToCart();
            });

        } else {
            // Finish the activity if no valid data is received
            finish();
        }
    }

    // Update UI with Waffle data
    private void updateUI(Waffle waffle) {
        ImageView imageViewItemImg = findViewById(R.id.imageViewItemImg); // Adjust the ImageView ID
        TextView textViewItemPrice = findViewById(R.id.textViewPrice); // Adjust the TextView IDs
        TextView textViewItemName = findViewById(R.id.textViewName);

        // Use Glide to load the Waffle image into the ImageView
        Glide.with(this).load(waffle.getWaffleImg()).into(imageViewItemImg);
        textViewItemName.setText(waffle.getWaffleName());
        textViewItemPrice.setText(String.valueOf(waffle.getWafflePrice()));
    }

    // Add the selected Waffle item to the Cart
    private void addToCart() {
        // Observe changes in the WaffleViewModel for the specified waffleId
        LiveData<Waffle> waffleLiveData = viewModel.getWaffleById(waffleId);

        // Execute the code inside the observer when the LiveData changes
        waffleLiveData.observe(this, waffle -> {
            if (waffle != null) {
                // Create a Cart object with Waffle data and insert it using the CartViewModel
                String itemName = waffle.getWaffleName();
                double price = waffle.getWafflePrice();
                int amount = 1;

                Cart cart = new Cart(0, itemName, price, amount);
                cartViewModel.insert(cart);

                // Log that the item is added to the cart
                Log.i("Click", "Added to cart");

                // Navigate to the WafflePageActivity after adding to the cart
                Intent intent = new Intent(WaffleItemActivity.this, WafflePageActivity.class);
                startActivity(intent);
            }
        });
    }
}