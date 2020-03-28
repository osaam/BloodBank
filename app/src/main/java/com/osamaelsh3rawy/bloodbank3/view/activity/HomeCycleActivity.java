package com.osamaelsh3rawy.bloodbank3.view.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle.HomeArticelsFragment;
import com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle.HomeFragment;
import com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle.MoreFragment;
import com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle.MyAccountFragment;
import com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle.NotificationFragment;

import butterknife.ButterKnife;

import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.replaceFragment;

public class HomeCycleActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cycle);

        replaceFragment(getSupportFragmentManager(), R.id.activity_home_container, new HomeFragment());

        BottomNavigationView btnView = findViewById(R.id.bottom_navigation_view);
        btnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        replaceFragment(getSupportFragmentManager(), R.id.activity_home_container, new HomeFragment());
                        break;
                    case R.id.nav_my_account:
                        replaceFragment(getSupportFragmentManager(), R.id.activity_home_container, new MyAccountFragment());
                        break;
                    case R.id.nav_notification:
                        replaceFragment(getSupportFragmentManager(), R.id.activity_home_container,new NotificationFragment());
                        break;
                    case R.id.nav_more:
                        replaceFragment(getSupportFragmentManager(), R.id.activity_home_container, new MoreFragment());
                        break;

                }
                return true;
            }
        });

    }


}