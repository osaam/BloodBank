package com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.adapter.NotificationAdapter;
import com.osamaelsh3rawy.bloodbank3.data.api.ApiServies;
import com.osamaelsh3rawy.bloodbank3.data.model.notification.Notification;
import com.osamaelsh3rawy.bloodbank3.data.model.notification.NotificationData;
import com.osamaelsh3rawy.bloodbank3.helper.OnEndLess;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.osamaelsh3rawy.bloodbank3.data.api.ApiClient.getClient;


public class NotificationFragment extends BaseFragment {

    private List<NotificationData> notificationDataList=new ArrayList<>();

    @BindView(R.id.fragment_notify_recycler)
    RecyclerView fragmentNotifyRecycler;
    private NotificationAdapter notificationAdapter;
    private int MaxPage;
    private OnEndLess onEndLess;

    public NotificationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home_notification, container, false);
        ButterKnife.bind(this, view);
        initFragment();
        inti();

        return view;
    }

    private void inti() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        fragmentNotifyRecycler.setLayoutManager(linearLayoutManager);


        notificationAdapter=new NotificationAdapter(getContext(),getActivity(),notificationDataList);
        fragmentNotifyRecycler.setAdapter(notificationAdapter);

        onEndLess=new OnEndLess(linearLayoutManager,1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page<=MaxPage) {
                    if(MaxPage != 0 &&current_page!= 1){
                        onEndLess.previous_page=current_page;
                        getnotify(current_page);
                    }
                    else {
                        onEndLess.current_page=onEndLess.previous_page;
                    }
                }
            }
        };

        getnotify(1);
    }

    private void getnotify(int page) {
        ApiServies apiServies=getClient().create(ApiServies.class);
        apiServies.getNotifications("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",1).enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
                if (response.body().getStatus()==1) {
                    notificationDataList.addAll(response.body().getData().getData());
                    notificationAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {

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
}

