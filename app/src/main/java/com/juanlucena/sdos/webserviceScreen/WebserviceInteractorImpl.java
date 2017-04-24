package com.juanlucena.sdos.webserviceScreen;

import android.content.Context;

import com.juanlucena.sdos.model.WebserviceObject;
import com.juanlucena.sdos.services.WebserviceConfiguration;
import com.orm.SugarContext;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebserviceInteractorImpl implements WebserviceInteractor {

    private WebserviceConfiguration apiService = WebserviceConfiguration.retrofit.create(WebserviceConfiguration.class);
    private Context context;
    private WebserviceInteractor.OnObjectsRequestedListener listener;

    public WebserviceInteractorImpl(Context context, WebserviceInteractor.OnObjectsRequestedListener listener){
        this.context = context;
        this.listener = listener;
    }


    @Override
    public void doRequestWebservice() {

        final Call<List<WebserviceObject>> call = apiService.callRequestItems();

        call.enqueue(new Callback<List<WebserviceObject>>() {
            @Override
            public void onResponse(Call<List<WebserviceObject>> call, Response<List<WebserviceObject>> response) {

                List<WebserviceObject> webserviceObjectList = response.body();
                SugarContext.init(context);
                for(WebserviceObject item: webserviceObjectList){
                    item.save();
                    item.getLocation1().save();
                }
                SugarContext.terminate();

                listener.onObjectsRequested();
            }

            @Override
            public void onFailure(Call<List<WebserviceObject>> call, Throwable t) {
            }
        });
    }
}
