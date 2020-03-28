package com.osamaelsh3rawy.bloodbank3.view.fragment.splashCycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.data.local.SharedPreferencesManger;
import com.osamaelsh3rawy.bloodbank3.view.activity.HomeCycleActivity;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import java.util.Timer;
import java.util.TimerTask;

import static com.osamaelsh3rawy.bloodbank3.data.local.SharedPreferencesManger.loadUserData;
import static com.osamaelsh3rawy.bloodbank3.data.local.constance.User_Api_Takin;
import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.replaceFragment;


public class SplashFragment extends BaseFragment {

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        initFragment();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPreferencesManger.LoadData(getActivity(), User_Api_Takin) != null) {
                        Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    } else{
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_splash_container, new SliderFragment());
            }}
        }, 1000);

        return view;

    }


    @Override
    public void onBack() {
        getActivity().finish();
    }

}



