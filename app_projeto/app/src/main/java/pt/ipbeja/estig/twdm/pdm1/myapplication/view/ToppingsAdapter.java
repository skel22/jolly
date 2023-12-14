package pt.ipbeja.estig.twdm.pdm1.myapplication.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pt.ipbeja.estig.twdm.pdm1.myapplication.R;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Gelado;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Toppings;

// Adapter class for the RecyclerView displaying Toppings items
public class ToppingsAdapter extends RecyclerView.Adapter<ToppingsAdapter.ToppingsViewHolder> {
    private List<Toppings> toppingItems;
    private ToppingsAdapter.ToppingsAdapterEventListener toppingsEventListener;

    // Constructor to initialize the adapter with Toppings items and an event listener
    public ToppingsAdapter(List<Toppings> toppingsList, ToppingsAdapterEventListener toppingsEventListener){
        this.toppingItems = toppingsList;
        this.toppingsEventListener = toppingsEventListener;
    }

    // Create a new ViewHolder when needed
    @NonNull
    public ToppingsAdapter.ToppingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_items, parent, false);
        return new ToppingsAdapter.ToppingsViewHolder(rootView);
    }

    // Bind data to the ViewHolder
    public void onBindViewHolder(@NonNull ToppingsAdapter.ToppingsViewHolder holder, int position){
        final Toppings toppingsItem = this.toppingItems.get(position);
        holder.textViewTopName.setText(toppingsItem.getTopName());
        holder.textViewTopPrice.setText(String.valueOf(toppingsItem.getTopPrice()) + " €");
        Glide.with(holder.rootView.getContext()).load(toppingsItem.getTopImg()).into(holder.imageViewTopItem);

        // Set a click listener for the item to handle item click events
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toppingsEventListener != null) toppingsEventListener.onToppingsClicked(toppingsItem.getTopId());
            }
        });
    }

    // Return the number of items in the adapter
    public int getItemCount(){
        return this.toppingItems.size();
    }

    // Refresh the list of Toppings items in the adapter
    public void refreshList(List<Toppings> toppings){
        this.toppingItems = toppings;
        notifyDataSetChanged();
    }

    // ViewHolder class for the ToppingsAdapter
    public class ToppingsViewHolder extends RecyclerView.ViewHolder{
        private View rootView;
        private TextView textViewTopName;
        private TextView textViewTopPrice;
        private ImageView imageViewTopItem;

        // Constructor to initialize the ViewHolder
        public ToppingsViewHolder(@NonNull View rootView){
            super(rootView);
            this.rootView = rootView;
            this.textViewTopName = rootView.findViewById(R.id.textViewItemName);
            this.textViewTopPrice = rootView.findViewById(R.id.textViewItemPrice);
            this.imageViewTopItem = rootView.findViewById(R.id.imageViewItem);
        }
    }

    // Interface for handling interactions with Toppings items
    public interface ToppingsAdapterEventListener{
        void onToppingsClicked(long topId);
    }
}
