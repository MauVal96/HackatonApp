package com.example.mauricio.hackatonapp.api;

import com.example.mauricio.hackatonapp.models.Response;
import com.example.mauricio.hackatonapp.models.UpdateSet;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocalizacionApi {

    @GET("data/{token}")
    Call<Response<ArrayList<UpdateSet>>> getResponse(@Path("token") String token );
}
