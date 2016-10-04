package com.example.android.bandungflight.utils;

/**
 * For LEARNING
 * Created by Ridwan Ismail on 23 April 2016
 * You can contact me at : ismail.ridwan98@gmail.com
 * -------------------------------------------------
 * Bandung Flight
 * com.example.android.bandungflight.utils
 * or see link for more detail http://bibucket.org/iwanz98/bandung-flight
 */

public class Cons {
    public static final String TAG = "Bandung Flight";
    public static final boolean ENABLE_DEBUG = true;

    public static final String PRIVATE_PREF = "private_pref";
    public static final String LAST_UPDATE_DAY_PREF = "last_update_pref";
    
    public static final String FLIGHT_URL = "http://andryod.com/androidtraining/flights/departures";

    //paramater api
    public static String FLIGHT_ID = "id";
    public static String FLIGHT_CARRIER_CODE = "carrierCode";
    public static String FLIGHT_CARRIER_NAME = "carrierName";
    public static String FLIGHT_NUMBER = "flightNumber";
    public static String FLIGHT_DURATIONS = "flightDurations";
    public static String FLIGHT_EQUIPMENT = "flightEquipment";

    public static String FLIGHT_DEPARTURE_AIRPORT = "departureAirport";
    public static String FLIGHT_DEPARTURE_TIME = "departureTimestamp";

    public static String FLIGHT_ARRIVAL_AIRPORT = "arrivalAirport";
    public static String FLIGHT_ARRIVAL_TIME = "arrivalTimestamp";

    public static String FLIGHT_AIRPORT_CITY = "city";
    public static String FLIGHT_AIRPORT_NAME = "name";

    //column table
    public static final int COL_FLIGHT_INT_ID = 0;
    public static final int COL_FLIGHT_ID = 1;
    public static final int COL_CARRIER_CODE = 2;
    public static final int COL_FLIGHT_NUMBER = 3;
    public static final int COL_CARRIER_NAME = 4;
    public static final int COL_DEPARTURE_CITY_NAME = 5;
    public static final int COL_ARRIVAL_CITY_NAME = 6;
    public static final int COL_DEPARTURE_TIMESTAMP = 7;
    
}
