package com.example.serendibtours;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TourDataAdapter extends ArrayAdapter<TourDataModelClass> {

    private Context context;
    private int resource;
    List<TourDataModelClass> tourDataModelClassList;

    TourDataAdapter(Context context, int resource, List<TourDataModelClass> tourDataModelClassList){
        super(context,resource,tourDataModelClassList);

        this.context = context;
        this.resource = resource;
        this.tourDataModelClassList = tourDataModelClassList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView mainLocation = row.findViewById(R.id.mainLocation);
        TextView mainPackageNo = row.findViewById(R.id.mainPackageNo);
        TextView mainTourGuideName = row.findViewById(R.id.mainTourGuideName);
        TextView mainNoOfDaysPerTrip = row.findViewById(R.id.mainNoOfDaysPerTrip);
        TextView mainIsAccommodationAvailable = row.findViewById(R.id.mainIsAccommodationAvailable);
        TextView mainIsFoodAvailable = row.findViewById(R.id.mainIsFoodAvailable);

        //tourDataModelClassList [Object1,Object2,Object3]
        TourDataModelClass tourDataModelClass = tourDataModelClassList.get(position);
        mainLocation.setText(tourDataModelClass.getLocation());
        mainPackageNo.setText(tourDataModelClass.getPackageNo());
        mainTourGuideName.setText(tourDataModelClass.getTourGuideName());
        mainNoOfDaysPerTrip.setText(tourDataModelClass.getNoOfDaysPerTrip());
        mainIsAccommodationAvailable.setText(tourDataModelClass.getIsAccommodationAvailable());
        mainIsFoodAvailable.setText(tourDataModelClass.getIsFoodAvailable());

        return row;
    }
}
