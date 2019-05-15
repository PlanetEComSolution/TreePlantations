package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Login_Model {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Data")
    @Expose
    private List<Login_Responce> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Login_Responce> getData() {
        return data;
    }

    public void setData(List<Login_Responce> data) {
        this.data = data;

    }
}