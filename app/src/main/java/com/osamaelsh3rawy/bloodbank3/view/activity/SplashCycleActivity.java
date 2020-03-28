package com.osamaelsh3rawy.bloodbank3.view.activity;

import android.os.Bundle;
import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.view.fragment.splashCycle.SplashFragment;

import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.replaceFragment;

public class SplashCycleActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_cycle);

        replaceFragment(getSupportFragmentManager(), R.id.activity_splash_container, new SplashFragment());
    }

}