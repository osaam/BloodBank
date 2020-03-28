
package com.osamaelsh3rawy.bloodbank3.data.model.catigory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Catigory {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private CatigoryPagination data;

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

    public CatigoryPagination getData() {
        return data;
    }

    public void setData(CatigoryPagination data) {
        this.data = data;
    }

}
