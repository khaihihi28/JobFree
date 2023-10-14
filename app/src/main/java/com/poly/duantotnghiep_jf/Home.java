package com.poly.duantotnghiep_jf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.poly.duantotnghiep_jf.Adapter.ViewPagerAdapter;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager pager;
    BottomNavigationView bot;
    DrawerLayout drawer;
//    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bot = findViewById(R.id.bot);
//        toolbar = findViewById(R.id.tool);
//        drawer = findViewById(R.id.drawer);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,  R.string.nav_drawer_open, R.string.nav_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
        pager = findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bot.getMenu().findItem(R.id.trangchu).setChecked(true);
                        break;
                    case 1:
                        bot.getMenu().findItem(R.id.yeuthich).setChecked(true);
                        break;
                    case 2:
                        bot.getMenu().findItem(R.id.khampha).setChecked(true);
                        break;
                    case 3:
                        bot.getMenu().findItem(R.id.menu).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bot.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.trangchu:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.yeuthich:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.khampha:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.menu:
                        pager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolber, menu);
        return super.onCreateOptionsMenu(menu);
    }
//
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}