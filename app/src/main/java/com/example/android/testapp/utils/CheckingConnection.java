package com.example.android.testapp.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.android.testapp.App;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Checking the Internet connection
 */
public class CheckingConnection {

    public static boolean hasConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getContext().getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = connectivityManager.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
