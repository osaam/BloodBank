package com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.adapter.SpinnerAdapter;
import com.osamaelsh3rawy.bloodbank3.data.api.ApiServies;
import com.osamaelsh3rawy.bloodbank3.data.local.SharedPreferencesManger;
import com.osamaelsh3rawy.bloodbank3.data.model.register.Register;
import com.osamaelsh3rawy.bloodbank3.helper.DateModel;
import com.osamaelsh3rawy.bloodbank3.helper.HelperMethod;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.osamaelsh3rawy.bloodbank3.data.api.ApiClient.getClient;
import static com.osamaelsh3rawy.bloodbank3.data.local.SharedPreferencesManger.SaveData;
import static com.osamaelsh3rawy.bloodbank3.data.local.constance.User_Api_Takin;
import static com.osamaelsh3rawy.bloodbank3.data.local.constance.User_Birth;
import static com.osamaelsh3rawy.bloodbank3.data.local.constance.User_Blood;
import static com.osamaelsh3rawy.bloodbank3.data.local.constance.User_City;
import static com.osamaelsh3rawy.bloodbank3.data.local.constance.User_Email;
import static com.osamaelsh3rawy.bloodbank3.data.local.constance.User_Last;
import static com.osamaelsh3rawy.bloodbank3.data.local.constance.User_Name;
import static com.osamaelsh3rawy.bloodbank3.data.local.constance.User_Phone;
import static com.osamaelsh3rawy.bloodbank3.helper.GeneralRequest.getData;


public class MyAccountFragment extends BaseFragment {

    SpinnerAdapter bloodAdapter, governAdpter, cityAdapter;
    @BindView(R.id.sp_blood_type_new_account)
    Spinner spBloodTypeNewAccount;
    @BindView(R.id.sp_governmen_new_account)
    Spinner spGovernmenNewAccount;
    @BindView(R.id.sp_city_new_account)
    Spinner spCityNewAccount;
    @BindView(R.id.et_name_new_account)
    EditText etNameNewAccount;
    @BindView(R.id.et_mail_new_account)
    EditText etMailNewAccount;
    @BindView(R.id.et_birth_new_account)
    EditText etBirthNewAccount;
    @BindView(R.id.et_last_donation_date_new_account)
    EditText etLastDonationDateNewAccount;
    @BindView(R.id.et_phone_new_account)
    EditText etPhoneNewAccount;
    @BindView(R.id.et_pass_new_account)
    EditText etPassNewAccount;
    @BindView(R.id.et_confirm_pass_new_account)
    EditText etConfirmPassNewAccount;

    DateModel dateModel;

    public MyAccountFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home_my_account, container, false);
        ButterKnife.bind(this, view);
        initFragment();
        ApiServies apiServies = getClient().create(ApiServies.class);
        setData();

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

    public void setData() {
        etNameNewAccount.setText(SharedPreferencesManger.LoadData(getActivity(), User_Name));
        etMailNewAccount.setText(SharedPreferencesManger.LoadData(getActivity(), User_Email));
        etPhoneNewAccount.setText(SharedPreferencesManger.LoadData(getActivity(), User_Phone));
        etBirthNewAccount.setText(SharedPreferencesManger.LoadData(getActivity(), User_Birth));
        etLastDonationDateNewAccount.setText(SharedPreferencesManger.LoadData(getActivity(), User_Last));
    }

    private void onCall() {
        ApiServies apiServies = getClient().create(ApiServies.class);

        String name = etNameNewAccount.getText().toString();
        String email = etMailNewAccount.getText().toString();
        String phone = etPhoneNewAccount.getText().toString();
        String password = etPassNewAccount.getText().toString();
        String passwordConfirmation = etConfirmPassNewAccount.getText().toString();

        String birth_date = etBirthNewAccount.getText().toString();
        String donationLastDate = etLastDonationDateNewAccount.getText().toString();

        int cityId = cityAdapter.selectedId;
        int bloodTypeId = bloodAdapter.selectedId;
        String tokin = SharedPreferencesManger.LoadData(getActivity(), User_Api_Takin);

        apiServies.editClientData(name, email, birth_date, cityId, phone, donationLastDate, password, passwordConfirmation, bloodTypeId, tokin).enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.body().getStatus() == 1) {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                    SaveData(getActivity(), User_Api_Takin, response.body().getData().getApiToken());
                    SaveData(getActivity(), User_Name, response.body().getData().getClient().getName());
                    SaveData(getActivity(), User_Email, response.body().getData().getClient().getEmail());
                    SaveData(getActivity(), User_City, response.body().getData().getClient().getCity());
                    SaveData(getActivity(), User_Phone, response.body().getData().getClient().getPhone());
                    SaveData(getActivity(), User_Blood, response.body().getData().getClient().getBloodType());
                    SaveData(getActivity(), User_Birth, response.body().getData().getClient().getBirthDate());
                    SaveData(getActivity(), User_Last, response.body().getData().getClient().getDonationLastDate());

                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBack() {

        super.onBack();
    }


    @OnClick({R.id.et_last_donation_date_new_account, R.id.btn_confirm, R.id.et_birth_new_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_last_donation_date_new_account:
                dateModel = new DateModel("02", "02", "2020", "02-02-2020");
                HelperMethod.showCalender(getActivity(), "choose Date", etLastDonationDateNewAccount, dateModel);
                break;
            case R.id.et_birth_new_account:
                dateModel = new DateModel("02", "02", "2020", "02-02-2020");
                HelperMethod.showCalender(getActivity(), "choose Date", etBirthNewAccount, dateModel);
                break;
            case R.id.btn_confirm:
                onCall();
                break;
        }
    }
}

