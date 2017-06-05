package client.websocket.arief.com.simplewebsocketclient.presenter;

import client.websocket.arief.com.simplewebsocketclient.service.iPresenter;

/**
 * Created by arief pc on 6/4/2017.
 */

public class TopikPresenter implements iPresenter.TopikPresenter {

    private iPresenter.ViewTopik mViewTopik;

    public TopikPresenter(iPresenter.ViewTopik viewTopik){
        this.mViewTopik = viewTopik;
    }

    @Override
    public void getTopic(String issueId) {

    }
}
