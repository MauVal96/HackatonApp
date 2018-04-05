package com.example.mauricio.hackatonapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mauricio.hackatonapp.R;
import com.example.mauricio.hackatonapp.api.LocalizacionApi;
import com.example.mauricio.hackatonapp.application.MyApp;
import com.example.mauricio.hackatonapp.fragments.AcercaDeFragment;
import com.example.mauricio.hackatonapp.fragments.HistorialFragment;
import com.example.mauricio.hackatonapp.fragments.MapFragment;
import com.example.mauricio.hackatonapp.fragments.PerfilFragment;
import com.example.mauricio.hackatonapp.fragments.TrendingFragment;
import com.example.mauricio.hackatonapp.interfaces.Comunicator;
import com.example.mauricio.hackatonapp.models.UpdateSet;

import java.util.ArrayList;
import com.example.mauricio.hackatonapp.models.Response;
import com.google.android.gms.maps.model.Marker;

import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MapFragment fragment = new MapFragment();
    private Fragment fragmentVol;
    private SwipeRefreshLayout refreshLayout;
    private Retrofit retrofit;
    private LocalizacionApi api;
    private ArrayList<UpdateSet> updateSets;

    public ArrayList<UpdateSet> getUpdateSets() {
        return updateSets;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        refreshLayout = findViewById(R.id.swiperefresh);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://merakiton.ddns.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(LocalizacionApi.class);

        update();
        setToolBar();
        setDefaultFragment();


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                update();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction = false;
                int select;
                switch (item.getItemId()) {
                    case R.id.menu_main:
                        selectFragment(fragment,item);
                        fragmentTransaction = false;
                        break;
                    case R.id.proyecto_1:
                        fragmentVol = new TrendingFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.integrantes:
                        fragmentVol = new HistorialFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.acerca_de:
                        fragmentVol = new AcercaDeFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.perfil:
                    fragmentVol = new PerfilFragment();
                    fragmentTransaction = true;
                    break;

                }
                if (fragmentTransaction) {
                    selectFragment(fragmentVol,item);
                }
                return true;
            }
        });




    }




    private void setDefaultFragment() {
        MenuItem item = navigationView.getMenu().getItem(Menu.FIRST);
        selectFragment(fragment,item);
        item.setChecked(true);
    }

    //Manda un valor para seleccionar Fragments en SecondActivity
    private void intentSecondActivity(int s) {
        Intent intent = new Intent(this, SecondActivity.class);
        switch (s) {
            case 0:
                intent.putExtra("selector", s);
                startActivity(intent);
                break;
        }
    }
    //Asigna la toolbar
    public void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menú principal");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void selectFragment(Fragment fragment, MenuItem item){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
        drawerLayout.closeDrawers();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void instanceRetrofit() {

    }

    private void update() {
        Call<Response<ArrayList<UpdateSet>>> call = api.getResponse("be7a413e-2eff-4b87-a0f0-1c51d8e84357");
        call.enqueue(new Callback<Response<ArrayList<UpdateSet>>>() {
            @Override
            public void onResponse(Call<Response<ArrayList<UpdateSet>>> call, retrofit2.Response<Response<ArrayList<UpdateSet>>> response) {
                updateSets = response.body().update_set;
                fragment.setUpdateSets(updateSets);
                Toast.makeText(MainActivity.this,"Localizacion de dispositivos realizada",Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<Response<ArrayList<UpdateSet>>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error en la conexión",Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);


            }
        });
    }




}
