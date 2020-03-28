
package com.osamaelsh3rawy.bloodbank3.data.model.catigoryPost;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatigoryPost {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<CatigoryPostData> data = null;

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

    public List<CatigoryPostData> getData() {
        return data;
    }

    public void setData(List<CatigoryPostData> data) {
        this.data = data;
    }

}
