package com.example.android.fud;
import android.view.View; // Add this line

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NgoDashboardActivity extends AppCompatActivity {

    private RecyclerView donationsRecyclerView;
    private TextView noDonationsText;
    private DatabaseHelper dbHelper;
    private NgoRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_dashboard);

        dbHelper = new DatabaseHelper(this);
        donationsRecyclerView = findViewById(R.id.donationsRecyclerView);
        noDonationsText = findViewById(R.id.noDonationsText);

        donationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadDonations();
    }

    private void loadDonations() {
        Cursor cursor = dbHelper.getAllDonations(); // Assuming method for fetching NGO-specific donations
        if (cursor != null && cursor.getCount() > 0) {
            adapter = new NgoRecyclerViewAdapter(this, cursor);
            donationsRecyclerView.setAdapter(adapter);
            noDonationsText.setVisibility(View.GONE);
        } else {
            noDonationsText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();  // Ensure resources are released
        if (adapter != null) {
            adapter.closeCursor();  // Ensure Cursor resources are released
        }
    }
}
