package com.juanlucena.sdos.webserviceScreen;

public interface WebserviceInteractor {

    void doRequestWebservice();

    interface OnObjectsRequestedListener{
        void onObjectsRequested();
    }
}
