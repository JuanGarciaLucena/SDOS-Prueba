package com.juanlucena.sdos.loginScreen;

import android.content.BroadcastReceiver;
import android.content.Context;

public interface LoginInteractor {

    void doDBInit(Context context, OnDBInitializedListener listener);

    interface OnDBInitializedListener{
        void onDBInitialized(BroadcastReceiver receiver);
    }
}
