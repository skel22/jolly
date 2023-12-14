package pt.ipbeja.estig.twdm.pdm1.myapplication.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pt.ipbeja.estig.twdm.pdm1.myapplication.R;
import pt.ipbeja.estig.twdm.pdm1.myapplication.model.Crepe;
import pt.ipbeja.estig.twdm.pdm1.myapplication.viewmodel.CrepeViewModel;

// Adapter class for the RecyclerView displaying Crepe items
public class CrepeAdapter extends RecyclerView.Adapter<CrepeAdapter.CrepeViewHolder> {
    private CrepeViewModel viewModel;
    private List<Crepe> crepeItems;
    private CrepeAdapterEventListener crepeEventListener;

    // Constructor to initialize the adapter with Crepe items and an event listener
    public CrepeAdapter(List<Crepe> crepeList, CrepeAdapterEventListener crepeEventListener){
        this.crepeItems = crepeList;
        this.crepeEventListener = crepeEventListener;
    }

    // Create a new ViewHolder when needed
    @NonNull
    public CrepeViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int ViewType){
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_items, parent, false);
        return new CrepeViewHolder(rootView, parent.getContext());
    }

    // Bind data to the ViewHolder
    public void onBindViewHolder(@NonNull CrepeViewHolder holder, int position){
        final Crepe crepeItem = this.crepeItems.get(position);
        holder.textViewCrepeName.setText(crepeItem.getCrepeName());
        holder.textViewCrepePrice.setText(String.valueOf(crepeItem.getCrepePrice()) + " €");
        Glide.with(holder.rootView.getContext()).load(crepeItem.getCrepeImg()).into(holder.imageViewCrepeItem);

        // Set a click listener for the item to handle item click events
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crepeEventListener != null) crepeEventListener.onCrepeClicked(crepeItem.getCrepeId());
            }
        });
    }

    // Return the number of items in the adapter
    public int getItemCount(){
        return this.crepeItems.size();
    }

    // Refresh the list of Crepe items in the adapter
    public void refreshList(List<Crepe> crepes) {
        this.crepeItems = crepes;
        notifyDataSetChanged();
    }

    // ViewHolder class for the CrepeAdapter
    public class CrepeViewHolder extends RecyclerView.ViewHolder{
        private View rootView;
        private TextView textViewCrepeName;
        private TextView textViewCrepePrice;
        private ImageView imageViewCrepeItem;
        private Context context;

        // Constructor to initialize the ViewHolder
        public CrepeViewHolder(@NonNull View rootView, Context context){
            super(rootView);
            this.rootView = rootView;
            textViewCrepeName = itemView.findViewById(R.id.textViewItemName);
            textViewCrepePrice = itemView.findViewById(R.id.textViewItemPrice);
            imageViewCrepeItem = itemView.findViewById(R.id.imageViewItem);
            this.context = context;
        }
    }

    // Interface for handling interactions with Crepe items
    public interface CrepeAdapterEventListener{
        void onCrepeClicked(long crepeId);
    }
}
