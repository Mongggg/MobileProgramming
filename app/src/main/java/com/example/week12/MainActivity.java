package com.example.week12;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    HomeFragment homeFragment;
    LoginFragment2 loginFragment;
    RegisterFragment RegisterFragment;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = (DrawerLayout) findViewById(R.id.main_drawer);
        getSupportActionBar().setDisplayShowTitleEnabled(false);  //제목이안보이게
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //홈버튼활성화
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.d_open, R.string.d_close);
        toggle.syncState();
        homeFragment = new HomeFragment();
        loginFragment = new LoginFragment2();
        RegisterFragment = new RegisterFragment();
        fm = getSupportFragmentManager();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_home) {
                    drawer.closeDrawers();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.addToBackStack(null);
                    ft.replace(R.id.container, homeFragment);
                    ft.commit();
                    Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_SHORT).show();
                }
                if (id == R.id.menu_menu1) {
                    drawer.closeDrawers();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.addToBackStack(null);
                    ft.replace(R.id.container, loginFragment);
                    ft.commit();
                    Toast.makeText(getApplicationContext(), "Menu1", Toast.LENGTH_SHORT).show();
                }
                if (id == R.id.menu_menu2)
                    Toast.makeText(getApplicationContext(), "Menu2", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return false;
        return super.onOptionsItemSelected(item);
    }
}