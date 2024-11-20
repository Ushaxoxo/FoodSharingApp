package com.example.android.fud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ngoDashboardAdapter extends RecyclerView.Adapter<ngoDashboardAdapter.DonationViewHolder> {

    private Context context;
    private List<Donation> donationList;

    public ngoDashboardAdapter(Context context, List<Donation> donationList) {
        this.context = context;
        this.donationList = donationList;
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.donation_list_item, parent, false);
        return new DonationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {
        Donation donation = donationList.get(position);
        holder.donationName.setText(donation.getName());
        holder.donationDish.setText(donation.getDish());
        holder.donationQuantity.setText("Quantity: " + donation.getQuantity() + " People");

        // Update the icons or load image if needed
    }

    @Override
    public int getItemCount() {
        return donationList.size();
    }

    static class DonationViewHolder extends RecyclerView.ViewHolder {
        TextView donationName, donationDish, donationQuantity;

        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);
            donationName = itemView.findViewById(R.id.donationName);
            donationDish = itemView.findViewById(R.id.donationDish);
            donationQuantity = itemView.findViewById(R.id.donationQuantity);
        }
    }
}
