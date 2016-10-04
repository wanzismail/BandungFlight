package com.example.android.bandungflight.networks;

import android.content.Context;

import com.example.android.bandungflight.R;
import com.example.android.bandungflight.models.Flight;
import com.example.android.bandungflight.models.FlightEvent;
import com.example.android.bandungflight.utils.Debug;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.android.bandungflight.utils.Cons.FLIGHT_AIRPORT_CITY;
import static com.example.android.bandungflight.utils.Cons.FLIGHT_AIRPORT_NAME;
import static com.example.android.bandungflight.utils.Cons.FLIGHT_ARRIVAL_AIRPORT;
import static com.example.android.bandungflight.utils.Cons.FLIGHT_ARRIVAL_TIME;
import static com.example.android.bandungflight.utils.Cons.FLIGHT_CARRIER_CODE;
import static com.example.android.bandungflight.utils.Cons.FLIGHT_CARRIER_NAME;
import static com.example.android.bandungflight.utils.Cons.FLIGHT_DEPARTURE_AIRPORT;
import static com.example.android.bandungflight.utils.Cons.FLIGHT_DEPARTURE_TIME;
import static com.example.android.bandungflight.utils.Cons.FLIGHT_DURATIONS;
import static com.example.android.bandungflight.utils.Cons.FLIGHT_EQUIPMENT;
import static com.example.android.bandungflight.utils.Cons.FLIGHT_ID;
import static com.example.android.bandungflight.utils.Cons.FLIGHT_NUMBER;

/**
 * For LEARNING
 * Created by Ridwan Ismail on 23 April 2016
 * You can contact me at : ismail.ridwan98@gmail.com
 * -------------------------------------------------
 * Bandung Flight
 * com.example.android.bandungflight
 * or see link for more detail http://bibucket.org/iwanz98/bandung-flight
 */

public class FlightLoader {
    private Context mContext;
    private SignedUrl signedUrl;
    
    public FlightLoader(Context context) {
        mContext = context;
    }

    public void getFlightData(String url) throws Exception {
        signedUrl = new SignedUrl(mContext, url);

        Connection.get(signedUrl.getSignedUrl(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                ArrayList<Flight> mFlights = new ArrayList<Flight>();

                Debug.i(String.valueOf(response.length()));
                Debug.i(response.toString());

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject flightItem = response.getJSONObject(i);
                        
                        JSONObject flightDepartureAirport = flightItem.getJSONObject(FLIGHT_DEPARTURE_AIRPORT);
                        JSONObject flightArrivalAirport = flightItem.getJSONObject(FLIGHT_ARRIVAL_AIRPORT);

                        Flight flight = new Flight();

                        flight.flight_id = flightItem.getString(FLIGHT_ID);
                        flight.flight_carrier_code = flightItem.getString(FLIGHT_CARRIER_CODE);
                        flight.flight_carrier_name = flightItem.getString(FLIGHT_CARRIER_NAME);
                        flight.flight_number = flightItem.getString(FLIGHT_NUMBER);
                        flight.flight_durations = flightItem.getInt(FLIGHT_DURATIONS);
                        flight.flight_equipment = flightItem.getString(FLIGHT_EQUIPMENT);

                        flight.flight_dp_airport_name = flightDepartureAirport.getString(FLIGHT_AIRPORT_NAME);
                        flight.flight_dp_airport_city = flightDepartureAirport.getString(FLIGHT_AIRPORT_CITY);
                        flight.flight_departure_time = flightItem.getString(FLIGHT_DEPARTURE_TIME);

                        flight.flight_ar_airport_name = flightArrivalAirport.getString(FLIGHT_AIRPORT_NAME);
                        flight.flight_ar_airport_city = flightArrivalAirport.getString(FLIGHT_AIRPORT_CITY);
                        flight.flight_arrival_time = flightItem.getString(FLIGHT_ARRIVAL_TIME);

                        mFlights.add(flight);
                    }
                    EventBus.getDefault().post(new FlightEvent("OK", mFlights));
                } catch (JSONException e) {
                    e.printStackTrace();
                    EventBus.getDefault().post(new FlightEvent("ERROR", e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                throwable.printStackTrace();
                EventBus.getDefault().post(new FlightEvent("ERROR", mContext.getString(R.string.kesalahan_jaringan)));
            }
        });
    }
}
