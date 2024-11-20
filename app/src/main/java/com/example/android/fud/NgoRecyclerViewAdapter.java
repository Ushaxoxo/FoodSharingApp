package com.example.android.fud;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class NgoRecyclerViewAdapter extends RecyclerView.Adapter<NgoRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private Cursor cursor;

    public NgoRecyclerViewAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.donation_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String dish = cursor.getString(cursor.getColumnIndexOrThrow("dish"));
            String quantity = cursor.getString(cursor.getColumnIndexOrThrow("quantity"));

            holder.donationName.setText(name);
            holder.donationDish.setText(dish);
            holder.donationQuantity.setText("Servings: " + quantity);
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void closeCursor() {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView donationName;
        public TextView donationDish;
        public TextView donationQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            donationName = itemView.findViewById(R.id.donationName);
            donationDish = itemView.findViewById(R.id.donationDish);
            donationQuantity = itemView.findViewById(R.id.donationQuantity);
        }
    }
}
