package com.example.mauricio.hackatonapp.models;

import java.util.List;

public class Response <T>{

    private double last_update;
    private T update_set;

    public T getUpdate_set() {
        return update_set;
    }

    public void setUpdate_set(T update_set) {
        this.update_set = update_set;
    }

    public Response(double last_update, T update_set) {
        this.last_update = last_update;
        this.update_set = update_set;
    }

    public double getLast_update() {
        return last_update;
    }

    public void setLast_update(double last_update) {
        this.last_update = last_update;
    }




}
