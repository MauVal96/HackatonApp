package com.example.mauricio.hackatonapp.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mauricio.hackatonapp.api.LocalizacionApi;
import com.example.mauricio.hackatonapp.application.MyApp;
import com.example.mauricio.hackatonapp.controllers.Triangulation;
import com.example.mauricio.hackatonapp.interfaces.Comunicator;
import com.example.mauricio.hackatonapp.models.Location;
import com.example.mauricio.hackatonapp.models.Response;
import com.example.mauricio.hackatonapp.models.UpdateSet;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.example.mauricio.hackatonapp.R;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback,View.OnClickListener{

    private GoogleMap gMap;
    private MapView mView;
    private ArrayList<MarkerOptions> markers = new ArrayList<>();
    private FloatingActionButton fab;
    private LatLng expoGDL;
    private ArrayList<UpdateSet> updateSets;
    private AppCompatActivity activity;

    public void setUpdateSets(ArrayList<UpdateSet> updateSets) {
        this.updateSets = updateSets;
        desplegarPuntos();
    }

    private void desplegarPuntos() {
        ArrayList<Location> locations = Triangulation.ubiMeraki(updateSets);

        for (int i = 0;i<locations.size();i++){
            gMap.addMarker(new MarkerOptions().position(new LatLng(locations.get(i).getLat(),locations.get(i).getLng()))
                    .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_on)));
        }

    }

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
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(this);

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
        checkIfGPSIsEnabled();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        expoGDL = new LatLng(20.652917, -103.391745);
        this.gMap = googleMap;
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

    private void checkIfGPSIsEnabled(){
        try {
            int gpsSignal = Settings.Secure.getInt(getActivity().getContentResolver(),Settings.Secure.LOCATION_MODE);
            if (gpsSignal==0){
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        crearAlertDialog();
    }

    private void crearAlertDialog() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.alert_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(v);
        builder.setTitle("Insertar Marcadores");
        builder.setMessage("Introduce los siguientes datos ");

        EditText nombreEdt = v.findViewById(R.id.nom_edt);
        final String nombre = nombreEdt.getText().toString();
        EditText descEdt = v.findViewById(R.id.des_edt);
        final String desc = descEdt.getText().toString();


        builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MarkerOptions markerAux = new MarkerOptions();
                markerAux.position(expoGDL);
                markerAux.title(nombre);
                markerAux.snippet(desc);
                markerAux.draggable(true);
                updateMarkers(markerAux);
                alertDialog.dismiss();

            }
        });


    }

    private void updateMarkers(MarkerOptions m) {
        gMap.addMarker(m);
    }

}




