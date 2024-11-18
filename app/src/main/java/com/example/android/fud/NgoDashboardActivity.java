package com.example.android.fud;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class NgoDashboardActivity extends AppCompatActivity {

    private ListView donationsListView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_dashboard);

        dbHelper = new DatabaseHelper(this);
        donationsListView = findViewById(R.id.donationsListView);

        loadDonations();
    }

    private void loadDonations() {
        Cursor cursor = dbHelper.getAllDonations();
        String[] from = new String[]{"name", "dish", "date"};
        int[] to = new int[]{R.id.nameTextView, R.id.dishTextView, R.id.dateTextView};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.donation_list_item,
                cursor,
                from,
                to,
                0
        );

        donationsListView.setAdapter(adapter);
    }
}
