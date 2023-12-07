package com.poly.duantotnghiep_jf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.poly.duantotnghiep_jf.Activity.ProfileUser;
import com.poly.duantotnghiep_jf.Activity.TaikhoanJob;
import com.poly.duantotnghiep_jf.Adapter.ViewPagerAdapter;
import com.poly.duantotnghiep_jf.Fragment.HomeFragment;
import com.poly.duantotnghiep_jf.Fragment.KhamPhaFragment;
import com.poly.duantotnghiep_jf.Fragment.MenuFragment;
import com.poly.duantotnghiep_jf.Fragment.YeuThichFragment;
import com.poly.duantotnghiep_jf.Helper.AccountHelper;
import com.poly.duantotnghiep_jf.Helper.AuthHelper;
import com.poly.duantotnghiep_jf.Helper.FireBaseHelper;
import com.poly.duantotnghiep_jf.Model.Account;
import com.poly.duantotnghiep_jf.viewPager.CustomViewPager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Account account;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();


        setupBottomNavigationView();
        showInitialFragment();

        final FloatingSearchView mSearchView = findViewById(R.id.search_view);

        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

            }
        });

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,
                null,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);


        Menu menu = navigationView.getMenu();
        MenuItem unLockAccItem = menu.findItem(R.id.nav_unlock_acc);
        MenuItem companyManegeItem = menu.findItem(R.id.nav_company_manege);

        AccountHelper.checkIsManegeCompany(manegeCompany -> {
            if(manegeCompany){
                unLockAccItem.setVisible(false);
                companyManegeItem.setVisible(true);
            }
            else {
                unLockAccItem.setVisible(true);
                companyManegeItem.setVisible(false);
            }
        });





        AccountHelper.getAccountByUid(FireBaseHelper.getCurrentUserUid(), new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    account = snapshot.getValue(Account.class);

                    de.hdodenhof.circleimageview.CircleImageView profileImageView = navigationView.getHeaderView(0).findViewById(R.id.profile_image);
                    TextView profile_name = navigationView.getHeaderView(0).findViewById(R.id.profile_name);
                    TextView profile_email = navigationView.getHeaderView(0).findViewById(R.id.profile_email);
                    TextView tv_count_coin = navigationView.getHeaderView(0).findViewById(R.id.tv_count_coin);

                    profile_name.setText(account.getName().toString());
                    profile_email.setText(account.getEmail().toString());
                    tv_count_coin.setText(String.valueOf(account.getCoin()));
                    if(account.getAvatar().equals("R.drawable.profile_img_default")){
                        Glide.with(getBaseContext())
                                .load(R.drawable.profile_img_default)
                                .into(profileImageView);
                    }
                    else {
                        Glide.with(getBaseContext())
                                .load(account.getAvatar().toString())
                                .into(profileImageView);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        TextView btn_nap_coin = navigationView.getHeaderView(0).findViewById(R.id.btn_nap_coin);
        btn_nap_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        mSearchView.attachNavigationDrawerToMenuButton(drawer);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                Intent intentProfile = new Intent(MainActivity.this, ProfileUser.class);
                startActivity(intentProfile);
                break;
            case R.id.nav_make_cv:
                Toast.makeText(this, R.string.make_cv, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_unlock_acc:
                Toast.makeText(this, R.string.unlock_acc, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_company_manege:
                Toast.makeText(this, R.string.company_manege, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_change_pass:
                Toast.makeText(this, R.string.change_pass, Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                AuthHelper.signOutHelper();
                Intent intent = new Intent(MainActivity.this, TaikhoanJob.class);
                startActivity(intent);
                finish();
                Toast.makeText(this, R.string.logout, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        item.setCheckable(false);

        // Đóng Navigation Drawer sau khi xử lý sự kiện
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void bind(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void setupBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private void showInitialFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.bnav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.bnav_yeuThich:
                        selectedFragment = new YeuThichFragment();
                        break;
                    case R.id.bnav_khamPha:
                        selectedFragment = new KhamPhaFragment();
                        break;
                    case R.id.bnav_menu:
                        selectedFragment = new MenuFragment();
                        break;
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
                }

                return true;
            };



}