package com.jenbumapps.core.utility.connect;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connectivity {

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        return (info != null && info.isConnected());
    }

    public static void isServerOnline(ServerConnection.ServerConnectionListener mListener) {
        ServerConnection.getInstance().setConnectionHelper(mListener).execute();
    }

}
