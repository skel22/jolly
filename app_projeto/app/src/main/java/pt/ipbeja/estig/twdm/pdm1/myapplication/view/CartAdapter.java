package pt.ipbeja.estig.twdm.pdm1.myapplication.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pt.ipbeja.estig.twdm.pdm1.myapplication.R;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.AppDatabase;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Cart;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.CartDao;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.CartViewModel;

// Adapter class for the RecyclerView displaying Cart items
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private CartViewModel viewModel;
    private List<Cart> carts;
    private OnCartItemInteractionListener listener;

    // Constructor to initialize the adapter with Cart items and a listener for item interactions
    public CartAdapter(List<Cart> carts, OnCartItemInteractionListener listener, Context context) {
        viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(CartViewModel.class);
        this.carts = carts;
        this.listener = listener;
    }

    // Create a new ViewHolder when needed
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, parent, false);
        return new CartViewHolder(rootView, parent.getContext());
    }

    // Bind data to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        AppDatabase db = AppDatabase.getInstance(holder.context);
        CartDao cartDao = db.getCartDao();

        final Cart cart = this.carts.get(position);
        holder.textViewItemName.setText(cart.getItemName());
        holder.textViewItemPrice.setText(String.valueOf(cart.getPrice() * cart.getAmount()) + " €");
        holder.textViewItemAmount.setText(Integer.toString(cart.getAmount()));

        // Set click listeners for the buttons to update Cart items and notify the listener
        holder.buttonAddAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increase the amount of the Cart item
                int amount = cart.getAmount();
                amount++;
                cartDao.updateAmount(amount, cart.getItemId());
                cart.setAmount(amount);
                holder.textViewItemAmount.setText(Integer.toString(amount));

                // Notify the listener about the add amount action
                if (listener != null) {
                    listener.onAddAmountClicked(cart);
                }
            }
        });

        holder.buttonTakeAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Decrease the amount of the Cart item and handle deletion if amount is zero
                int amount = cart.getAmount();
                if (amount > 0) {
                    amount--;
                    cartDao.updateAmount(amount, cart.getItemId());
                    cart.setAmount(amount);
                    holder.textViewItemAmount.setText(Integer.toString(amount));

                    // Notify the listener about the take amount action
                    if (amount == 0) {
                        cartDao.delete(cart);
                        if (listener != null) {
                            listener.onDeleteClicked(cart);
                        }
                    } else {
                        if (listener != null) {
                            listener.onTakeAmountClicked(cart);
                        }
                    }
                }
                refreshList(holder.context);
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete the Cart item and notify the listener
                cartDao.delete(cart);
                if (listener != null) {
                    listener.onDeleteClicked(cart);
                }
                refreshList(holder.context);
            }
        });
    }

    // Return the number of items in the adapter
    @Override
    public int getItemCount() {
        return this.carts.size();
    }

    // Refresh the list of Cart items from the ViewModel
    public void refreshList(Context context) {
        viewModel.getAll().observe((LifecycleOwner) context, cartItems -> {
            this.carts = cartItems;
        });
    }

    // ViewHolder class for the CartAdapter
    public class CartViewHolder extends RecyclerView.ViewHolder {
        private View rootView;
        private TextView textViewItemName;
        private TextView textViewItemPrice;
        private TextView textViewTotalPrice;
        private Button buttonAddAmount;
        private Button buttonTakeAmount;
        private Button buttonDelete;
        private TextView textViewItemAmount;
        private Context context;

        // Constructor to initialize the ViewHolder
        public CartViewHolder(@NonNull View rootView, Context context) {
            super(rootView);
            this.rootView = rootView;
            textViewItemName = itemView.findViewById(R.id.textViewCartName);
            textViewItemPrice = itemView.findViewById(R.id.textViewCartPrice);
            textViewTotalPrice = itemView.findViewById(R.id.textViewShowTotal);
            buttonAddAmount = itemView.findViewById(R.id.buttonAddAmount);
            buttonTakeAmount = itemView.findViewById(R.id.buttonTakeAmount);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            textViewItemAmount = itemView.findViewById(R.id.textViewItemAmount);
            this.context = context;
        }
    }

    // Interface for handling interactions with Cart items
    public interface OnCartItemInteractionListener {
        void onAddAmountClicked(Cart cart);
        void onTakeAmountClicked(Cart cart);
        void onDeleteClicked(Cart cart);
    }
}
