package com.example.android.fud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class NeedFoodActivity extends AppCompatActivity {

    ImageView addImage;
    ImageView newImage;
    LinearLayout layout;
    int SELECT_IMAGE_CODE = 1;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_food);

        // For Vibrations
        Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        layout = findViewById(R.id.addImageLayout);

        TextView quantity = findViewById(R.id.quantity);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                quantity.setText("Quantity: " + progress + " People");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        addImage = (ImageView) findViewById(R.id.addImage);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newImage = new ImageView(NeedFoodActivity.this);

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
            }
        });

        //Submit Button
        btnSubmit = findViewById(R.id.need_3);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(80);
                Intent intent = new Intent(NeedFoodActivity.this, SuccessfulSubmission.class);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Uri uri = data.getData();
            newImage.setImageURI(uri);
            addView(newImage,addImage.getWidth(),addImage.getHeight());
        }

    }


    private void addView(ImageView imageView, int width, int height) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

        // setting the margin in linearlayout
        params.setMargins(8,8,8,8);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(params);

        // adding the image in layout
        layout.addView(imageView);
    }

}