package com.osamaelsh3rawy.bloodbank3.view.fragment.loginCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.adapter.SpinnerAdapter;
import com.osamaelsh3rawy.bloodbank3.data.api.ApiServies;
import com.osamaelsh3rawy.bloodbank3.data.local.SharedPreferencesManger;
import com.osamaelsh3rawy.bloodbank3.data.model.register.Register;
import com.osamaelsh3rawy.bloodbank3.view.activity.HomeCycleActivity;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.osamaelsh3rawy.bloodbank3.data.api.ApiClient.getClient;
import static com.osamaelsh3rawy.bloodbank3.helper.GeneralRequest.getData;


public class NewAcountFragment extends BaseFragment {

    @BindView(R.id.sp_governmen_new_account)
    Spinner spGovernmenNewAccount;
    @BindView(R.id.sp_city_new_account)
    Spinner spCityNewAccount;
    @BindView(R.id.txt_creat_new_account)
    TextView txtCreatNewAccount;
    @BindView(R.id.et_name_new_account)
    EditText etNameNewAccount;
    @BindView(R.id.et_mail_new_account)
    EditText etMailNewAccount;
    @BindView(R.id.et_birth_new_account)
    EditText etBirthNewAccount;
    @BindView(R.id.sp_blood_type_new_account)
    Spinner spBloodTypeNewAccount;
    @BindView(R.id.et_blood_type_new_account)
    TextInputLayout etBloodTypeNewAccount;
    @BindView(R.id.et_last_donation_date_new_account)
    EditText etLastDonationDateNewAccount;
    @BindView(R.id.et_phone_new_account)
    EditText etPhoneNewAccount;
    @BindView(R.id.et_pass_new_account)
    EditText etPassNewAccount;
    @BindView(R.id.et_confirm_pass_new_account)
    EditText etConfirmPassNewAccount;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private Unbinder unbinder;
    private ApiServies apiServies;
    private ArrayList<Object> cityid;

    SpinnerAdapter bloodAdapter, governAdpter, cityAdapter;

    public NewAcountFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login_new_acount, container, false);
        ButterKnife.bind(this, view);
        initFragment();
        apiServies = getClient().create(ApiServies.class);

        bloodAdapter = new SpinnerAdapter(getActivity());

        getData(apiServies.getBloodType(), bloodAdapter, "Blood Type", spBloodTypeNewAccount);

//////////////////////////////////////////////////////////
        governAdpter = new SpinnerAdapter(getActivity());
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    cityAdapter = new SpinnerAdapter(getActivity());
                    getData(apiServies.getcity(getId()), cityAdapter, "CITY", spCityNewAccount); } }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        ///////////
        getData(apiServies.getGovernorates(), governAdpter, " Governates", spGovernmenNewAccount, listener);
        return view;
    }



    public void setRegestrationData() {
        String user_name = etNameNewAccount.getText().toString().trim();
        if (user_name.trim().equals("") || etNameNewAccount.getText() == null || etNameNewAccount.getText().length() == 0) {
            return;
        }

        String email = etMailNewAccount.getText().toString().trim();
        String phone = etPhoneNewAccount.getText().toString().trim();
        String pass = etPassNewAccount.getText().toString().trim();
        String confirm_pass = etConfirmPassNewAccount.getText().toString().trim();
        String birth_day = etBirthNewAccount.getText().toString().trim();
        String last_danation = etLastDonationDateNewAccount.getText().toString().trim();
        int blood_type = bloodAdapter.selectedId;
        int governrate = governAdpter.selectedId;
        int city = cityAdapter.selectedId;

        apiServies.register(user_name, email, birth_day, city, phone, last_danation, pass, confirm_pass, blood_type).enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                try {
                    Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
                    getActivity().startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(baseActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                if (response.body().getStatus() == 1) {
                    SharedPreferencesManger.SaveData(getActivity(), "USER_DATA", response.body().getData());
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
        baseActivity.onBackPressed();
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        setRegestrationData();
    }
}

