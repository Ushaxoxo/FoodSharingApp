package com.example.android.fud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class NgoAgentActivity extends AppCompatActivity {

    ImageView addImage;
    ImageView newImage;
    LinearLayout layout;
    int SELECT_IMAGE_CODE = 1;
    Uri newImageUri; // To store image URI
    Button btnSubmit;
    DatabaseHelper dbHelper;

    private EditText locationField, dishField, timeField, dateField;
    private SeekBar quantitySeekBar;
    private TextView quantityText;
    private CheckBox assuranceCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_agent);

        dbHelper = new DatabaseHelper(this); // Initialize Database Helper

        // Initialize Views
        locationField = findViewById(R.id.locationField);
        dishField = findViewById(R.id.dishField);
        timeField = findViewById(R.id.timeField);
        dateField = findViewById(R.id.dateField);
        quantitySeekBar = findViewById(R.id.seekBar);
        quantityText = findViewById(R.id.quantity);
        assuranceCheckbox = findViewById(R.id.assuranceCheckbox);
        addImage = findViewById(R.id.addImage);
        btnSubmit = findViewById(R.id.submit_btn);
        layout = findViewById(R.id.addImageLayout);

        // Vibrations
        Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        // Handle SeekBar
        quantitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                quantityText.setText("Quantity: " + progress + " People");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Image Selector
        addImage.setOnClickListener(v -> {
            newImage = new ImageView(NgoAgentActivity.this);
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_IMAGE_CODE);
        });

        // Submit Button Logic
        btnSubmit.setOnClickListener(v -> {
            vibe.vibrate(80);

            // Validate form
            if (!assuranceCheckbox.isChecked()) {
                Toast.makeText(NgoAgentActivity.this, "Please confirm food quality assurance", Toast.LENGTH_SHORT).show();
                return;
            }

            // Collect data
            String location = locationField.getText().toString();
            String dish = dishField.getText().toString();
            String time = timeField.getText().toString();
            String date = dateField.getText().toString();
            int quantity = quantitySeekBar.getProgress();
            String imageUri = (newImageUri != null) ? newImageUri.toString() : "";

            // Insert into database
            boolean result = dbHelper.insertDonation("NGO Agent", "N/A", dish, time, date, quantity, true, imageUri);

            if (result) {
                Toast.makeText(NgoAgentActivity.this, "Submission Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NgoAgentActivity.this, SuccessfulSubmission.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(NgoAgentActivity.this, "Submission Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_IMAGE_CODE && resultCode == RESULT_OK && data != null) {
            newImageUri = data.getData();
            newImage.setImageURI(newImageUri);
            addView(newImage, addImage.getWidth(), addImage.getHeight());
        }
    }

    private void addView(ImageView imageView, int width, int height) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        params.setMargins(8, 8, 8, 8);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(params);
        layout.addView(imageView);
    }
}
