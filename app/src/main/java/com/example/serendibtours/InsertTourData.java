package com.example.serendibtours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertTourData extends AppCompatActivity {

    private EditText location,packageNo,tourGuideName,noOfDaysPerTrip,isAccommodationAvailable,isFoodAvailable;
    private Button btn;
    private DBHandler dbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_tour_data);

        location = findViewById(R.id.Location);
        packageNo = findViewById(R.id.PackageNo);
        tourGuideName = findViewById(R.id.TourGuideName);
        noOfDaysPerTrip = findViewById(R.id.NoOfDaysPerTrip);
        isAccommodationAvailable = findViewById(R.id.IsAccommodationAvailable);
        isFoodAvailable = findViewById(R.id.IsFoodAvailable);
        btn = findViewById(R.id.InsertData);
        context = this;

        dbHandler = new DBHandler(context);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userLocation = location.getText().toString();
                String userPackageNo = packageNo.getText().toString();
                String userTourGuideName = tourGuideName.getText().toString();
                String userNoOfDaysPerTrip = noOfDaysPerTrip.getText().toString();
                String userIsAccommodationAvailable = isAccommodationAvailable.getText().toString();
                String userIsFoodAvailable = isFoodAvailable.getText().toString();

                TourDataModelClass tourdatamodelclass = new TourDataModelClass(userLocation,userPackageNo,userTourGuideName,userNoOfDaysPerTrip,userIsAccommodationAvailable,userIsFoodAvailable);
                dbHandler.insertTourData(tourdatamodelclass);

                startActivity(new Intent(context,MainActivity.class));
            }
        });
    }
}