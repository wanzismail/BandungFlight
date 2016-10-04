package com.example.android.bandungflight.networks;

import java.util.TreeSet;

/**
 * For LEARNING
 * Created by Ridwan Ismail on 23 April 2016
 * You can contact me at : ismail.ridwan98@gmail.com
 * -------------------------------------------------
 * Bandung Flight
 * com.example.android.bandungflight.networks
 * or see link for more detail http://bibucket.org/iwanz98/bandung-flight
 */

public class HttpValues {
    private TreeSet<String> mValues;

    public HttpValues() {
        mValues = new TreeSet();
    }

    public HttpValues(String value) {
        mValues = new TreeSet();
        mValues.add(value);
    }

    public void add(String value) {
        mValues.add(value);
    }

    public void remove(String value) {
        mValues.remove(value);
    }

    public void clear() {
        mValues.clear();
    }

    public boolean isEmpty() {
        return mValues.isEmpty();
    }

    public TreeSet<String> getAll() {
        return mValues;
    }
}
