package com.fjmg.worldbuilding.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavType;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.fjmg.worldbuilding.R;
import com.fjmg.worldbuilding.data.model.User;
import com.fjmg.worldbuilding.databinding.ActivityMainBinding;
import com.fjmg.worldbuilding.ui.login.LoginActivity;
import com.fjmg.worldbuilding.utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private NavController controller;
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toogle;
    private static Bundle dataUser = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inicializamos el binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //Inicializacion de firebase
        FirebaseApp.initializeApp(this);
        //Carga el layout
        setContentView(binding.getRoot());
        //Carga la imagen por defecto de los universos sino tinen imagen
        Utils.IMAGEN_DEFAULT = getString(R.string.imgBase64Default);
        //Carga un nuevo graph con un parametro por defecto que es la UID del usuario
        Bundle dataUser  = new Bundle();
        dataUser.putString(User.UID,getIntent().getStringExtra(User.UID));
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navHostFragment.getNavController().setGraph(R.navigation.nav_graph,dataUser);
        controller = navHostFragment.getNavController();
        //Creamos una variable de si es ivisible o no la actionBar y la asignamos
         toogle = new ActionBarDrawerToggle(
                                                             this,
                                                                    binding.getRoot(),
                                                                    R.string.nav_bar_open,
                                                                    R.string.nav_bar_close
                                                                );
        binding.getRoot().addDrawerListener(toogle);
        toogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Cuando seleccione un item
        binding.navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.universosFragment:
                        controller.navigate(R.id.universosFragment,dataUser);
                        break;
                    case R.id.categoriaFragment:
                        controller.navigate(R.id.categoriaFragment,dataUser);
                        break;
                }

                return false;
            }
        });
        Set<Integer> topLevelDestionation = new HashSet<>();
        //Primer nivel sin flecha
        topLevelDestionation.add(R.id.mainFragment);
        topLevelDestionation.add(R.id.preferenceFragment);
        //Controlador del navegador
        NavigationUI.setupWithNavController(binding.navigation,controller);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestionation).setOpenableLayout(binding.getRoot()).build();
        NavigationUI.setupActionBarWithNavController(this,controller,appBarConfiguration);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (toogle.onOptionsItemSelected(item))
       {
           return true;
       }
       return super.onOptionsItemSelected(item);
    }
    public Bundle changeData(Bundle bundle)
    {
        dataUser = bundle;
        return dataUser;
    }
    public Bundle getBundle()
    {
        return dataUser;
    }
}