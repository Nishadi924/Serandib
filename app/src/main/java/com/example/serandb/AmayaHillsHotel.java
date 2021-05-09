package com.example.serandb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class AmayaHillsHotel extends AppCompatActivity {
    RatingBar ratingBar;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amaya_hills_hotel);

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