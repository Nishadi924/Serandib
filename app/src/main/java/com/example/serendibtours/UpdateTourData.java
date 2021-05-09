package com.example.serendibtours;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UpdateTourData extends AppCompatActivity {

    private EditText updateLocation,updatePackageNo,updateTourGuideName,updateNoOfDaysPerTrip,updateIsAccommodationAvailable,updateIsFoodAvailable;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tour_data);

        updateLocation = findViewById(R.id.UpdateLocation);
        updatePackageNo = findViewById(R.id.UpdatePackageNo);
        updateTourGuideName = findViewById(R.id.UpdateTourGuideName);
        updateNoOfDaysPerTrip = findViewById(R.id.UpdateNoOfDaysPerTrip);
        updateIsAccommodationAvailable = findViewById(R.id.UpdateIsAccommodationAvailable);
        updateIsFoodAvailable = findViewById(R.id.UpdateIsFoodAvailable);
        update = findViewById(R.id.UpdateData);

    }
}