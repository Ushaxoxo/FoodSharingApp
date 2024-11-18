package com.example.android.fud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Donations.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "donations";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AADHAAR = "aadhaar";
    private static final String COLUMN_DISH = "dish";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_ASSURANCE = "assurance";
    private static final String COLUMN_IMAGE_URI = "imageUri";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_IS_NGO = "is_ngo"; // 0 = regular user, 1 = NGO
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_AADHAAR + " TEXT, "
                + COLUMN_DISH + " TEXT, "
                + COLUMN_TIME + " TEXT, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_QUANTITY + " INTEGER, "
                + COLUMN_ASSURANCE + " INTEGER, " // Boolean stored as INTEGER
                + COLUMN_IMAGE_URI + " TEXT)";
        db.execSQL(CREATE_TABLE);
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT UNIQUE, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_IS_NGO + " INTEGER DEFAULT 0)"; // 0 = Regular User, 1 = NGO
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert data
    public boolean insertDonation(String name, String aadhaar, String dish, String time, String date,
                                  int quantity, boolean assurance, String imageUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AADHAAR, aadhaar);
        values.put(COLUMN_DISH, dish);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_ASSURANCE, assurance ? 1 : 0); // Convert boolean to INTEGER
        values.put(COLUMN_IMAGE_URI, imageUri);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    // Retrieve all data (optional)
    public Cursor getAllDonations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }


    // Register a User
    public boolean registerUser(String username, String password, boolean isNgo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_IS_NGO, isNgo ? 1 : 0);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    // Authenticate User
    public boolean authenticateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean authenticated = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return authenticated;
    }

    // Authenticate NGO User
    public boolean authenticateNgo(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ? AND "
                + COLUMN_IS_NGO + " = 1";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean authenticated = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return authenticated;
    }
}
