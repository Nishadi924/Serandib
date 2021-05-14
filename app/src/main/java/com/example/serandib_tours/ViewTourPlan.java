package com.example.serandib_tours;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewTourPlan extends AppCompatActivity {
    EditText destination,addOntherPlace,accommodation,noOfDays,date;
    Button saveChangesButton, deleteButton;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tour_plan);

        destination = findViewById(R.id.editTextDestination);
        addOntherPlace = findViewById(R.id.editTextAddOtherPlace);
        accommodation = findViewById(R.id.editTextAccomodation);
        noOfDays = findViewById(R.id.editTextNoOfDays);
        date = findViewById(R.id.editTextDate);

        saveChangesButton = findViewById(R.id.saveChangesButton);
        deleteButton = findViewById(R.id.deleteButton);


        Intent intent = getIntent();
        TourPlan tourPlan = intent.getParcelableExtra("plan");

        Toast.makeText(this,"selected" + tourPlan.getNoOfDays(),Toast.LENGTH_LONG).show();

        destination.setText(tourPlan.getDestination());
        addOntherPlace.setText(tourPlan.getOtherPlaces());
        accommodation.setText(tourPlan.getAccommodation());
        noOfDays.setText(tourPlan.getNoOfDays());
        date.setText(tourPlan.getDate());

        //deletion
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dh = new DatabaseHelper(ViewTourPlan.this);
                TourPlan tp = new TourPlan(destination.getText().toString(),addOntherPlace.getText().toString(), accommodation.getText().toString(), noOfDays.getText().toString(),date.getText().toString());

                boolean delete = dh.delete(tp);
                if(delete==true){
                Intent intent1 = new Intent(ViewTourPlan.this,MainActivity.class);
                startActivity(intent1);
                Toast.makeText(ViewTourPlan.this,"Tour Plan was Deleted",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ViewTourPlan.this,"Could not delete",Toast.LENGTH_SHORT).show();

                }
            }
        });
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TourPlan tp = new TourPlan(destination.getText().toString(),addOntherPlace.getText().toString(), accommodation.getText().toString(), noOfDays.getText().toString(),date.getText().toString());

                DatabaseHelper databaseHelper = new DatabaseHelper(ViewTourPlan.this);
                boolean suc = databaseHelper.update(tp);
                if(suc == true){
                    //Toast.makeText(ViewTourPlan.this,"Plan was updated" + destination.getText().toString(),Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(ViewTourPlan.this,MainActivity.class);
                    startActivity(intent1);
                }
                else{
                    Toast.makeText(ViewTourPlan.this,"Could not update",Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}