package com.example.android.bandungflight.models;

import java.util.ArrayList;

/**
 * For LEARNING
 * Created by Ridwan Ismail on 23 April 2016
 * You can contact me at : ismail.ridwan98@gmail.com
 * -------------------------------------------------
 * Bandung Flight
 * com.example.android.bandungflight.models
 * or see link for more detail http://bibucket.org/iwanz98/bandung-flight
 */

public class FlightEvent {
    public ArrayList<Flight> flight;
    public String message;
    public String status;

    public FlightEvent(String status, ArrayList<Flight> flight) {
        this.status = status;
        this.flight = flight;
    }

    public FlightEvent(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
