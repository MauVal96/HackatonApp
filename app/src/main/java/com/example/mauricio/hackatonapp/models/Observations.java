package com.example.mauricio.hackatonapp.models;

public class Observations {

    private String os;
    private String ssid;
    private String manufacturer;
    private double seenEpoch;
    private Location location;
    private String ipv6;
    private double rssi;
    private String clientMac;
    private String seenTime;
    private String ipv4;

    public Observations(String os, String ssid, String manufacturer, double seenEpoch, Location location, String ipv6, double rssi, String clientMac, String seenTime, String ipv4) {
        this.os = os;
        this.ssid = ssid;
        this.manufacturer = manufacturer;
        this.seenEpoch = seenEpoch;
        this.location = location;
        this.ipv6 = ipv6;
        this.rssi = rssi;
        this.clientMac = clientMac;
        this.seenTime = seenTime;
        this.ipv4 = ipv4;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getSeenEpoch() {
        return seenEpoch;
    }

    public void setSeenEpoch(double seenEpoch) {
        this.seenEpoch = seenEpoch;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public double getRssi() {
        return rssi;
    }

    public void setRssi(double rssi) {
        this.rssi = rssi;
    }

    public String getClientMac() {
        return clientMac;
    }

    public void setClientMac(String clientMac) {
        this.clientMac = clientMac;
    }

    public String getSeenTime() {
        return seenTime;
    }

    public void setSeenTime(String seenTime) {
        this.seenTime = seenTime;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }
}
