package com.example.andaluciaapp.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.andaluciaapp.Fragments.AnimacionesFragment;
import com.example.andaluciaapp.Fragments.PersonajeFragment;
import com.example.andaluciaapp.Fragments.SonidosFragment;
import com.example.andaluciaapp.Fragments.VideoFragment;
import com.example.andaluciaapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PersonajeFragment()).commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_personajes) selectedFragment = new PersonajeFragment();
            else if (itemId == R.id.nav_videos) selectedFragment = new VideoFragment();
            else if (itemId == R.id.nav_sonidos) selectedFragment = new SonidosFragment();
            else if (itemId == R.id.nav_animaciones) selectedFragment = new AnimacionesFragment();

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        });
    }
}