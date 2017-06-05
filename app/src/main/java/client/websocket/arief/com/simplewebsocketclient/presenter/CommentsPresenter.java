package client.websocket.arief.com.simplewebsocketclient.presenter;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import client.websocket.arief.com.simplewebsocketclient.config.Utils;
import client.websocket.arief.com.simplewebsocketclient.object.Comment;
import client.websocket.arief.com.simplewebsocketclient.object.DataSend;
import client.websocket.arief.com.simplewebsocketclient.service.Api;
import client.websocket.arief.com.simplewebsocketclient.service.Connection;
import client.websocket.arief.com.simplewebsocketclient.service.iPresenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by arief pc on 6/4/2017.
 */

public class CommentsPresenter implements iPresenter.CommentsPresenter{

    private iPresenter.ViewComments mViewComments;
    private Api mApi;

    public CommentsPresenter(iPresenter.ViewComments viewComments){
        this.mViewComments = viewComments;
        mApi = Connection.create().create(Api.class);
    }

    @Override
    public void getComments(String issueId) {
        mViewComments.loading(true);
        mApi.getComments(Api.TOKEN, issueId, 0, 100)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Comment.CommentResult>() {
                    @Override
                    public void onCompleted() {
                        mViewComments.loading(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewComments.loading(false);
                        Utils.log(e.getMessage());
                    }

                    @Override
                    public void onNext(Comment.CommentResult commentResult) {
                        mViewComments.setUpData(commentResult.data);
                    }
                });
    }



    @Override
    public void insertComment(String issueId, String comments) {
        mViewComments.loading(true);


        String str = "{\"comment\":\"oke oce \", \"uuid\":\"bean17249_6\"}";


        mApi.postComment(Api.TOKEN, comments, str)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Comment.CommentPost>() {
                    @Override
                    public void onCompleted() {
                        mViewComments.loading(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mViewComments.loading(false);
                    }

                    @Override
                    public void onNext(Comment.CommentPost commentPost) {
                        mViewComments.addComment(commentPost.data);
                    }
                });
    }
}
