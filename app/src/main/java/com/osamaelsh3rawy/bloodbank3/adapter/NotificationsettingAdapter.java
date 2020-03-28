package com.osamaelsh3rawy.bloodbank3.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.data.model.city.GeneralResponseData;
import com.osamaelsh3rawy.bloodbank3.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationsettingAdapter extends RecyclerView.Adapter<NotificationsettingAdapter.ViewHolder> {


    private BaseActivity activity;
    private List<GeneralResponseData> generalResponseData = new ArrayList<>();
    private List<String> oldIds = new ArrayList<>();
    public List<Integer> newIds = new ArrayList<>();

    public NotificationsettingAdapter(Activity activity, List<GeneralResponseData> notificationDataList, List<String> oldIds) {

        this.activity = (BaseActivity) activity;
        this.generalResponseData = notificationDataList;
        this.oldIds = oldIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.recycler_notification_setting_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setDate(holder, position);


    }

    private void setDate(ViewHolder holder, int position) {
        holder.item_ch.setText(generalResponseData.get(position).getName());
        if (oldIds.contains(String.valueOf(generalResponseData.get(position).getId()))) {
            holder.item_ch.setChecked(true);
            newIds.add(generalResponseData.get(position).getId());
        } else {
            holder.item_ch.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return generalResponseData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_ch)
        CheckBox item_ch;
        private View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.item_ch)
        public void onViewClicked() {


            if (!item_ch.isChecked()) {
                for (int i = 0; i < newIds.size(); i++) {
                    if (newIds.get(i).equals(generalResponseData.get(getAdapterPosition()).getId())) {
                        newIds.remove(i);   /**hena lw mwgod w ana 3ayz ams7o mn el newIds */
                        break;
                    }
                }
            } else {
                newIds.add(generalResponseData.get(getAdapterPosition()).getId());
                /** hena lw 3ady et3mlo check fa keda ha3mlo add */
            }

        }

    }

}
