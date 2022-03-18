package com.example.mad_a1_g8;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBClass {

    // database constants
    public static final String DB_NAME = "tripplanner.db";
    public static final int    DB_VERSION = 1;

    // trip planner table constants
    public static final String TRIP_TABLE = "tripplanner";

    public static final String TRIP_ID = "id";
    public static final int    TRIP_ID_COL = 0;

    public static final String TRIP_NAME = "name";
    public static final int    TRIP_NAME_COL = 1;

    public static final String TRIP_DESTINATION = "destination";
    public static final int    TRIP_DESTINATION_COL = 2;

    public static final String TRIP_HOTEL = "hotel";
    public static final int    TRIP_HOTEL_COL = 3;

    public static final String TRIP_NIGHTS = "nights";
    public static final int    TRIP_NIGHTS_COL = 4;

    public static final String TRIP_SIGHTS = "sights";
    public static final int    TRIP_SIGHTS_COL = 5;

    public static final String TRIP_PHONE = "phone";
    public static final int    TRIP_PHONE_COL = 6;

    public static final String CREATE_TASK_TABLE =
            "CREATE TABLE " + TRIP_TABLE + " (" +
                    TRIP_ID             + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    TRIP_NAME           + " TEXT NOT NULL, " +
                    TRIP_DESTINATION    + " TEXT    NOT NULL, " +
                    TRIP_HOTEL          + " TEXT, " +
                    TRIP_NIGHTS         + " INTEGER NOT NULL DEFAULT 0, " +
                    TRIP_SIGHTS         + " INTEGER NOT NULL DEFAULT 0," +
                    TRIP_PHONE          + " TEXT);";



}
