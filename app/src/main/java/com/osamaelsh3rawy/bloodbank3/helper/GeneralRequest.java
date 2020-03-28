package com.osamaelsh3rawy.bloodbank3.helper;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.osamaelsh3rawy.bloodbank3.adapter.SpinnerAdapter;
import com.osamaelsh3rawy.bloodbank3.data.model.city.GeneralResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class GeneralRequest {

    public static void getData(Call<GeneralResponse> call,final SpinnerAdapter adapter,final String hint, Spinner spinner) {
        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        adapter.setData(response.body().getData(), hint);
                        spinner.setAdapter(adapter);
                    }
                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
            }
        });
    }
    public static void getData(Call<GeneralResponse> call, SpinnerAdapter adapter, String hint, Spinner spinner, AdapterView.OnItemSelectedListener listener) {
        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        adapter.setData(response.body().getData(), hint);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(listener);
                    }
                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
            }
        });
    }
}