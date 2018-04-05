package com.example.mauricio.hackatonapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.mauricio.hackatonapp.R;
import com.example.mauricio.hackatonapp.api.LocalizacionApi;
import com.example.mauricio.hackatonapp.application.MyApp;
import com.example.mauricio.hackatonapp.fragments.MapFragment;
import com.example.mauricio.hackatonapp.models.UpdateSet;

import java.util.ArrayList;
import com.example.mauricio.hackatonapp.models.Response;

import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Fragment fragment;
    private Retrofit retrofit;
    private LocalizacionApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        //instanceRetrofit();
        //update();
        setToolBar();
        setDefaultFragment();

    }

    private void update() {
        Call<Response<ArrayList<UpdateSet>>> call = api.getResponse("be7a413e-2eff-4b87-a0f0-1c51d8e84357");
        call.enqueue(new Callback<Response<ArrayList<UpdateSet>>>() {
            @Override
            public void onResponse(Call<Response<ArrayList<UpdateSet>>> call, retrofit2.Response<Response<ArrayList<UpdateSet>>> response) {
                ArrayList <UpdateSet> updateSets = response.body().getUpdate_set();

                System.err.println(response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<Response<ArrayList<UpdateSet>>> call, Throwable t) {

            }
        });
    }

    private void instanceRetrofit() {
        this.retrofit = MyApp.getInstance("http://merakiton.ddns.net/data/");
        this.api = retrofit.create(LocalizacionApi.class);

    }


    private void setDefaultFragment() {
        MenuItem item = navigationView.getMenu().getItem(Menu.FIRST);
        selectFragment(new MapFragment(),item);
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

}
