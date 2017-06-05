package client.websocket.arief.com.simplewebsocketclient.object;

/**
 * Created by arief pc on 6/4/2017.
 */

public class DataSend {

    public String uuid;
    public String comment;
    public DataSend(String uuid, String comment){
        this.comment = comment;
        this.uuid = uuid;
    }
}
