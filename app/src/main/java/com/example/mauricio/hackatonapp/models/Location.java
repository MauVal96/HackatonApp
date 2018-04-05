package com.example.mauricio.hackatonapp.models;

public class Location {

    private double lng;
    private double lat;
    private double unc;
    private double x[];
    private double y[];

    public Location(double lng, double lat, double unc, double[] x, double[] y) {
        this.lng = lng;
        this.lat = lat;
        this.unc = unc;
        this.x = x;
        this.y = y;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getUnc() {
        return unc;
    }

    public void setUnc(double unc) {
        this.unc = unc;
    }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public double[] getY() {
        return y;
    }

    public void setY(double[] y) {
        this.y = y;
    }
}
