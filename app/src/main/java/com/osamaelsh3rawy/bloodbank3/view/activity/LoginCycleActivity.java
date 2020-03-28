package com.osamaelsh3rawy.bloodbank3.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.view.fragment.loginCycle.LoginFragment;
import com.osamaelsh3rawy.bloodbank3.view.fragment.splashCycle.SplashFragment;

import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.replaceFragment;

public class LoginCycleActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cycle);

        replaceFragment(getSupportFragmentManager(), R.id.activity_login_container, new LoginFragment());
    }
}
