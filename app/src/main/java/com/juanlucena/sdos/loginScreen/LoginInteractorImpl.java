package com.juanlucena.sdos.loginScreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.juanlucena.sdos.services.InitializeDBService;

public class LoginInteractorImpl extends BroadcastReceiver implements LoginInteractor{

    private OnDBInitializedListener listener;

    @Override
    public void doDBInit(Context context, OnDBInitializedListener listener) {

        this.listener = listener;

        IntentFilter filter = new IntentFilter(InitializeDBService.DB_INTIALIZED);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        context.registerReceiver(this, filter);

        Intent msgIntent = new Intent(context, InitializeDBService.class);
        context.startService(msgIntent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        listener.onDBInitialized(this);
    }
}
