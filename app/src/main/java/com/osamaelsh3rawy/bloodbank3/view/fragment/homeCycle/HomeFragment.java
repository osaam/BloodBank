package com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.adapter.TabPagerAdapter;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_home)
    ViewPager viewPagerHome;
    private TabPagerAdapter tabPagerAdapter;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);


        viewPagerHome.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //tab
        tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager());
        tabPagerAdapter.addPager(new HomeArticelsFragment(), "ARTICLES");
        tabPagerAdapter.addPager(new HomeDonationFragment(), "DONATION");

        viewPagerHome.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPagerHome);
        return view;
    }

}
