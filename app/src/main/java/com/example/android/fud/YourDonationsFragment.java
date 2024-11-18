package com.example.android.fud;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class YourDonationsFragment extends Fragment {

    private RecyclerView donationsRecyclerView;
    private TextView noDonationsText;
    private DonationAdapter donationAdapter;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_your_donations, container, false);

        donationsRecyclerView = root.findViewById(R.id.donationsRecyclerView);
        noDonationsText = root.findViewById(R.id.noDonationsText);

        dbHelper = new DatabaseHelper(getContext());
        loadDonations();

        return root;
    }

    private void loadDonations() {
        List<Donation> donationList = new ArrayList<>();
        Cursor cursor = dbHelper.getAllDonations();

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String dish = cursor.getString(cursor.getColumnIndex("dish"));
                int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));

                donationList.add(new Donation(name, dish, quantity));
            } while (cursor.moveToNext());
        }

        cursor.close();

        if (donationList.isEmpty()) {
            noDonationsText.setVisibility(View.VISIBLE);
            donationsRecyclerView.setVisibility(View.GONE);
        } else {
            noDonationsText.setVisibility(View.GONE);
            donationsRecyclerView.setVisibility(View.VISIBLE);

            donationAdapter = new DonationAdapter(donationList);
            donationsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            donationsRecyclerView.setAdapter(donationAdapter);
        }
    }
}
