package com.osamaelsh3rawy.bloodbank3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.data.model.city.GeneralResponse;
import com.osamaelsh3rawy.bloodbank3.data.model.city.GeneralResponseData;

import java.util.ArrayList;
import java.util.List;
public class SpinnerAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    public List<GeneralResponseData> list = new ArrayList<>();
    public int selectedId = 0;

    public SpinnerAdapter(Context applicationContext) {
        this.context = applicationContext;
        inflater = (LayoutInflater.from(applicationContext));
    }
    public void setData(List<GeneralResponseData> list, String hint) {
        this.list = new ArrayList<>();
        this.list.add(new GeneralResponseData(0,hint));
        this.list.addAll(list);
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_custom_spinner_layout, null);
        TextView spinnerTV = convertView.findViewById(R.id.tvSpinnerLayout);
//        ImageView spinnerIv = convertView.findViewById(R.id.ivSpinnerLayout);
        spinnerTV.setText(list.get(position).getName());

        selectedId = list.get(position).getId();
        return convertView;
    }
}