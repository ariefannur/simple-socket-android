package client.websocket.arief.com.simplewebsocketclient.config;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by arief pc on 6/4/2017.
 */

public class ImageUtils {

    public static void displayImage(Context mContext, ImageView img, String url){
        Picasso.with(mContext).load(url).into(img);
    }
}
