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
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Toppings;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Waffle;

// Adapter class for the RecyclerView displaying Waffle items
public class WaffleAdapter extends RecyclerView.Adapter<WaffleAdapter.WaffleViewHolder> {
    private List<Waffle> waffleItems;
    private WaffleAdapter.WaffleAdapterEventListener waffleEventListener;

    // Constructor to initialize the adapter with Waffle items and an event listener
    public WaffleAdapter(List<Waffle> waffleList, WaffleAdapterEventListener waffleEventListener){
        this.waffleItems = waffleList;
        this.waffleEventListener = waffleEventListener;
    }

    // Create a new ViewHolder when needed
    @NonNull
    @Override
    public WaffleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_items, parent, false);
        return new WaffleViewHolder(rootView);
    }

    // Bind data to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull WaffleViewHolder holder, int position){
        final Waffle waffleItem = this.waffleItems.get(position);
        holder.textViewWaffleName.setText(waffleItem.getWaffleName());
        holder.textViewWafflePrice.setText(String.valueOf(waffleItem.getWafflePrice()) + " €");
        Glide.with(holder.rootView.getContext()).load(waffleItem.getWaffleImg()).into(holder.imageViewWaffleItem);

        // Set a click listener for the item to handle item click events
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(waffleEventListener != null) waffleEventListener.onWaffleClicked(waffleItem.getWaffleId());
            }
        });
    }

    // Return the number of items in the adapter
    @Override
    public int getItemCount(){
        return this.waffleItems.size();
    }

    // Refresh the list of Waffle items in the adapter
    public void refreshList(List<Waffle> waffles){
        this.waffleItems = waffles;
        notifyDataSetChanged();
    }

    // ViewHolder class for the WaffleAdapter
    public class WaffleViewHolder extends RecyclerView.ViewHolder{
        private View rootView;
        private TextView textViewWaffleName;
        private TextView textViewWafflePrice;
        private ImageView imageViewWaffleItem;

        // Constructor to initialize the ViewHolder
        public WaffleViewHolder(@NonNull View rootView){
            super(rootView);
            this.rootView = rootView;
            this.textViewWaffleName = rootView.findViewById(R.id.textViewItemName);
            this.textViewWafflePrice = rootView.findViewById(R.id.textViewItemPrice);
            this.imageViewWaffleItem = rootView.findViewById(R.id.imageViewItem);
        }
    }

    // Interface for handling interactions with Waffle items
    public interface WaffleAdapterEventListener{
        void onWaffleClicked(long waffleId);
    }
}
