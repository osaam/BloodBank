package com.osamaelsh3rawy.bloodbank3.view.fragment.loginCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.data.api.ApiServies;
import com.osamaelsh3rawy.bloodbank3.data.model.notificationSetting.NotificationSetting;
import com.osamaelsh3rawy.bloodbank3.helper.HelperMethod;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.osamaelsh3rawy.bloodbank3.data.api.ApiClient.getClient;
import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.replaceFragment;


public class NewPassFragment extends BaseFragment {


    @BindView(R.id.et_code_confirm)
    EditText etCodeConfirm;
    @BindView(R.id.et_new_pass_1)
    EditText etNewPass1;
    @BindView(R.id.et_new_pass_2)
    EditText etNewPass2;
    private ApiServies apiServies;
   public String phone;

    public NewPassFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login_new_pass, container, false);
        initFragment();
        ButterKnife.bind(this, view);
        apiServies = getClient().create(ApiServies.class);


        return view;
    }

    private void onCall() {
        int pin_code = Integer.parseInt(etCodeConfirm.getText().toString());
        String password = etNewPass1.getText().toString();
        String password_confirmation = etNewPass2.getText().toString();


        apiServies.newPassword(password, password_confirmation, pin_code, phone).enqueue(new Callback<NotificationSetting>() {
            @Override
            public void onResponse(Call<NotificationSetting> call, Response<NotificationSetting> response) {
                if (response.body().getStatus() == 1) {
                    HelperMethod.replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_login_container, new LoginFragment());
                    Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<NotificationSetting> call, Throwable t) {

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
        onCall();
    }
}

