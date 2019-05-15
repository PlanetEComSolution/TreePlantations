package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Project_Code_Model {
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Data")
    @Expose
    private List<Project_Code_Responce_Model> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Project_Code_Responce_Model> getData() {
        return data;
    }

    public void setData(List<Project_Code_Responce_Model> data) {
        this.data = data;
    }
}
