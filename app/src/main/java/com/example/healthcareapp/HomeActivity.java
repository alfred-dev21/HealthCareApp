package com.example.healthcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.healthcareapp.menu.About;
import com.example.healthcareapp.menu.Home;
import com.example.healthcareapp.menu.Profile;
import com.example.healthcareapp.menu.Settings;
import com.example.healthcareapp.menu.TermsAndConditions;
import com.example.healthcareapp.menu.Share;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_nav,
                R.string.close_nav
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new Home())
                    .commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_menuItem:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new Home())
                        .commit();
                break;
            case R.id.profile_menuItem:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new Profile())
                        .commit();
                break;
            case R.id.share_menuItem:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new Share())
                        .commit();
                break;
            case R.id.setting_menuItem:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new Settings())
                        .commit();
                break;
            case R.id.t_and_c:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new TermsAndConditions())
                        .commit();
                break;
            case R.id.about_menuItem:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new About())
                        .commit();
                break;
            case R.id.logout_menuItem:
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                return false;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}