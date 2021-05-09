package com.example.serandb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridLayout mainGridLayout;
    DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainGridLayout =(GridLayout) findViewById(R.id.mainGridLayout);

        setSingleEvent(mainGridLayout);





        Button butt4= (Button)findViewById(R.id.button10);

        butt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int4=new Intent(MainActivity.this,pool.class);
                startActivity(int4);
            }
        });
        drawerLayout = findViewById(R.id.drawer_layout);


    }
    public void ClickMenu(View view){
      navigation.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){

        navigation.closeDrawer(drawerLayout);
    }
    public void ClickHome(View view){
        navigation.redirectActivity(this,navigation.class);
    }
    public void ClickMainActivity(View view){
        recreate();
    }




    private void setSingleEvent(GridLayout mainGridLayout) {

        for (int i=0;i<mainGridLayout.getChildCount();i++){
            CardView cardview =(CardView)mainGridLayout.getChildAt(i);
            final int finalI = i;
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalI==0){
                       Intent intent= new Intent(MainActivity.this,EarlsRegencyHotel.class);
                       startActivity(intent);
                    }
                    else  if(finalI==1){
                        Intent intent= new Intent(MainActivity.this,TheGoldenCrownHotel.class);
                        startActivity(intent);
                    }
                    else  if(finalI==2){
                        Intent intent= new Intent(MainActivity.this,AmayaHillsHotel.class);
                        startActivity(intent);
                    }
                    else if (finalI==3){
                        Intent intent= new Intent(MainActivity.this,TheGrandKandyanHotel.class);
                        startActivity(intent);
                    }

                }
            });
        }
    }


}