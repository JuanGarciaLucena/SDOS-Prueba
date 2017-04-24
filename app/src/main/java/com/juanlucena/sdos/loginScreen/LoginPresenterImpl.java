package com.juanlucena.sdos.loginScreen;

import android.content.BroadcastReceiver;
import android.content.Context;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnDBInitializedListener{

    private Context context;
    private LoginScreen loginScreen;
    private LoginInteractor loginInteractor = new LoginInteractorImpl();

    public LoginPresenterImpl(Context context, LoginScreen loginScreen){
        this.context = context;
        this.loginScreen = loginScreen;
    }

    @Override
    public void buildDatabase() {
        loginScreen.showProgress();
        loginInteractor.doDBInit(context, this);
    }

    @Override
    public void onDBInitialized(BroadcastReceiver receiver) {
        loginScreen.hideProgress();
        loginScreen.databaseInitialized(receiver);
    }
}
