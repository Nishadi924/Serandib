package com.example.serandib_tours;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    private FloatingActionButton fab;
    private NavigationView navigationView;



    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView = findViewById(R.id.recycleView);
        fab = findViewById(R.id.floating_action_button);





        //taking values from base
        ArrayList<TourPlan> tPlans = new ArrayList<>();
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        tPlans = (ArrayList<TourPlan>) databaseHelper.viewAll();

        //recycle view
        RecViewAdapter adapter = new RecViewAdapter(this);
        adapter.setTourPlans(tPlans);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));



        //click on listener for floating action button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,InsertPlanActivity.class);

                startActivity(intent);
            }
        });

        //navigation drawer


    }
}