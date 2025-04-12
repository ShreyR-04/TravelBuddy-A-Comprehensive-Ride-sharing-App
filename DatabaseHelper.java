package com.example.madcourseproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserAndTripDB.db";
    private static final int DATABASE_VERSION = 1;

    // Users table
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_PASSWORD = "password";

    // Trips table
    private static final String TABLE_TRIPS = "trips";
    private static final String COLUMN_TRIP_ID = "id";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_DESTINATION = "destination";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_PASSENGERS = "passengers";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PHONE + " TEXT,"
                + COLUMN_PASSWORD + " TEXT" + ")";

        String CREATE_TRIP_TABLE = "CREATE TABLE " + TABLE_TRIPS + "("
                + COLUMN_TRIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_LOCATION + " TEXT,"
                + COLUMN_DESTINATION + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_TIME + " TEXT,"
                + COLUMN_PASSENGERS + " TEXT" + ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TRIP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPS);
        onCreate(db);
    }

    // User table methods
    public boolean addUser(String name, String email, String phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        return result != -1;
    }

    public boolean checkUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_USER_ID}, COLUMN_EMAIL + "=?",
                new String[]{email}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_USER_ID}, COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{email, password}, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    // Trip table methods
    public void addTrip(String location, String destination, String date, String time, String passengers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_DESTINATION, destination);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_PASSENGERS, passengers);

        db.insert(TABLE_TRIPS, null, values);
        db.close();
    }

    public Cursor getAllTrips() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (!db.isOpen()) {
            db = this.getWritableDatabase();  // Reopen if closed
        }
        return db.rawQuery("SELECT * FROM " + TABLE_TRIPS, null);
    }

}


