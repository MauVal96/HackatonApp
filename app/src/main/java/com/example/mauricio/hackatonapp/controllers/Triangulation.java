package com.example.mauricio.hackatonapp.controllers;

import com.example.mauricio.hackatonapp.models.Location;
import com.example.mauricio.hackatonapp.models.Observations;
import com.example.mauricio.hackatonapp.models.UpdateSet;

import java.util.ArrayList;

public class Triangulation {

    private static final double R = 6378.137;


    public static double dist(Location locA, Location locB)
    {
        double dLat = (locA.getLat() - locB.getLat())*Math.PI/180;
        double dLon = (locA.getLng() - locB.getLng())*Math.PI/180;

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(locA.getLat()*Math.PI/180)*Math.cos(locA.getLat()*Math.PI/180)*Math.sin(dLon/2)* Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        double d = R *c;

        return d;
    }

    public static ArrayList<Location> ubiMeraki(ArrayList<UpdateSet> accessP)
    {
        ArrayList<Observations> obsAux = new ArrayList<>();
        ArrayList<Location> uMer= new ArrayList<>();
        for (int i = 0; i < accessP.size(); i++)
        {
         obsAux = accessP.get(i).getObservations();
            for (int j = 0; j < obsAux.size(); j++) {
                uMer.add(obsAux.get(j).getLocation());

            }
        }
        return uMer;
    }


}
