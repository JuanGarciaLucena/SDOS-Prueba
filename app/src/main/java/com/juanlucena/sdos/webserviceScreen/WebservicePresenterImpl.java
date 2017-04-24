package com.juanlucena.sdos.webserviceScreen;

import android.content.Context;

public class WebservicePresenterImpl implements WebservicePresenter, WebserviceInteractor.OnObjectsRequestedListener{

    private Context context;
    private WebserviceScreen adminScreen;

    public WebservicePresenterImpl(Context context, WebserviceScreen adminScreen){
        this.context = context;
        this.adminScreen = adminScreen;
    }

    @Override
    public void startWebserviceProcess() {
        adminScreen.showProgress();
        new WebserviceInteractorImpl(context, this).doRequestWebservice();
    }

    @Override
    public void onObjectsRequested() {
        adminScreen.hideProgress();
        adminScreen.populateRecyclerView();
    }
}
