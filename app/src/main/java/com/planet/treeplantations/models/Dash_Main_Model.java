package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Dash_Main_Model  implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("dashdata")
    @Expose

    private List<Dashboard_data> data = null;
    @SerializedName("data")
    @Expose
    private List<Dashboard_Data_Model> dashdata = null;
    @SerializedName("o_TotalCount")
    @Expose
    private Dash_count_Model oTotalCount;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Dashboard_data> getData() {
        return data;
    }

    public void setData(List<Dashboard_data> data) {
        this.data = data;
    }

    public List<Dashboard_Data_Model> getDashdata() {
        return dashdata;
    }

    public void setDashdata(List<Dashboard_Data_Model> dashdata) {
        this.dashdata = dashdata;
    }

    public Dash_count_Model getOTotalCount() {
        return oTotalCount;
    }

    public void setOTotalCount(Dash_count_Model oTotalCount) {
        this.oTotalCount = oTotalCount;
    }

}
