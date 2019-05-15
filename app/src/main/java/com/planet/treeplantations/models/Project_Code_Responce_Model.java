package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Project_Code_Responce_Model {
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("ProjectCodeName")
    @Expose
    private String projectCodeName;
    @SerializedName("IsActive")
    @Expose
    private String isActive;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("Updateddate")
    @Expose
    private String updateddate;
    @SerializedName("UpdatedBy")
    @Expose
    private Object updatedBy;

    Project_Code_Responce_Model(){}
    public Project_Code_Responce_Model(String projectCodeName,String iD) {
        this.projectCodeName = projectCodeName;
        this.iD = iD;
    }

    public String getID() {
        return iD;

    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getProjectCodeName() {
        return projectCodeName;
    }

    public void setProjectCodeName(String projectCodeName) {
        this.projectCodeName = projectCodeName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(String updateddate) {
        this.updateddate = updateddate;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }


    @Override
    public String toString() {
        return projectCodeName;
    }

}
