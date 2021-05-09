package com.example.serandb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class TheGrandKandyanHotel extends AppCompatActivity {
    RatingBar ratingBar;
    Button btSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_grand_kandyan_hotel);
        ratingBar = findViewById(R.id.rating_bar);
        btSubmit = findViewById(R.id.bt_submit);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(), s+"Star"
                        ,Toast.LENGTH_SHORT).show();

            }
        });
    }
}