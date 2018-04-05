package com.example.mauricio.hackatonapp.application;

import android.app.Application;

import com.example.mauricio.hackatonapp.models.UpdateSet;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application {
    private Retrofit retrofit;

    private static ArrayList<UpdateSet> updateSets;

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

    public static void guardarResponse(ArrayList<UpdateSet> ud){
        updateSets = ud;
    }
    public static ArrayList<UpdateSet> getResponse(){
        return updateSets;
    }
}
