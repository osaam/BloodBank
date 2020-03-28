package com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.data.local.SharedPreferencesManger;
import com.osamaelsh3rawy.bloodbank3.view.activity.SplashCycleActivity;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.osamaelsh3rawy.bloodbank3.data.local.constance.User_Api_Takin;
import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.replaceFragment;


public class MoreFragment extends BaseFragment {


    public MoreFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home_more, container, false);
        initFragment();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBack() {

        baseActivity.onBackPressed();    }

    @OnClick({R.id.fragment_more_txt_fav, R.id.fragment_more_txt_contact, R.id.fragment_more_txt_about, R.id.fragment_more_txt_rate, R.id.fragment_more_txt_notify, R.id.fragment_more_txt_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_more_txt_fav:
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_home_container, new MyFavouritesFragment());

                break;
            case R.id.fragment_more_txt_contact:
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_home_container, new ContactUsFragment());
                break;
            case R.id.fragment_more_txt_about:
                break;
            case R.id.fragment_more_txt_rate:
                break;
            case R.id.fragment_more_txt_notify:
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_home_container, new NotificationSettingFragment());
                break;
            case R.id.fragment_more_txt_out:
                if (SharedPreferencesManger.LoadData(getActivity(), User_Api_Takin) != (null)) {
                    Dialog dialog = new Dialog(getActivity());
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View v = inflater.inflate(R.layout.dialog_log_out, null);
                    dialog.setContentView(v);

                    Button button = (Button) dialog.findViewById(R.id.dialog_yes_btn);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), SplashCycleActivity.class);
                            getActivity().startActivity(intent);
                            getActivity().finish();
                            SharedPreferencesManger.clean(getActivity());
                            Toast.makeText(baseActivity, "You Have LogOut", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Button btn_done = (Button) dialog.findViewById(R.id.dialog_no_btn);
                    btn_done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }
                break;
        }
    }
}

