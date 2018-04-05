package com.example.mauricio.hackatonapp.models;

import java.util.List;

public class UpdateSet {

    private double update_epoch;
    private List<Observations> observations;
    private String apTags[];
    private String apMac;
    private String apFloors[];

    public UpdateSet(double update_epoch, List<Observations> observations, String[] apTags, String apMac, String[] apFloors) {
        this.update_epoch = update_epoch;
        this.observations = observations;
        this.apTags = apTags;
        this.apMac = apMac;
        this.apFloors = apFloors;
    }

    public double getUpdate_epoch() {
        return update_epoch;
    }

    public void setUpdate_epoch(double update_epoch) {
        this.update_epoch = update_epoch;
    }

    public List<Observations> getObservations() {
        return observations;
    }

    public void setObservations(List<Observations> observations) {
        this.observations = observations;
    }

    public String[] getApTags() {
        return apTags;
    }

    public void setApTags(String[] apTags) {
        this.apTags = apTags;
    }

    public String getApMac() {
        return apMac;
    }

    public void setApMac(String apMac) {
        this.apMac = apMac;
    }

    public String[] getApFloors() {
        return apFloors;
    }

    public void setApFloors(String[] apFloors) {
        this.apFloors = apFloors;
    }
}
