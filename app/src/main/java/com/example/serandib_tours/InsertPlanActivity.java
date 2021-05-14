package com.example.serandib_tours;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class InsertPlanActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button dateButton,saveButton,addOtherPlaceButton;
    private String date;
    private EditText editTextDestination,editTextAccomodation,editTextNoOfDays,editTextAddOtherPlace,editTextDate;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_plan);

        //buttons
        dateButton = findViewById(R.id.dateButton);
        saveButton = findViewById(R.id.saveButton);


        //editText
        editTextDestination = findViewById(R.id.editTextDestination);
        editTextAccomodation = findViewById(R.id.editTextAccomodation);
        editTextNoOfDays = findViewById(R.id.editTextNoOfDays);
        editTextAddOtherPlace = findViewById(R.id.editTextAddOtherPlace);
        editTextDate = findViewById(R.id.editTextDate);

        //into plan
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        //click listener for save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TourPlan tp = new TourPlan();
                Intent intent;

                try {
                    tp = new TourPlan(editTextDestination.getText().toString(),editTextAddOtherPlace.getText().toString(), editTextAccomodation.getText().toString(), editTextDate.getText().toString(),editTextNoOfDays.getText().toString());
                    DatabaseHelper databaseHelper = new DatabaseHelper(InsertPlanActivity.this);
                    boolean b = databaseHelper.addPlan(tp);
                    if(b==true){
                        Toast.makeText(InsertPlanActivity.this,"Plan added successfully,"+tp.getDate(),Toast.LENGTH_LONG).show();
                        intent = new Intent(InsertPlanActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(InsertPlanActivity.this,"Insertion failure ",Toast.LENGTH_LONG).show();
                    }
                }catch(Exception e){
                    Toast.makeText(InsertPlanActivity.this, "invalid data", Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        int year1 = calendar.get(java.util.Calendar.YEAR);
        int month1 = calendar.get(java.util.Calendar.MONTH);
        int day1 = calendar.get(java.util.Calendar.DATE);
        date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        editTextDate.setText(date);
    }


}

