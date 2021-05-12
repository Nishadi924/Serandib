package com.example.serendibtours;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "local_tour_guide";
    private static final String TABLE_NAME = "tour_destinations";

    //column names
    private static final String DESTINATION_ID = "dest_Id";
    private static final String LOCATION = "dest_location";
    private static final String PACKAGE_NO = "dest_package_no";
    private static final String TOUR_GUIDE_NAME = "tour_guide-name";
    private static final String NO_OF_DAYS_PER_TRIP = "no_of_days_per_each_trip";
    private static final String IS_ACCOMMODATION_AVAILABLE = "is_accommodation_available";
    private static final String IS_FOOD_AVAILABLE = "is_food_available";

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " " +
                "("
                + DESTINATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LOCATION + " TEXT,"
                + PACKAGE_NO + " TEXT,"
                + TOUR_GUIDE_NAME + " TEXT,"
                + NO_OF_DAYS_PER_TRIP + " TEXT,"
                + IS_ACCOMMODATION_AVAILABLE + " TEXT,"
                + IS_FOOD_AVAILABLE + " TEXT" +
                ");";

        //run the create query
        db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        //drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        //create tables again
        onCreate(db);
    }

    public void insertTourData(TourDataModelClass tourdatamodelclass) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(LOCATION, tourdatamodelclass.getLocation());
        contentValues.put(PACKAGE_NO, tourdatamodelclass.getPackageNo());
        contentValues.put(TOUR_GUIDE_NAME, tourdatamodelclass.getTourGuideName());
        contentValues.put(NO_OF_DAYS_PER_TRIP, tourdatamodelclass.getNoOfDaysPerTrip());
        contentValues.put(IS_ACCOMMODATION_AVAILABLE, tourdatamodelclass.getIsAccommodationAvailable());
        contentValues.put(IS_FOOD_AVAILABLE, tourdatamodelclass.getIsFoodAvailable());

        //save database values in the table
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        //close database
        sqLiteDatabase.close();
    }

    //Get All Tour Data In To a List
    public List<TourDataModelClass> getAllTourData() {

        List<TourDataModelClass> insertData = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                //create new TourDataModelClass object
                TourDataModelClass tourDataModelClass = new TourDataModelClass();
                //Empty object which created before
                tourDataModelClass.setDestId(cursor.getInt(0));
                tourDataModelClass.setLocation(cursor.getString(1));
                tourDataModelClass.setPackageNo(cursor.getString(2));
                tourDataModelClass.setTourGuideName(cursor.getString(3));
                tourDataModelClass.setNoOfDaysPerTrip(cursor.getString(4));
                tourDataModelClass.setIsAccommodationAvailable(cursor.getString(5));
                tourDataModelClass.setIsFoodAvailable(cursor.getString(6));

                //tourDataModelClass object is put in to the Array List object of insertData
                insertData.add(tourDataModelClass);
            } while (cursor.moveToNext());
        }
        return insertData;
    }

    //Delete items from DataBase
    public void deleteTourData(int dest_Id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, DESTINATION_ID + " =?", new String[]{String.valueOf(dest_Id)});
        db.close();
    }

    //Get a single tour data set
    public TourDataModelClass getSingleTourData(int dest_Id) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{DESTINATION_ID, LOCATION, PACKAGE_NO, TOUR_GUIDE_NAME, NO_OF_DAYS_PER_TRIP, IS_ACCOMMODATION_AVAILABLE, IS_FOOD_AVAILABLE},
                DESTINATION_ID + "= ?", new String[]{String.valueOf(dest_Id)},
                null, null,
                null);

        TourDataModelClass tourDataModelClass;
        if (cursor != null) {
                  cursor.moveToFirst();
            tourDataModelClass = new TourDataModelClass(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
            return tourDataModelClass;
        }
        return null;
    }

    //update a single tour data list
    public int updateSingleTourData(TourDataModelClass tourDataModelClass){
            SQLiteDatabase db = getWritableDatabase();

            ContentValues contentValues = new ContentValues();

            contentValues.put(DESTINATION_ID,tourDataModelClass.getDestId());
            contentValues.put(LOCATION,tourDataModelClass.getLocation());
            contentValues.put(PACKAGE_NO,tourDataModelClass.getPackageNo());
            contentValues.put(TOUR_GUIDE_NAME,tourDataModelClass.getTourGuideName());
            contentValues.put(NO_OF_DAYS_PER_TRIP,tourDataModelClass.getNoOfDaysPerTrip());
            contentValues.put(IS_ACCOMMODATION_AVAILABLE,tourDataModelClass.getIsAccommodationAvailable());
            contentValues.put(IS_FOOD_AVAILABLE,tourDataModelClass.getIsFoodAvailable());

            int status = db.update(TABLE_NAME,contentValues,DESTINATION_ID +" =?",
                    new String[]{String.valueOf(tourDataModelClass.getDestId())});

            db.close();
            return status;
    }
}