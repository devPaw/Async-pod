package ir.fanap.chat.sdk.application;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.fanap.podasync.Async;

public class SocketPresenter implements SocketContract.presenter {

    private Async async;
    private SocketContract.view view;

    public SocketPresenter(SocketContract.view view, Context context) {
        this.view = view;
        async = Async.getInstance(context);
    }

    @Override
    public String getMessage() {
        String message = async.getMessage();
        view.showMessage(message);
        return message;
    }

    @Override
    public void connect(String socketServerAddress, String appId, String serverName, String token) {
        async.connect(socketServerAddress, appId, serverName, token);
    }

    @Override
    public void sendMessage(String textMessage, int messageType) {
        async.sendMessage(textMessage, messageType);
    }

    @Override
    public void getLiveState() {
        async.getStateLiveData();
    }

    @Override
    public String getState() {
        return async.getState();
    }

    @Override
    public boolean isSocketOpen() {
        boolean isSocketOpen = false;
        if ((async.getState()) != null) {
            if (async.getState().equals("OPEN")) {
                isSocketOpen = true;
            }
        }
        return isSocketOpen;
    }

    @Override
    public void logOut() {
        async.logOut();
    }

    @Override
    public LiveData<String> getLiveData() {
        return async.getStateLiveData();
    }

    @Override
    public void getErrorMessage() {
        String error = async.getErrorMessage();
        view.showErrorMessage(error);
    }

    @Override
    public void closeSocket() {
        async.closeSocket();
    }
}
