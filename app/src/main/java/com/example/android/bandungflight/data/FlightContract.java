package com.example.android.bandungflight.data;

import android.provider.BaseColumns;

/**
 * For LEARNING
 * Created by Ridwan Ismail on 23 April 2016
 * You can contact me at : ismail.ridwan98@gmail.com
 * -------------------------------------------------
 * Bandung Flight
 * com.example.android.bandungflight.utils
 * or see link for more detail http://bibucket.org/iwanz98/bandung-flight
 */

public class FlightContract {

    public static final class FlightEntry implements BaseColumns {
        public static final String TABLE_NAME = "flight";

        public static final String COLUMN_FLIGHT_ID             = "flight_id";
        // Carrier flight code prefix
        public static final String COLUMN_CARRIER_CODE          = "carrier_code";
        // Carrier flight number
        public static final String COLUMN_FLIGHT_NUMBER         = "flight_number";
        public static final String COLUMN_CARRIER_NAME          = "carrier_name";
        // Departure city name
        public static final String COLUMN_DEPARTURE_CITY_NAME   = "departure_city_name";
        // Departure airport name
        public static final String COLUMN_DEPARTURE_AIRPORT_NAME   = "departure_airport_name";
        // Scheduled departure local time in YYYY-MM-DDThh:mm:ss
        public static final String COLUMN_DEPARTURE_TIMESTAMP   = "departure_timestamp";
        // Arrival city name
        public static final String COLUMN_ARRIVAL_CITY_NAME     = "arrival_city_name";
        // Arrival airport name
        public static final String COLUMN_ARRIVAL_AIRPORT_NAME  = "arrival_airport_name";
        // Scheduled arrival local time in YYYY-MM-DDThh:mm:ss
        public static final String COLUMN_ARRIVAL_TIMESTAMP     = "arrival_timestamp";
        // Estimated flight duration in minutes
        public static final String COLUMN_FLIGHT_DURATION       = "flight_duration";
        // Name of plane model for the flight
        public static final String COLUMN_PLANE_MODEL           = "plane_model";

    }
}
