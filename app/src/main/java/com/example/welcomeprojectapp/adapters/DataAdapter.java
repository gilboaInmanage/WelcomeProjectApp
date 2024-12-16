package com.example.welcomeprojectapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.welcomeprojectapp.R;
import com.example.welcomeprojectapp.models.Item;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private final List<Item> items;

    public DataAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Item item = items.get(position);
        holder.itemTitle.setText(item.getTitle());
        Glide.with(holder.itemImage.getContext())
                .load(item.getImageUrl())

                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        ImageView itemImage;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemImage = itemView.findViewById(R.id.itemImage);
        }
    }
}

