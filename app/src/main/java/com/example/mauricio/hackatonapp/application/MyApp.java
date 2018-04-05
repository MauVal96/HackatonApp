package com.example.mauricio.hackatonapp.application;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application {
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();


    }

    public static Retrofit getInstance(String URL){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
