package com.example.serandb;

import androidx.annotation.NonNull;
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

public class EarlsRegencyHotel extends AppCompatActivity {
    RatingBar ratingBar;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earls_regency_hotel);

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



        TextView textView=(TextView)findViewById(R.id.textview);

        String text = "Earls Regency.com  Hotels.com  agoda.com";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick( View widget) {
                Toast.makeText(EarlsRegencyHotel.this, "One", Toast.LENGTH_SHORT).show();

            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick( View widget) {
                Toast.makeText(EarlsRegencyHotel.this, "Two", Toast.LENGTH_SHORT).show();

            }
        };
        ClickableSpan clickableSpan3 = new ClickableSpan() {
            @Override
            public void onClick( View widget) {
                Toast.makeText(EarlsRegencyHotel.this, "Three", Toast.LENGTH_SHORT).show();

            }
        };

        ss.setSpan(clickableSpan1,0,17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan2,19,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan3,32,40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());







    }
}