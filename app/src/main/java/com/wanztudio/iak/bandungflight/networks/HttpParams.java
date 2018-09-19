package com.wanztudio.iak.bandungflight.networks;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * For LEARNING
 * Created by Ridwan Ismail on 23 April 2016
 * You can contact me at : ismail.ridwan98@gmail.com
 * -------------------------------------------------
 * Bandung Flight
 * com.wanztudio.iak.bandungflight.networks
 * or see link for more detail https://github.com/iwanz98/BandungFlight
 */

public class HttpParams {
    private TreeMap<String, HttpValues> mParams;

    public HttpParams() {
        mParams = new TreeMap();
    }

    public void put(String key, HttpValues values) {
        if (!mParams.containsKey(key)) {
            mParams.put(key, values);
        }
    }

    public HttpValues get(String key) {
        return mParams.containsKey(key) ? (HttpValues) mParams.get(key) : null;
    }

    public boolean containsKey(String key) {
        return mParams.containsKey(key);
    }

    public String getQueryString() {
        if (mParams.size() == 0) {
            return "";
        }
        StringBuffer querySb = new StringBuffer();
        int size = mParams.size();
        int i = 0;
        for (String key : mParams.keySet()) {
            HttpValues values = (HttpValues) mParams.get(key);
            if (values == null) {
                querySb.append(key + "=");
            } else if (values.isEmpty()) {
                querySb.append(key + "=");
            } else {
                Iterator<String> iterator = values.getAll().iterator();
                while (iterator.hasNext()) {
                    querySb.append(key + "=" + URIUtil.encode((String) iterator.next()));
                    if (iterator.hasNext()) {
                        querySb.append("&");
                    }
                }
            }
            if (i != size - 1) {
                querySb.append("&");
            }
            i++;
        }
        return "?" + querySb.toString();
    }
}
