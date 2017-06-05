package client.websocket.arief.com.simplewebsocketclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import client.websocket.arief.com.simplewebsocketclient.object.Comment;
import client.websocket.arief.com.simplewebsocketclient.presenter.CommentsPresenter;
import client.websocket.arief.com.simplewebsocketclient.service.Api;
import client.websocket.arief.com.simplewebsocketclient.service.WebSocketClient;
import client.websocket.arief.com.simplewebsocketclient.service.iPresenter;
import client.websocket.arief.com.simplewebsocketclient.view.CommentAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * Created by arief pc on 6/4/2017.
 */

public class CommentsActivity extends AppCompatActivity implements iPresenter.ViewComments, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rvMain)
    RecyclerView mRecyclerView;

    @BindView(R.id.etComment)
    EditText etComment;


    @BindView(R.id.refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private CommentAdapter mCommentAdapter;
    private CommentsPresenter mCommentsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_comment);
        ButterKnife.bind(this);

        mCommentAdapter = new CommentAdapter(new ArrayList<Comment.Data>());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mCommentAdapter);


        mCommentsPresenter = new CommentsPresenter(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mCommentsPresenter.getComments(Api.ISSUE_ID);

        loadWebSocket();
    }

    @OnClick(R.id.btnSend)
    void click(){
        mCommentsPresenter.insertComment(Api.ISSUE_ID, etComment.getText().toString());
    }

    @Override
    public void onRefresh() {
        mCommentsPresenter.getComments(Api.ISSUE_ID);
    }


    @Override
    public void loading(boolean isLoading) {
        mSwipeRefreshLayout.setRefreshing(isLoading);
    }

    @Override
    public void addComment(Comment.Data commentPost) {
        etComment.setText("");
        mCommentAdapter.addComment(commentPost);
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void setUpData(List<Comment.Data> commentResult) {
        mCommentAdapter.update(commentResult);
        mRecyclerView.smoothScrollToPosition(0);
    }

    // trigger after any update data from server
    void loadWebSocket(){
        Request request = new Request.Builder()
                .url("wss://dev.innerbeans.org:443/common/mobileSocket")
                .header("token", Api.TOKEN)
                .build();
        WebSocketClient listener = new WebSocketClient();
        OkHttpClient client = new OkHttpClient();
        WebSocket ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }
}
