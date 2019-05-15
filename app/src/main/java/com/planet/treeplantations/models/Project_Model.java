package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Project_Model {

    @SerializedName("PrjctList")
    @Expose
    private List<Project_Responce_Model> prjctList = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Project_Responce_Model> getPrjctList() {
        return prjctList;
    }

    public void setPrjctList(List<Project_Responce_Model> prjctList) {
        this.prjctList = prjctList;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
