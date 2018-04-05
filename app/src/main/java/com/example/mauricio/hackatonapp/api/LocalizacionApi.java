package com.example.mauricio.hackatonapp.api;

import com.example.mauricio.hackatonapp.models.Response;
import com.example.mauricio.hackatonapp.models.UpdateSet;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocalizacionApi {

    @GET("token/")
    Call<Response<ArrayList<UpdateSet>>> getResponse(@Path("token") String token);
}
