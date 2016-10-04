package com.example.android.bandungflight.utils;

import android.util.Log;

/**
 * For LEARNING
 * Created by Ridwan Ismail on 23 April 2016
 * You can contact me at : ismail.ridwan98@gmail.com
 * -------------------------------------------------
 * Bandung Flight
 * com.example.android.bandungflight.utils
 * or see link for more detail http://bibucket.org/iwanz98/bandung-flight
 */

public class Debug {

    public static void i(String message) {
        Debug.i(Cons.TAG, message);
    }

    public static void i(String tag, String message) {
        if (Cons.ENABLE_DEBUG) Log.i(tag, message);
    }

    public static void e(String message) {
        Debug.e(Cons.TAG, message);
    }

    public static void e(String tag, String message) {
        if (Cons.ENABLE_DEBUG) Log.e(tag, message);
    }

    public static void e(String tag, String message, Exception e) {
        if (Cons.ENABLE_DEBUG) {
            Log.e(tag, message);
            e.printStackTrace();
        }
    }

    public static void e(String message, Exception e) {
        Debug.e(message);
        e.printStackTrace();
    }
}
