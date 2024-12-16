package com.example.welcomeprojectapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.welcomeprojectapp.R;
import com.example.welcomeprojectapp.models.Item;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Context context;
    private List<Item> items;

    public DataAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.typeTextView.setText(item.getType());
        holder.descriptionTextView.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView typeTextView, descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
