package com.wanztudio.iak.bandungflight.networks;

/**
 * For LEARNING
 * Created by Ridwan Ismail on 23 April 2016
 * You can contact me at : ismail.ridwan98@gmail.com
 * -------------------------------------------------
 * Bandung Flight
 * com.wanztudio.iak.bandungflight.networks
 * or see link for more detail https://github.com/iwanz98/BandungFlight
 */

public class URIUtil {
    public static String encode(String input) {
        StringBuilder resultStr = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (isUnSafe(ch)) {
                resultStr.append('%');
                resultStr.append(toHex(ch / 16));
                resultStr.append(toHex(ch % 16));
            } else {
                resultStr.append(ch);
            }
        }
        return resultStr.toString();
    }

    private static char toHex(int ch) {
        return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
    }

    private static boolean isUnSafe(char ch) {
        if (ch > 128 || ch < 0)
            return true;
        return " %*$&+,/:;=?@<>#%'\n".indexOf(ch) >= 0;
    }
}
