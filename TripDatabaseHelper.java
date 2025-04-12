package com.example.madcourseproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TripDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "trip_details.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TRIP_DETAILS = "trip_details";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_DESTINATION = "destination";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_PASSENGERS = "passengers";

    public TripDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_TRIP_DETAILS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_DESTINATION + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_PASSENGERS + " TEXT)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP_DETAILS);
        onCreate(db);
    }

    public long insertTripDetails(String location, String destination, String date, String time, String passengers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_DESTINATION, destination);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_PASSENGERS, passengers);
        long result = db.insert(TABLE_TRIP_DETAILS, null, values);
        db.close();
        return result;
    }
}

