package com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.adapter.SpinnerAdapter;
import com.osamaelsh3rawy.bloodbank3.data.api.ApiServies;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.osamaelsh3rawy.bloodbank3.data.api.ApiClient.getClient;
import static com.osamaelsh3rawy.bloodbank3.helper.GeneralRequest.getData;
import static com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle.MapsActivity.addressString;


public class AddDonationFragment extends BaseFragment {

    SpinnerAdapter bloodAdapter, governAdpter, cityAdapter;
    @BindView(R.id.sp_blood_type_new_account)
    Spinner spBloodTypeNewAccount;
    @BindView(R.id.sp_governmen_new_account)
    Spinner spGovernmenNewAccount;
    @BindView(R.id.sp_city_new_account)
    Spinner spCityNewAccount;
    @BindView(R.id.btn_hospital_location)
    TextView btnHospitalLocation;


    public AddDonationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home_add_donation, container, false);
        ButterKnife.bind(this, view);
        initFragment();
        ApiServies apiServies = getClient().create(ApiServies.class);


        bloodAdapter = new SpinnerAdapter(getActivity());
        getData(apiServies.getBloodType(), bloodAdapter, "Blood Type", spBloodTypeNewAccount);

        governAdpter = new SpinnerAdapter(getActivity());
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {

                    cityAdapter = new SpinnerAdapter(getActivity());

                    getData(apiServies.getcity(getId()), cityAdapter, "CITY", spCityNewAccount);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        getData(apiServies.getGovernorates(), governAdpter, " Governates", spGovernmenNewAccount, listener);


        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBack() {

        baseActivity.onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (addressString != null) {
            btnHospitalLocation.setText(addressString);

        }
    }

    @OnClick({R.id.btn_hospital_location, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_hospital_location:
                startActivity(new Intent(getActivity(), MapsActivity.class));
                break;
//            case R.id.btn_confirm:
//
//                break;
        }
    }
}

