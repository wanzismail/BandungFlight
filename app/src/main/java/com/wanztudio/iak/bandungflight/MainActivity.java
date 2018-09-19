package com.wanztudio.iak.bandungflight;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.wanztudio.iak.bandungflight.data.FlightContract;
import com.wanztudio.iak.bandungflight.data.FlightDbHelper;
import com.wanztudio.iak.bandungflight.models.FlightEvent;
import com.wanztudio.iak.bandungflight.networks.FlightLoader;
import com.wanztudio.iak.bandungflight.utils.Cons;
import com.wanztudio.iak.bandungflight.utils.DateUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * For LEARNING
 * Created by Ridwan Ismail on 23 April 2016
 * You can contact me at : ismail.ridwan98@gmail.com
 * -------------------------------------------------
 * Bandung Flight
 * com.wanztudio.iak.bandungflight
 * or see link for more detail https://github.com/iwanz98/BandungFlight
 */

public class MainActivity extends AppCompatActivity {

    // Specify the columns we need.
    private static final String[] FLIGHT_COLUMNS = {
            FlightContract.FlightEntry._ID,
            FlightContract.FlightEntry.COLUMN_FLIGHT_ID,
            FlightContract.FlightEntry.COLUMN_CARRIER_CODE,
            FlightContract.FlightEntry.COLUMN_FLIGHT_NUMBER,
            FlightContract.FlightEntry.COLUMN_CARRIER_NAME,
            FlightContract.FlightEntry.COLUMN_DEPARTURE_CITY_NAME,
            FlightContract.FlightEntry.COLUMN_ARRIVAL_CITY_NAME,
            FlightContract.FlightEntry.COLUMN_DEPARTURE_TIMESTAMP
    };

    private FlightAdapter mFlightAdapter;
    private SQLiteDatabase db;
    private FlightDbHelper dbHelper;
    private FlightLoader mLoader;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ListView listView;

    private SharedPreferences mSharedPref;
    private String lastUpdateDate;

    private boolean isProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView = (ListView) findViewById(android.R.id.list);
            listView.setNestedScrollingEnabled(true);
        }

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));

        mLoader = new FlightLoader(this);
        dbHelper = new FlightDbHelper(this);
        db = dbHelper.getWritableDatabase();

        mSharedPref = getSharedPreferences(Cons.PRIVATE_PREF, Context.MODE_PRIVATE);
        lastUpdateDate = mSharedPref.getString(Cons.LAST_UPDATE_DAY_PREF, "");

        if (!DateUtils.today().equals(lastUpdateDate)) {
            getData(true);
        }

        ReadFlightDataTask readDataTask = new ReadFlightDataTask();
        readDataTask.execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void getData(boolean progress) {
        if (!isNetworkOn(this)) {
            Toast.makeText(MainActivity.this, R.string.tidak_ada_koneksi, Toast.LENGTH_SHORT).show();
        } else {
            try {
                mLoader.getFlightData(Cons.FLIGHT_URL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Subscribe
    public void onFlightEvent(FlightEvent event) {
        if (event.status.equals("OK")) {

            db.beginTransaction();

            try {
                db.delete(FlightContract.FlightEntry.TABLE_NAME, null, null);

                for (int i = 0; i < event.flight.size(); i++) {
                    
                    ContentValues flightValues = new ContentValues();
                    flightValues.put(FlightContract.FlightEntry.COLUMN_FLIGHT_ID, event.flight.get(i).flight_id);
                    flightValues.put(FlightContract.FlightEntry.COLUMN_CARRIER_CODE, event.flight.get(i).flight_carrier_code);
                    flightValues.put(FlightContract.FlightEntry.COLUMN_CARRIER_NAME, event.flight.get(i).flight_carrier_name);
                    flightValues.put(FlightContract.FlightEntry.COLUMN_FLIGHT_NUMBER, event.flight.get(i).flight_number);
                    flightValues.put(FlightContract.FlightEntry.COLUMN_FLIGHT_DURATION, event.flight.get(i).flight_durations);
                    flightValues.put(FlightContract.FlightEntry.COLUMN_PLANE_MODEL, event.flight.get(i).flight_equipment);
                    flightValues.put(FlightContract.FlightEntry.COLUMN_DEPARTURE_AIRPORT_NAME, event.flight.get(i).flight_dp_airport_name);
                    flightValues.put(FlightContract.FlightEntry.COLUMN_DEPARTURE_CITY_NAME, event.flight.get(i).flight_dp_airport_city);
                    flightValues.put(FlightContract.FlightEntry.COLUMN_DEPARTURE_TIMESTAMP, event.flight.get(i).flight_departure_time);
                    flightValues.put(FlightContract.FlightEntry.COLUMN_ARRIVAL_AIRPORT_NAME,  event.flight.get(i).flight_ar_airport_name);
                    flightValues.put(FlightContract.FlightEntry.COLUMN_ARRIVAL_CITY_NAME,  event.flight.get(i).flight_ar_airport_city);
                    flightValues.put(FlightContract.FlightEntry.COLUMN_ARRIVAL_TIMESTAMP, event.flight.get(i).flight_arrival_time);

                    db.insert(FlightContract.FlightEntry.TABLE_NAME, null, flightValues);
                }
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }

            new ReadFlightDataTask().execute();

        } else if (event.status.equals("ERROR")) {
            Toast.makeText(MainActivity.this, event.message, Toast.LENGTH_SHORT).show();
        }
    }

    private class ReadFlightDataTask extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... params) {
            FlightDbHelper dbHelper = new FlightDbHelper(MainActivity.this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            return db.query(FlightContract.FlightEntry.TABLE_NAME,
                    FLIGHT_COLUMNS, null, null, null, null, FlightContract.FlightEntry.COLUMN_DEPARTURE_TIMESTAMP);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            if (cursor != null) {
                ListView listView = (ListView) findViewById(android.R.id.list);
                mFlightAdapter = new FlightAdapter(MainActivity.this, cursor, 0);
                listView.setAdapter(mFlightAdapter);
            }
        }
    }

    public boolean isNetworkOn(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

}
