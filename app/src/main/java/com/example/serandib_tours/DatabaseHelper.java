package com.example.serandib_tours;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TOUR_PLAN = "tour_plan";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DESTINATION = "destination";
    public static final String COLUMN_ACCOMMODATION = "accommodation";
    public static final String COLUMN_NO_OF_DAYS = "no_of_days";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_OTHER_PLACES = "other_places";
    Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "serandib.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTimeTable = "create table " + TOUR_PLAN + " (" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_DESTINATION + " text, " + COLUMN_OTHER_PLACES + " text," + COLUMN_ACCOMMODATION + " text, " + COLUMN_NO_OF_DAYS + " text, " + COLUMN_DATE + " text, " + COLUMN_STATUS + " text)";
        db.execSQL(creatTimeTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addPlan(TourPlan tourPlan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DESTINATION,tourPlan.getDestination());
        cv.put(COLUMN_OTHER_PLACES,tourPlan.getOtherPlaces());
        cv.put(COLUMN_ACCOMMODATION,tourPlan.getAccommodation());
        cv.put(COLUMN_NO_OF_DAYS,tourPlan.getNoOfDays());
        cv.put(COLUMN_DATE,tourPlan.getDate());
        cv.put(COLUMN_STATUS,tourPlan.getState());

        long suc = db.insert(TOUR_PLAN,null,cv);
        if(suc == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public List<TourPlan> viewAll(){
        List<TourPlan> planList = new ArrayList<>();

        String querySelect = "select * from " + TOUR_PLAN ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.rawQuery(querySelect,null);

        if(cr.moveToFirst()){
            do {
                int id = cr.getInt(0);
                String destination = cr.getString(1);
                String otherPlaces = cr.getString(2);
                String accommodation = cr.getString(3);
                String noOfDays = cr.getString(4);
                String date = cr.getString(5);
                String state = cr.getString(6);

                TourPlan plan = new TourPlan(destination, otherPlaces, accommodation, noOfDays, date, state);
                planList.add(plan);
            }while (cr.moveToNext());
        }
        else{

        }
        cr.close();
        db.close();

        return planList;
    }

    public boolean delete(TourPlan tourPlan){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "delete from " + TOUR_PLAN + " where " + COLUMN_DESTINATION + "=" + tourPlan.getDestination();

        int suc = db.delete(TOUR_PLAN, "destination = ?", new String[]{tourPlan.getDestination()});


        if(suc == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean update(TourPlan tourPlan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DESTINATION,tourPlan.getDestination());
        cv.put(COLUMN_OTHER_PLACES,tourPlan.getOtherPlaces());
        cv.put(COLUMN_ACCOMMODATION,tourPlan.getAccommodation());
        cv.put(COLUMN_NO_OF_DAYS,tourPlan.getNoOfDays());
        cv.put(COLUMN_DATE,tourPlan.getDate());
        cv.put(COLUMN_STATUS,tourPlan.getState());


        int suc = db.update(TOUR_PLAN,cv, " destination = ?" ,new String[] {tourPlan.getDestination()});
        if(suc == 1) {
            return true;
        }
        else {
            return false;
        }

    }
}
