
package com.osamaelsh3rawy.bloodbank3.data.model.donationFilter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationFilter {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private DonationFilterPagination data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DonationFilterPagination getData() {
        return data;
    }

    public void setData(DonationFilterPagination data) {
        this.data = data;
    }

}
