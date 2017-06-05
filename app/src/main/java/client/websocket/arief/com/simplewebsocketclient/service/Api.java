package client.websocket.arief.com.simplewebsocketclient.service;

import org.json.JSONObject;

import java.util.HashMap;

import client.websocket.arief.com.simplewebsocketclient.object.Comment;
import client.websocket.arief.com.simplewebsocketclient.object.DataSend;
import client.websocket.arief.com.simplewebsocketclient.object.Topik;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by arief pc on 6/4/2017.
 */

public interface Api {

    String TOKEN =  "00d8aca5-6b4e-4382-a8dd-b72e36dc160e";
    String ISSUE_ID = "bean17249_6";

    @GET("api/issues/{issue_id}")
    Observable<Topik> getTopik(@Header("token") String token, @Path("issue_id") String issue_id);

    @GET("api/issues/{issue_id}/comments")
    Observable<Comment.CommentResult> getComments(@Header("token") String token, @Path("issue_id") String issue_id, @Query("startNumber") int number, @Query("pageSize") int page);

    @Headers({"Content-type: application/json","Accept: */*"})
    @POST("api/issues/{issue_id}/comments")
    Observable<Comment.CommentPost> postComment(@Header("token") String token, @Path("issue_id") String issue_id, @Body DataSend body);



}
