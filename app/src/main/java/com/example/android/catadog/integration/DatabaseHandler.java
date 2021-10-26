package com.example.android.catadog.integration;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Catadog";
    private static final String TABLE_RAZAS = "razas";
    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RAZAS_TABLE = "CREATE TABLE " + TABLE_RAZAS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOMBRE + " TEXT" + ")";
        db.execSQL(CREATE_RAZAS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RAZAS);

        // Create tables again
        onCreate(db);
    }

    public void addRaza(String raza) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, raza);

        // Inserting Row
        db.insert(TABLE_RAZAS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
}
