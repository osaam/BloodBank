package com.osamaelsh3rawy.bloodbank3.view.fragment.loginCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.data.api.ApiServies;
import com.osamaelsh3rawy.bloodbank3.data.model.rePassward.RePassword;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.osamaelsh3rawy.bloodbank3.data.api.ApiClient.getClient;
import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.replaceFragment;


public class ForgetPassFragment extends BaseFragment {


    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.et_phone_2)
    EditText etPhone2;

   // String phone;
    private ApiServies apiServies;

    public ForgetPassFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login_forget_pass, container, false);
        ButterKnife.bind(this, view);
        initFragment();
        return view;
    }

    private void inti() {
     final String phone=etPhone2.getText().toString();

        apiServies = getClient().create(ApiServies.class);

        apiServies.reset_password(phone).enqueue(new Callback<RePassword>() {
            @Override
            public void onResponse(Call<RePassword> call, Response<RePassword> response) {
                if (response.body().getStatus() == 1) {
                    NewPassFragment newPassFragment = new NewPassFragment();
                    newPassFragment.phone = phone;
                    replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_login_container, new NewPassFragment());
                    Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RePassword> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBack() {
        baseActivity.superonBackPressed();
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        inti();
    }

}

