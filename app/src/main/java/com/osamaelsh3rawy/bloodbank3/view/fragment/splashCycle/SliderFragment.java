package com.osamaelsh3rawy.bloodbank3.view.fragment.splashCycle;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.adapter.SliderAdapter;
import com.osamaelsh3rawy.bloodbank3.view.activity.LoginCycleActivity;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SliderFragment extends BaseFragment {


    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.linear_dots)
    LinearLayout linearDots;
    @BindView(R.id.fragment_slider_img)
    ImageView fragmentSliderImg;
    public SliderAdapter adapter;
    public TextView[] mDots;
    int current_page;

    public SliderFragment() {
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
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        ButterKnife.bind(this, view);


        adapter = new SliderAdapter(getActivity());
        viewPager.setAdapter(adapter);
        addDotsIndecator(0);
        return view;
    }

    public void addDotsIndecator(int position) {
        mDots = new TextView[3];
        for (int page = 0; page < mDots.length; page++) {

            mDots[page] = new TextView(getActivity());
            mDots[page].setText(Html.fromHtml("&#8226"));
            mDots[page].setTextSize(60);
            mDots[page].setTextColor(getResources().getColor(R.color.colorSlider));


            linearDots.addView(mDots[page]);
        }
        if (mDots.length > 0)
            mDots[position].setTextColor(getResources().getColor(R.color.colorSliderSelected));
    }

    ViewPager.OnPageChangeListener Slider_view = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {


            if (position == 0 || position == 1) {
                fragmentSliderImg.setEnabled(true);

                fragmentSliderImg.setImageResource(R.drawable.ic_arrow);

            } else {
                fragmentSliderImg.setEnabled(true);

                fragmentSliderImg.setImageResource(R.drawable.ic_done_slider);
            }

            addDotsIndecator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }

    @OnClick(R.id.fragment_slider_img)
    public void onViewClicked() {
        if (viewPager.getCurrentItem() == 0) {
            viewPager.setCurrentItem(1);
        }
        else if (viewPager.getCurrentItem() == 1) {
            viewPager.setCurrentItem(2);
            fragmentSliderImg.setImageResource(R.drawable.ic_done_slider);
        }
        else {
            getActivity().finish();
        }
        Intent intent = new Intent(getActivity(), LoginCycleActivity.class);
        getActivity().startActivity(intent);


    }
}

