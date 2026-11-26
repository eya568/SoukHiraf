package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// We extend RecyclerView.Adapter and specify that it uses our custom ViewHolder
public class ArtisanAdapter extends RecyclerView.Adapter<ArtisanAdapter.ArtisanViewHolder> {

    private List<Artisan> artisanList;

    // Constructor: We pass the list of data when we create the adapter
    public ArtisanAdapter(List<Artisan> artisanList) {
        this.artisanList = artisanList;
    }

    // 1. onCreateViewHolder: This creates the empty "card" layout from your XML
    @NonNull
    @Override
    public ArtisanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // We "inflate" (build) the item_artisan_card.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artisan_card, parent, false);
        return new ArtisanViewHolder(view);
    }

    // 2. onBindViewHolder: This takes the data and puts it inside the empty card
    @Override
    public void onBindViewHolder(@NonNull ArtisanViewHolder holder, int position) {
        // Get the specific artisan for this row (position)
        Artisan currentArtisan = artisanList.get(position);

        // Set the data into the TextViews and ImageView
        holder.tvName.setText(currentArtisan.getName());
        holder.tvCraft.setText(currentArtisan.getCraft());
        holder.tvPrice.setText(currentArtisan.getPrice());
        holder.ivImage.setImageResource(currentArtisan.getImageResId());
    }

    // 3. getItemCount: Tells the RecyclerView how many items are in the list
    @Override
    public int getItemCount() {
        return artisanList.size();
    }

    // This is the ViewHolder class.
    // It holds the references to the views inside your XML so you don't have to find them every time you scroll.
    public static class ArtisanViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCraft, tvPrice;
        ImageView ivImage;

        public ArtisanViewHolder(@NonNull View itemView) {
            super(itemView);
            // Connect the Java variables to the XML IDs
            tvName = itemView.findViewById(R.id.tvArtisanName);
            tvCraft = itemView.findViewById(R.id.tvArtisanCraft);
            tvPrice = itemView.findViewById(R.id.tvArtisanPrice);
            ivImage = itemView.findViewById(R.id.ivArtisanImage);
        }
    }
}