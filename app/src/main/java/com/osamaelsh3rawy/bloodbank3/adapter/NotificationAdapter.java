package com.osamaelsh3rawy.bloodbank3.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.data.model.notification.NotificationData;
import com.osamaelsh3rawy.bloodbank3.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {


    private Context context;
    private BaseActivity activity;
    private List<NotificationData> notificationDataList = new ArrayList<>();

    public NotificationAdapter(Context context, Activity activity, List<NotificationData> notificationDataList) {
        this.context = context;
        this.activity = (BaseActivity) activity;
        this.notificationDataList = notificationDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_notify_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setDate(holder, position);


    }

    private void setDate(ViewHolder holder, int position) {
        NotificationData notificationData = notificationDataList.get(position);

        if (notificationDataList.get(position).getPivot().getIsRead().equals("1")) {
            holder.notifyItemImgIcon.setImageResource(R.drawable.ic_notifications_full);
        } else {
            holder.notifyItemImgIcon.setImageResource(R.drawable.ic_notifications_none);
        }

        holder.notifyItemTxtAlarm.setText(notificationData.getTitle());
        holder.notifyItemTxtDate.setText((notificationData.getCreatedAt()));
    }

    @Override
    public int getItemCount() {
        return notificationDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.notify_item_img_icon)
        ImageView notifyItemImgIcon;
        @BindView(R.id.notify_item_txt_alarm)
        TextView notifyItemTxtAlarm;
        @BindView(R.id.notify_item_img_clock)
        ImageView notifyItemImgClock;
        @BindView(R.id.notification_item_txt_date)
        TextView notifyItemTxtDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
