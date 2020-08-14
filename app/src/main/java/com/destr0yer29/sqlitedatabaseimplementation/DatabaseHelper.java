package com.destr0yer29.sqlitedatabaseimplementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="university";
    private static final int DB_VERSION=1;

    public DatabaseHelper(@Nullable Context context ) {
        super(context , DB_NAME , null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStatement = "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT,name Text,email Text);";
        db.execSQL(sqlStatement);

        ContentValues values = new ContentValues();
        values.put("name","Manish");
        values.put("email","manishbansiwal29@gmail.com");

        db.insert("students",null,values);

        ContentValues secondValues = new ContentValues();
        secondValues.put("name","yash");
        secondValues.put("email","yash2025@gmail.com");

        db.insert("students",null,secondValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion) {

    }
}
