package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Region_Model {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Data")
    @Expose
    private List<Region_Responce_Model> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Region_Responce_Model> getData() {
        return data;
    }

    public void setData(List<Region_Responce_Model> data) {
        this.data = data;
    }

}
