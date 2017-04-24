package com.juanlucena.sdos.loginScreen;

import android.content.BroadcastReceiver;

public interface LoginScreen {

    void showProgress();
    void hideProgress();
    void databaseInitialized(BroadcastReceiver receiver);
}
