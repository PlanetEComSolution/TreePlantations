package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Range_Model {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Data")
    @Expose
    private List<Range_Responce_Model> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Range_Responce_Model> getData() {
        return data;
    }

    public void setData(List<Range_Responce_Model> data) {
        this.data = data;
    }


}
