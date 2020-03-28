package com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.adapter.NotificationsettingAdapter;
import com.osamaelsh3rawy.bloodbank3.data.api.ApiServies;
import com.osamaelsh3rawy.bloodbank3.data.model.city.GeneralResponse;
import com.osamaelsh3rawy.bloodbank3.data.model.notificationSetting.NotificationSetting;
import com.osamaelsh3rawy.bloodbank3.helper.HelperMethod;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.osamaelsh3rawy.bloodbank3.data.api.ApiClient.getClient;


public class NotificationSettingFragment extends BaseFragment {


    @BindView(R.id.notify_item_rc_blood)
    RecyclerView notifyItemRcBlood;
    @BindView(R.id.notify_item_rc_govern)
    RecyclerView notifyItemRcGovern;
    @BindView(R.id.notify_item_relative_blood_gone)
    RelativeLayout notifyItemRelativeBloodGone;
    @BindView(R.id.notify_item_relative_govern_gone)
    RelativeLayout notifyItemRelativeGovernGone;
    @BindView(R.id.notification_setting_img_blood)
    ImageView notificationSettingImgBlood;
    @BindView(R.id.notification_setting_img_govern)
    ImageView notificationSettingImgGovern;
    private List<String> Governments = new ArrayList<>();
    private List<String> bloodTypes = new ArrayList<>();
    ApiServies apiServies = getClient().create(ApiServies.class);
    private NotificationsettingAdapter governAdapter, bloodAdapter;

    public NotificationSettingFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home_more_notification_setting, container, false);
        ButterKnife.bind(this, view);
        initFragment();
        getNotificationSetting();
        init();
        return view;
    }

    private void init() {
        notifyItemRcBlood.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        notifyItemRcGovern.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }

    private void getNotificationSetting() {

        apiServies.getNotificationSetting("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl").enqueue(new Callback<NotificationSetting>() {
            @Override
            public void onResponse(Call<NotificationSetting> call, Response<NotificationSetting> response) {

                try {
                    if (response.body().getStatus() == 1) {
                        Governments = response.body().getData().getGovernorates();
                        bloodTypes = response.body().getData().getBloodTypes();
                        getGoverments();
                        getbloodType();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<NotificationSetting> call, Throwable t) {

            }
        });
    }

    private void getGoverments() {

        apiServies.getGovernorates().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

                governAdapter = new NotificationsettingAdapter(getActivity(), response.body().getData(), Governments);
                notifyItemRcGovern.setAdapter(governAdapter);
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });
    }


    private void getbloodType() {
        apiServies.getBloodType().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

                bloodAdapter = new NotificationsettingAdapter(getActivity(), response.body().getData(), bloodTypes);
                notifyItemRcBlood.setAdapter(bloodAdapter);
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

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

    public void visible(View view, ImageView imageView) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.drawable.ic_remove);
        } else {
            imageView.setImageResource(R.drawable.ic_add_white_24dp);
            view.setVisibility(View.GONE);
        }
    }

    private void onCall(boolean i) {
        apiServies.setNotificationSetting("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", governAdapter.newIds
                , bloodAdapter.newIds).enqueue(new Callback<NotificationSetting>() {
            @Override
            public void onResponse(Call<NotificationSetting> call, Response<NotificationSetting> response) {

                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<NotificationSetting> call, Throwable t) {

            }
        });

    }

    @OnClick({R.id.notification_setting_img_blood, R.id.notification_setting_img_govern, R.id.notification_setting_btn_save})
    public void onViewClicked(View view) {
        HelperMethod.disappearKeypad(getActivity(), getView());
        switch (view.getId()) {
            case R.id.notification_setting_img_blood:
                visible(notifyItemRcBlood,notificationSettingImgBlood );
                break;
            case R.id.notification_setting_img_govern:
                visible(notifyItemRcGovern,notificationSettingImgGovern );
                break;
            case R.id.notification_setting_btn_save:
                onCall(false);
                break;
        }
    }
}