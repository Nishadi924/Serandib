package com.example.serendibtours;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Button add;
    private ListView listview;
    Context context;
    private DBHandler dbHandler;
    private List<TourDataModelClass> tourDataModelClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        dbHandler = new DBHandler(context);
        add = findViewById(R.id.add);
        listview = findViewById(R.id.tourinformationlist);
        tourDataModelClassList = new ArrayList<>();

        // call the getAllTourData() which is in DBHandler Class
        tourDataModelClassList = dbHandler.getAllTourData();

        TourDataAdapter adapter = new TourDataAdapter(context,R.layout.tour_guide_information_service,tourDataModelClassList);

        listview.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,InsertTourData.class));
            }
        });

        //create a dialog box which tap on a item in the list view asking about whether update or delete
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               final TourDataModelClass tourDataModelClass = tourDataModelClassList.get(position);

                //creating the prompt dialog box
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(tourDataModelClass.getLocation());
                builder.setMessage(tourDataModelClass.getPackageNo());

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteTourData(tourDataModelClass.getDestId());
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(context,UpdateTourData.class);
                            intent.putExtra("dest_Id",String.valueOf(tourDataModelClass.getDestId()));
                            startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }
}