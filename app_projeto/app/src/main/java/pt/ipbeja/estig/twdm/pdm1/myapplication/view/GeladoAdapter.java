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
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.GeladoViewModel;

// Adapter class for the RecyclerView displaying Gelado items
public class GeladoAdapter extends RecyclerView.Adapter<GeladoAdapter.GeladoViewHolder> {
    private GeladoViewModel viewModel;
    private List<Gelado> geladoItems;
    private GeladoAdapterEventListener geladoEventListener;

    // Constructor to initialize the adapter with Gelado items and an event listener
    public GeladoAdapter(List<Gelado> geladoList, GeladoAdapterEventListener geladoEventListener){
        this.geladoItems = geladoList;
        this.geladoEventListener = geladoEventListener;
    }

    // Create a new ViewHolder when needed
    @NonNull
    public GeladoViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int ViewType){
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_items, parent, false);
        return new GeladoViewHolder(rootView);
    }

    // Bind data to the ViewHolder
    public void onBindViewHolder(@NonNull GeladoViewHolder holder, int position){
        final Gelado geladoItem = this.geladoItems.get(position);
        holder.textViewIceName.setText(geladoItem.getGelName());
        holder.textViewIcePrice.setText(String.valueOf(geladoItem.getGelPrice()) + " €");
        Glide.with(holder.rootView.getContext()).load(geladoItem.getGelImg()).into(holder.imageViewIceItem);

        // Set a click listener for the item to handle item click events
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(geladoEventListener != null) geladoEventListener.onGeladoClicked(geladoItem.getGelId());
            }
        });
    }

    // Return the number of items in the adapter
    public int getItemCount(){
        return this.geladoItems.size();
    }

    // Refresh the list of Gelado items in the adapter
    public void refreshList(List<Gelado> gelados){
        this.geladoItems = gelados;
        notifyDataSetChanged();
    }

    // ViewHolder class for the GeladoAdapter
    public class GeladoViewHolder extends RecyclerView.ViewHolder{
        private View rootView;
        private TextView textViewIceName;
        private TextView textViewIcePrice;
        private ImageView imageViewIceItem;

        // Constructor to initialize the ViewHolder
        public GeladoViewHolder(@NonNull View rootView){
            super(rootView);
            this.rootView = rootView;
            this.textViewIceName = rootView.findViewById(R.id.textViewItemName);
            this.textViewIcePrice = rootView.findViewById(R.id.textViewItemPrice);
            this.imageViewIceItem = rootView.findViewById(R.id.imageViewItem);
        }
    }

    // Interface for handling interactions with Gelado items
    public interface GeladoAdapterEventListener{
        void onGeladoClicked(long gelId);
    }
}
