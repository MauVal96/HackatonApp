package com.example.mauricio.hackatonapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mauricio.hackatonapp.api.LocalizacionApi;
import com.example.mauricio.hackatonapp.application.MyApp;
import com.example.mauricio.hackatonapp.models.Response;
import com.example.mauricio.hackatonapp.models.UpdateSet;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.example.mauricio.hackatonapp.R;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback{

    private GoogleMap gMap;
    private MapView mView;
    private ArrayList<Marker> markers;




    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_map, container, false);

        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view.findViewById(R.id.map);
        if (mView != null) {
            mView.onCreate(null);
            mView.onResume();
            mView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng expoGDL = new LatLng(20.652917, -103.391745);
        this.gMap = googleMap;
        gMap.addMarker(new MarkerOptions().position(expoGDL).title("Expo Guadalajara"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(expoGDL));
        gMap.setMinZoomPreference(10);



        CameraPosition camera= new CameraPosition.Builder()
                .target(expoGDL)
                .zoom(17)
                .bearing(0)
                .tilt(30)
                .build();
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));

    }


}




