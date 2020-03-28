package com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.adapter.DonationAdapter;
import com.osamaelsh3rawy.bloodbank3.adapter.SpinnerAdapter;
import com.osamaelsh3rawy.bloodbank3.data.api.ApiServies;
import com.osamaelsh3rawy.bloodbank3.data.local.SharedPreferencesManger;
import com.osamaelsh3rawy.bloodbank3.data.model.donation.Donation;
import com.osamaelsh3rawy.bloodbank3.data.model.donation.DonationData;
import com.osamaelsh3rawy.bloodbank3.helper.OnEndLess;
import com.osamaelsh3rawy.bloodbank3.view.activity.BaseActivity;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.osamaelsh3rawy.bloodbank3.data.api.ApiClient.getClient;
import static com.osamaelsh3rawy.bloodbank3.data.local.constance.User_Api_Takin;
import static com.osamaelsh3rawy.bloodbank3.helper.GeneralRequest.getData;
import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.replaceFragment;


public class HomeDonationFragment extends BaseFragment {

    ArrayList<DonationData> listDonationData = new ArrayList<>();
    @BindView(R.id.recycler_donation)
    RecyclerView recyclerDonation;
    @BindView(R.id.sp_blood_type_donation)
    Spinner spDonationBloodType;
    @BindView(R.id.sp_blood_type_donation_govern)
    Spinner spDonationGovern;
    private ApiServies apiServies;
    SpinnerAdapter bloodAdapter, governAdapter;
    private int MaxPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_donation, container, false);
        ButterKnife.bind(this, view);
        apiServies = getClient().create(ApiServies.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerDonation.setLayoutManager(linearLayoutManager);

        DonationAdapter donationAdapter = new DonationAdapter(getActivity(), (BaseActivity) getActivity(), listDonationData);

        String tokin= SharedPreferencesManger.LoadData(getActivity(),User_Api_Takin);
        apiServies.getDonationReq(tokin, 1).enqueue(new Callback<Donation>() {
            @Override
            public void onResponse(Call<Donation> call, Response<Donation> response) {
                if (response.body().getStatus() == 1) {
                    listDonationData.addAll(response.body().getData().getData());
                    DonationAdapter donationAdapter = new DonationAdapter(getActivity(), (BaseActivity) getActivity(), listDonationData);
                    recyclerDonation.setAdapter(donationAdapter);
                }
            }
            @Override
            public void onFailure(Call<Donation> call, Throwable t) {
            }
        });

        bloodAdapter = new SpinnerAdapter(getActivity());
        getData(apiServies.getBloodType(), bloodAdapter, "blood type",spDonationBloodType);

        governAdapter = new SpinnerAdapter(getActivity());
        AdapterView.OnItemSelectedListener listener=new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                apiServies.donationFilter(tokin, bloodAdapter.selectedId, governAdapter.selectedId, 1).enqueue(new Callback<Donation>() {
                    @Override
                    public void onResponse(Call<Donation> call, Response<Donation> response) {
                        if (response.body().getStatus() == 1) {
                            listDonationData.clear();
                            listDonationData.addAll(response.body().getData().getData());
                            donationAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Donation> call, Throwable t) {

                    }
                });
            }@Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        getData(apiServies.getGovernorates(), governAdapter, " government",spDonationGovern,listener);
        return view;
    }

    @OnClick(R.id.fragment_home_btn_donation_add)
    public void onViewClicked() {
        replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_home_container, new AddDonationFragment());
    }
}

