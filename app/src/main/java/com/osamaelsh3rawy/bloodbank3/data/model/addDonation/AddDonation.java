
package com.osamaelsh3rawy.bloodbank3.data.model.addDonation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddDonation {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private AddDonationData data;

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

    public AddDonationData getData() {
        return data;
    }

    public void setData(AddDonationData data) {
        this.data = data;
    }

}
