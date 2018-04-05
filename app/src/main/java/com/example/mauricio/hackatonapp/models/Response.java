package com.example.mauricio.hackatonapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response <T>{
    @SerializedName("last_update")
    public double last_update;
    @SerializedName("update_set")
    public T update_set;





}
