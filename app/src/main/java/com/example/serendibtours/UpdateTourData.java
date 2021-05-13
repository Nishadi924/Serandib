package com.example.serendibtours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateTourData extends AppCompatActivity {

    private EditText updateLocation,updatePackageNo,updateTourGuideName,updateNoOfDaysPerTrip,updateIsAccommodationAvailable,updateIsFoodAvailable;
    private Button update;
    private DBHandler dbHandler;
    private Context context;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tour_data);

        context = this;
        dbHandler = new DBHandler(context);

        updateLocation = findViewById(R.id.UpdateLocation);
        updatePackageNo = findViewById(R.id.UpdatePackageNo);
        updateTourGuideName = findViewById(R.id.UpdateTourGuideName);
        updateNoOfDaysPerTrip = findViewById(R.id.UpdateNoOfDaysPerTrip);
        updateIsAccommodationAvailable = findViewById(R.id.UpdateIsAccommodationAvailable);
        updateIsFoodAvailable = findViewById(R.id.UpdateIsFoodAvailable);
        update = findViewById(R.id.UpdateData);

        final String dest_Id = getIntent().getStringExtra("dest_Id");
        TourDataModelClass tourDataModelClass = dbHandler.getSingleTourData(Integer.parseInt(dest_Id));

        updateLocation.setText(tourDataModelClass.getLocation());
        updatePackageNo.setText(tourDataModelClass.getPackageNo());
        updateTourGuideName.setText(tourDataModelClass.getTourGuideName());
        updateNoOfDaysPerTrip.setText(tourDataModelClass.getNoOfDaysPerTrip());
        updateIsAccommodationAvailable.setText(tourDataModelClass.getIsAccommodationAvailable());
        updateIsFoodAvailable.setText(tourDataModelClass.getIsFoodAvailable());

        //calling the validate methods
        if (!updateValidateLocation() | !updateValidatePackageNo() | !updateValidateTourGuideName() | !updateValidNoOfDaysPerTrip() | !updateValidIsAccommodationAvailable() | !updateValidIsFoodAvailable()){
            return;
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateLocationText = updateLocation.getText().toString();
                String updatePackageNoText = updatePackageNo.getText().toString();
                String updateTourGuideNameText = updateTourGuideName.getText().toString();
                String updateNoOfDaysPerTripText = updateNoOfDaysPerTrip.getText().toString();
                String updateIsAccommodationAvailableText = updateIsAccommodationAvailable.getText().toString();
                String updateIsFoodAvailableText = updateIsFoodAvailable.getText().toString();


                TourDataModelClass tourDataModelClass1 = new TourDataModelClass(Integer.parseInt(dest_Id),updateLocationText,updatePackageNoText,updateTourGuideNameText,updateNoOfDaysPerTripText,
                        updateIsAccommodationAvailableText,updateIsFoodAvailableText);
                int state = dbHandler.updateSingleTourData(tourDataModelClass1);
                startActivity(new Intent(context,MainActivity.class));
            }
        });
    }

    //validate function

    //Validate Function

    private boolean updateValidateLocation() {
        String val = updateLocation.getText().toString().trim();
        if (val.isEmpty()) {
            updateLocation.setError("Field Cannot Be Empty!");
            return false;
        } else {
            updateLocation.setError(null);
            updateLocation.setEnabled(false);
            return true;
        }
    }

    private boolean updateValidatePackageNo(){
        String val= updatePackageNo.getText().toString().trim();
        String checkSpaces = "\\A\\w{1,20}\\z";
        if (val.isEmpty()){
            updatePackageNo.setError("Field Cannot Be Empty!");
            return false;
        }else if(val.length()>20){
            updatePackageNo.setError("Package No is too large!");
            return false;
        }else if(!val.matches(checkSpaces)){
            updatePackageNo.setError("No White Spaces are Allow!");
            return false;
        } else {
            updatePackageNo.setError(null);
            updatePackageNo.setEnabled(false);
            return true;
        }
    }

    private boolean updateValidateTourGuideName(){
        String val=updateTourGuideName.getText().toString().trim();
        if (val.isEmpty()){
            updateTourGuideName.setError("Field Cannot Be Empty!");
            return false;
        }else {
            updateTourGuideName.setError(null);
            updateTourGuideName.setEnabled(false);
            return true;
        }
    }

    private boolean updateValidNoOfDaysPerTrip(){
        String val=updateNoOfDaysPerTrip.getText().toString().trim();
        if (val.isEmpty()){
            updateNoOfDaysPerTrip.setError("Field Cannot Be Empty!");
            return false;
        } else {
            updateNoOfDaysPerTrip.setError(null);
            updateNoOfDaysPerTrip.setEnabled(false);
            return true;
        }
    }

    private boolean updateValidIsAccommodationAvailable(){
        String val=updateIsAccommodationAvailable.getText().toString().trim();
        if (val.isEmpty()){
            updateIsAccommodationAvailable.setError("Field Cannot Be Empty!");
            return false;
        } else {
            updateIsAccommodationAvailable.setError(null);
            updateIsAccommodationAvailable.setEnabled(false);
            return true;
        }
    }

    private boolean updateValidIsFoodAvailable(){
        String val=updateIsFoodAvailable.getText().toString().trim();
        if (val.isEmpty()){
            updateIsFoodAvailable.setError("Field Cannot Be Empty!");
            return false;
        } else {
            updateIsFoodAvailable.setError(null);
            updateIsFoodAvailable.setEnabled(false);
            return true;
        }
    }
}