package com.wanztudio.iak.bandungflight.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.wanztudio.iak.bandungflight.data.FlightContract.FlightEntry;

/**
 * For LEARNING
 * Created by Ridwan Ismail on 23 April 2016
 * You can contact me at : ismail.ridwan98@gmail.com
 * -------------------------------------------------
 * Bandung Flight
 * com.wanztudio.iak.bandungflight.utils
 * or see link for more detail https://github.com/iwanz98/BandungFlight
 */

public class FlightDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "flight.db";

    public FlightDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FLIGHT_TABLE = "CREATE TABLE " + FlightEntry.TABLE_NAME + " (" +
                FlightEntry._ID + " INTEGER PRIMARY KEY," +
                FlightEntry.COLUMN_FLIGHT_ID + " INTEGER, " +
                FlightEntry.COLUMN_CARRIER_CODE + " TEXT NOT NULL, " +
                FlightEntry.COLUMN_CARRIER_NAME + " TEXT NOT NULL, " +
                FlightEntry.COLUMN_FLIGHT_NUMBER + " TEXT NOT NULL, " +
                FlightEntry.COLUMN_PLANE_MODEL + " TEXT NOT NULL, " +
                FlightEntry.COLUMN_DEPARTURE_CITY_NAME + " TEXT NOT NULL, " +
                FlightEntry.COLUMN_DEPARTURE_AIRPORT_NAME + " TEXT NOT NULL, " +
                FlightEntry.COLUMN_DEPARTURE_TIMESTAMP + " TEXT NOT NULL, " +
                FlightEntry.COLUMN_ARRIVAL_CITY_NAME + " TEXT NOT NULL, " +
                FlightEntry.COLUMN_ARRIVAL_AIRPORT_NAME + " TEXT NOT NULL, " +
                FlightEntry.COLUMN_ARRIVAL_TIMESTAMP + " TEXT NOT NULL, " +
                FlightEntry.COLUMN_FLIGHT_DURATION + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_FLIGHT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FlightEntry.TABLE_NAME);
        onCreate(db);
    }
}
