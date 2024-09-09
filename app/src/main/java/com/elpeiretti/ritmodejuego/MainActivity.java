package com.elpeiretti.ritmodejuego;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.elpeiretti.ritmodejuego.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding viewBinding = ActivityMainBinding.inflate(getLayoutInflater());

        NavController navController = NavHostFragment.findNavController(viewBinding.navHostFragment.getFragment());
        NavigationUI.setupWithNavController(viewBinding.toolbar, navController,
                new AppBarConfiguration.Builder(navController.getGraph()).build());

        setContentView(viewBinding.getRoot());
    }
}