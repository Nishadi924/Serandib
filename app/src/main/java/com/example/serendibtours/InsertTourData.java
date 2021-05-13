package com.example.serendibtours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertTourData extends AppCompatActivity {

    private EditText location, packageNo, tourGuideName, noOfDaysPerTrip, isAccommodationAvailable, isFoodAvailable;
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

        //calling the validate methods
        if (!validateLocation() | !validatePackageNo() | !validateTourGuideName() | !validNoOfDaysPerTrip() | !validIsAccommodationAvailable() | !validIsFoodAvailable()){
            return;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userLocation = location.getText().toString();
                String userPackageNo = packageNo.getText().toString();
                String userTourGuideName = tourGuideName.getText().toString();
                String userNoOfDaysPerTrip = noOfDaysPerTrip.getText().toString();
                String userIsAccommodationAvailable = isAccommodationAvailable.getText().toString();
                String userIsFoodAvailable = isFoodAvailable.getText().toString();

                TourDataModelClass tourdatamodelclass = new TourDataModelClass(userLocation, userPackageNo, userTourGuideName, userNoOfDaysPerTrip, userIsAccommodationAvailable, userIsFoodAvailable);
                dbHandler.insertTourData(tourdatamodelclass);

                startActivity(new Intent(context, MainActivity.class));
            }
        });
    }

    //Validate Function

    private boolean validateLocation() {
        String val = location.getText().toString().trim();
        if (val.isEmpty()) {
            location.setError("Field Cannot Be Empty!");
            return false;
        } else {
            location.setError(null);
            location.setEnabled(false);
            return true;
        }
    }

    private boolean validatePackageNo(){
        String val= packageNo.getText().toString().trim();
        String checkSpaces = "\\A\\w{1,20}\\z";
        if (val.isEmpty()){
            packageNo.setError("Field Cannot Be Empty!");
            return false;
        }else if(val.length()>20){
            packageNo.setError("Package No is too large!");
            return false;
        }else if(!val.matches(checkSpaces)){
            packageNo.setError("No White Spaces are Allow!");
            return false;
        } else {
            packageNo.setError(null);
            packageNo.setEnabled(false);
            return true;
        }
    }

    private boolean validateTourGuideName(){
        String val=tourGuideName.getText().toString().trim();
        if (val.isEmpty()){
            tourGuideName.setError("Field Cannot Be Empty!");
            return false;
        }else {
            tourGuideName.setError(null);
            tourGuideName.setEnabled(false);
            return true;
        }
    }

    private boolean validNoOfDaysPerTrip(){
        String val=noOfDaysPerTrip.getText().toString().trim();
        if (val.isEmpty()){
            noOfDaysPerTrip.setError("Field Cannot Be Empty!");
            return false;
        } else {
            noOfDaysPerTrip.setError(null);
            noOfDaysPerTrip.setEnabled(false);
            return true;
        }
    }

    private boolean validIsAccommodationAvailable(){
        String val=isAccommodationAvailable.getText().toString().trim();
        if (val.isEmpty()){
            isAccommodationAvailable.setError("Field Cannot Be Empty!");
            return false;
        } else {
            isAccommodationAvailable.setError(null);
            isAccommodationAvailable.setEnabled(false);
            return true;
        }
    }

    private boolean validIsFoodAvailable(){
        String val=isFoodAvailable.getText().toString().trim();
        if (val.isEmpty()){
            isFoodAvailable.setError("Field Cannot Be Empty!");
            return false;
        } else {
            isFoodAvailable.setError(null);
            isFoodAvailable.setEnabled(false);
            return true;
        }
    }
}