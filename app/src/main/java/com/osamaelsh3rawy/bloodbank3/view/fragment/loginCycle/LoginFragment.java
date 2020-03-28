package com.osamaelsh3rawy.bloodbank3.view.fragment.loginCycle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.data.api.ApiServies;
import com.osamaelsh3rawy.bloodbank3.data.model.login.Login;
import com.osamaelsh3rawy.bloodbank3.view.activity.HomeCycleActivity;
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
import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.replaceFragment;


public class LoginFragment extends BaseFragment {

    @BindView(R.id.image_logo_login_fragment)
    ImageView imageLogoLoginFragment;
    @BindView(R.id.et_phone_login_fragment)
    EditText etPhoneLoginFragment;
    @BindView(R.id.et_pass_login_fragment)
    EditText etPassLoginFragment;
    @BindView(R.id.ck_remember_login_fragment)
    CheckBox ckRememberLoginFragment;
    @BindView(R.id.tx_forget_pass_login_fragment)
    TextView txForgetPassLoginFragment;
    @BindView(R.id.btn_login_login_fragment)
    Button btnLoginLoginFragment;
    @BindView(R.id.new_account_login_fragment)
    TextView newAccountLoginFragment;
    private ApiServies apiServies;

    public LoginFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        apiServies = getClient().create(ApiServies.class);


        sharedorefrance();

        return view;
    }

    private void sharedorefrance() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", 0);
        if (sharedPreferences.getBoolean("x", false)) {

            etPhoneLoginFragment.setText(sharedPreferences.getString("num", ""));
            etPassLoginFragment.setText(sharedPreferences.getString("pass", ""));
            ckRememberLoginFragment.setChecked(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBack() {

        baseActivity.finish();
    }

    @OnClick({R.id.tx_forget_pass_login_fragment, R.id.btn_login_login_fragment, R.id.new_account_login_fragment,R.id.ck_remember_login_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tx_forget_pass_login_fragment:
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_login_container, new ForgetPassFragment());
                break;

            case R.id.btn_login_login_fragment:
                String phone = etPhoneLoginFragment.getText().toString();
                String password = etPassLoginFragment.getText().toString();
                login(phone, password);
                break;

            case R.id.new_account_login_fragment:
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_login_container, new NewAcountFragment());
                break;

            case R.id.ck_remember_login_fragment:

                if(ckRememberLoginFragment.isChecked()){
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", 0);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("num",etPhoneLoginFragment.getText().toString());
                    editor.putString("pass",etPassLoginFragment.getText().toString());
                    editor.putBoolean("x",true);
                    editor.commit();
                }



                break;
        }
    }

    public void login(String phone, String pass) {

        apiServies.login(phone, pass).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body().getStatus() == 1) {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                    SaveData(getActivity(),User_Api_Takin,response.body().getData().getApiToken());
                    SaveData(getActivity(), User_Name, response.body().getData().getClient().getName());
                    SaveData(getActivity(), User_Email, response.body().getData().getClient().getEmail());
                    SaveData(getActivity(), User_City, response.body().getData().getClient().getCity());
                    SaveData(getActivity(), User_Phone, response.body().getData().getClient().getPhone());
                    SaveData(getActivity(), User_Blood, response.body().getData().getClient().getBloodType());
                    SaveData(getActivity(), User_Birth, response.body().getData().getClient().getBirthDate());
                    SaveData(getActivity(), User_Last, response.body().getData().getClient().getDonationLastDate());


                    Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
                    getActivity().startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.ck_remember_login_fragment)
    public void onViewClicked() {
    }
}

