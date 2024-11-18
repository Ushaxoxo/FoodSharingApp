package com.example.android.fud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder> {

    private List<Donation> donationList;

    public DonationAdapter(List<Donation> donationList) {
        this.donationList = donationList;
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donation, parent, false);
        return new DonationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {
        Donation donation = donationList.get(position);
        holder.nameTextView.setText(donation.getName());
        holder.dishTextView.setText(donation.getDish());
        holder.quantityTextView.setText("Quantity: " + donation.getQuantity() + " People");
    }


    @Override
    public int getItemCount() {
        return donationList.size();
    }

    public static class DonationViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, dishTextView, quantityTextView;

        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.donationName);
            dishTextView = itemView.findViewById(R.id.donationDish);
            quantityTextView = itemView.findViewById(R.id.donationQuantity);
        }
    }
}
