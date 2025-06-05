package com.example.week12;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity3 extends AppCompatActivity {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    HomeFragment homeFragment;
    LoginFragment3 loginFragment;
    RegisterFragment RegisterFragment;////// LoginFragment3으로 변경
    FragmentManager fm;

    /////// MainActivity에 3행추가
    View navHeader; //네비게이션 드로우어 헤더
    SharedPreferences preferences;
    TextView tvUname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        drawer = (DrawerLayout) findViewById(R.id.main_drawer);

        getSupportActionBar().setDisplayShowTitleEnabled(false);  //제목이 안 보이게
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //홈버튼 활성화

        toggle = new ActionBarDrawerToggle(this,drawer, R.string.d_open, R.string.d_close );
        toggle.syncState();

        homeFragment = new HomeFragment();
        loginFragment = new LoginFragment3();  //LoginFragment3으로 변경
        RegisterFragment = new RegisterFragment();
        fm = getSupportFragmentManager();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        //NavigationView Header
        navHeader = navigationView.getHeaderView(0);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.menu_home){
                    drawer.closeDrawers();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.addToBackStack(null);
                    ft.replace(R.id.container, homeFragment);
                    ft.commit();
                    Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_SHORT).show();
                }

                if(id == R.id.menu_menu1) {
                    drawer.closeDrawers();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.addToBackStack(null);
                    ft.replace(R.id.container, loginFragment);
                    ft.commit();
                    Toast.makeText(getApplicationContext(), "Menu1", Toast.LENGTH_SHORT).show();
                }

                if(id == R.id.menu_menu2) {
                    drawer.closeDrawers();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.addToBackStack(null);
                    ft.replace(R.id.container, RegisterFragment);
                    ft.commit();
                    Toast.makeText(getApplicationContext(), "Menu2", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ////// MainActivity3 code : SharedPreferences 반영
        if(toggle.onOptionsItemSelected(item)){  //navigation toggle
            //SharedPreferences
            preferences = getApplicationContext().getSharedPreferences("userInfo", MODE_PRIVATE);
            String uName = preferences.getString("name", "");
            //NavigationDrawer Header의 텍스트 값 변경
            tvUname = (TextView) navHeader.findViewById(R.id.u_name);
            tvUname.setText(uName+"님");
            return false;
        }
        ////// MainActivity code
        /*  if(toggle.onOptionsItemSelected(item))
            return false;*/
        return super.onOptionsItemSelected(item);
    }
}