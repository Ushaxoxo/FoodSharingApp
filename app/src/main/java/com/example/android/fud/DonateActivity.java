package com.example.android.fud;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DonateActivity extends AppCompatActivity {

    private EditText nameField, aadhaarField, dishField, timeField, dateField;
    private SeekBar quantitySeekBar;
    private TextView quantityText;
    private CheckBox assuranceCheckbox;
    private ImageView addImage;
    private Uri newImageUri;
    private Button btnSubmit;
    private LinearLayout layout;

    private DatabaseHelper dbHelper;

    private static final int SELECT_IMAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        // Initialize fields
        nameField = findViewById(R.id.nameField);
        aadhaarField = findViewById(R.id.aadhaarField);
        dishField = findViewById(R.id.foodField);
        timeField = findViewById(R.id.timeField);
        dateField = findViewById(R.id.dateField);
        quantitySeekBar = findViewById(R.id.seekBar);
        quantityText = findViewById(R.id.quantity);
        assuranceCheckbox = findViewById(R.id.assuranceCheckbox);
        addImage = findViewById(R.id.addImage);
        btnSubmit = findViewById(R.id.submit_btn1);
        layout = findViewById(R.id.addImageLayout);

        dbHelper = new DatabaseHelper(this);

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

        addImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_IMAGE_CODE);
        });

        btnSubmit.setOnClickListener(v -> {
            Vibrator vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            if (vibe != null) vibe.vibrate(80);

            if (!assuranceCheckbox.isChecked()) {
                Toast.makeText(DonateActivity.this, "Please confirm food quality assurance", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = nameField.getText().toString();
            String aadhaar = aadhaarField.getText().toString();
            String dish = dishField.getText().toString();
            String time = timeField.getText().toString();
            String date = dateField.getText().toString();
            int quantity = quantitySeekBar.getProgress();
            boolean assurance = assuranceCheckbox.isChecked();
            String imageUri = (newImageUri != null) ? newImageUri.toString() : "";

            boolean result = dbHelper.insertDonation(name, aadhaar, dish, time, date, quantity, assurance, imageUri);

            if (result) {
                Toast.makeText(DonateActivity.this, "Donation Submitted!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DonateActivity.this, SuccessfulSubmission.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(DonateActivity.this, "Submission failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE_CODE && resultCode == RESULT_OK && data != null) {
            newImageUri = data.getData();
            ImageView newImage = new ImageView(this);
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
