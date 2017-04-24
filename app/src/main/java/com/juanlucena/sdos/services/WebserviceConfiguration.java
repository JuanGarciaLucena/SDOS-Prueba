package com.juanlucena.sdos.services;

import com.juanlucena.sdos.model.WebserviceObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface WebserviceConfiguration {

    @GET("hma6-9xbg.json?category=Fruit&item=Peaches")
    Call<List<WebserviceObject>> callRequestItems();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://data.ct.gov/resource/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
