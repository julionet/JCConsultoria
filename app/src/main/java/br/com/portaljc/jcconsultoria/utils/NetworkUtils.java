package br.com.portaljc.jcconsultoria.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;

import java.io.IOException;

public class NetworkUtils {

    public static boolean isNetworkAvailable(@NonNull Context context) {
        boolean c = isConnected(context);
        boolean w = isWifiConnected(context);
        boolean m = isMobileConnected(context);
        return c && (w || m);
    }

    private static boolean isConnected(@NonNull Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr != null) {
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } else {
            return false;
        }
    }

    private static boolean isWifiConnected(@NonNull Context context) {
        return isConnected(context, ConnectivityManager.TYPE_WIFI);
    }

    private static boolean isMobileConnected(@NonNull Context context) {
        return isConnected(context, ConnectivityManager.TYPE_MOBILE);
    }

    private static boolean isConnected(@NonNull Context context, int type) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (connMgr != null) {
                NetworkInfo networkInfo = connMgr.getNetworkInfo(type);
                return networkInfo != null && networkInfo.isConnected();
            } else {
                return false;
            }
        } else {
            return connMgr != null && isConnected(connMgr, type);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static boolean isConnected(@NonNull ConnectivityManager connMgr, int type) {
        Network[] networks = connMgr.getAllNetworks();
        NetworkInfo networkInfo;
        for (Network mNetwork : networks) {
            networkInfo = connMgr.getNetworkInfo(mNetwork);
            if (networkInfo != null && networkInfo.getType() == type && networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public static Boolean isInternetNetwork(@NonNull Context context) {
        boolean c = isConnected(context);
        boolean w = isWifiConnected(context);
        boolean m = isMobileConnected(context);

        if (c && w) {
            return true;
        }

        if (c && (w || m)) {
            try {
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
                int result = process.waitFor();
                return (result == 0);
            } catch (IOException | InterruptedException e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
