package client.websocket.arief.com.simplewebsocketclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import client.websocket.arief.com.simplewebsocketclient.config.ImageUtils;
import client.websocket.arief.com.simplewebsocketclient.config.Utils;
import client.websocket.arief.com.simplewebsocketclient.object.Topik;
import client.websocket.arief.com.simplewebsocketclient.service.Api;
import client.websocket.arief.com.simplewebsocketclient.service.Connection;
import client.websocket.arief.com.simplewebsocketclient.service.WebSocketClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String issueId = "bean17249_6";

    @BindView(R.id.txtTitle)
    TextView txtTitle;

    @BindView(R.id.txtUsername)
    TextView txtUsername;

    @BindView(R.id.imgUser)
    ImageView imgUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

       loadData();

    }

    @OnClick(R.id.btnComments)
    void click(){
        startActivity(new Intent(getApplicationContext(), CommentsActivity.class));
    }



    void loadData(){
        Api api = Connection.create().create(Api.class);
        api.getTopik(Api.TOKEN, issueId)
           .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Topik>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.log(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(Topik topik) {
                        txtTitle.setText(topik.planetName);
                        txtUsername.setText(topik.creatorName);
                        ImageUtils.displayImage(getApplicationContext(), imgUser, topik.creatorIcon);
                    }
                });

    }
}
