package client.websocket.arief.com.simplewebsocketclient.config;

import android.util.Log;

import client.websocket.arief.com.simplewebsocketclient.BuildConfig;

/**
 * Created by arief pc on 6/4/2017.
 */

public class Utils {

    public static void log(String str){
        if(BuildConfig.DEBUG){
            Log.d("AF", str);
        }
    }
}
