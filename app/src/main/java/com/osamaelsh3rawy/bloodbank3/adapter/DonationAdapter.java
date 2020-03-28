package com.osamaelsh3rawy.bloodbank3.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.data.model.donation.DonationData;
import com.osamaelsh3rawy.bloodbank3.view.activity.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.onPermission;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder> {


    @BindView(R.id.img_call_donation)
    ImageView imgCallDonation;
    private Context context;
    private BaseActivity activity;
    private ArrayList<DonationData> listDonationData = new ArrayList<>();


    public DonationAdapter(Context context, BaseActivity activity, ArrayList<DonationData> listDonationData) {
        this.context = context;
        this.activity = activity;
        this.listDonationData = listDonationData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_donation_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonationData donationData = listDonationData.get(position);
        holder.nameDonation.setText(donationData.getPatientName());
        // holder.cityDonation.setText(donationData.getCity().getName());
        holder.hospitlDonation.setText(donationData.getHospitalName());
        //holder.donationBloodType.setText(donationData.getBloodType().getName());

        holder.imgCallDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPermission(activity);
                activity.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", listDonationData.get(position).getClient().getPhone(), null)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDonationData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_donation)
        TextView nameDonation;
        @BindView(R.id.hospitl_donation)
        TextView hospitlDonation;
        @BindView(R.id.city_donation)
        TextView cityDonation;
        @BindView(R.id.donation_blood_type)
        TextView donationBloodType;
        @BindView(R.id.img_call_donation)
        ImageView imgCallDonation;
        @BindView(R.id.img_info_donation)
        ImageView imgInfoDonation;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
