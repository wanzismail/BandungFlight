package com.wanztudio.iak.bandungflight.models;

import java.util.ArrayList;

/**
 * For LEARNING
 * Created by Ridwan Ismail on 23 April 2016
 * You can contact me at : ismail.ridwan98@gmail.com
 * -------------------------------------------------
 * Bandung Flight
 * com.wanztudio.iak.bandungflight.models
 * or see link for more detail https://github.com/iwanz98/BandungFlight
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
