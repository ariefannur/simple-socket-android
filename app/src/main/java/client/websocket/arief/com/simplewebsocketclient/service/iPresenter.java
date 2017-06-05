package client.websocket.arief.com.simplewebsocketclient.service;

import java.util.List;

import client.websocket.arief.com.simplewebsocketclient.object.Comment;

/**
 * Created by arief pc on 6/4/2017.
 */

public interface iPresenter {

    interface TopikPresenter{
        void getTopic(String issueId);
    }

    interface ViewTopik{
        void setUpData();
        void loading(boolean isLoading);
    }

    interface CommentsPresenter{
        void getComments(String issueId);
        void insertComment(String issueId, String comments);
    }

    interface ViewComments{
        void loading(boolean isLoading);

        void addComment(Comment.Data commentPost);

        void setUpData(List<Comment.Data> commentResult);
    }
}
