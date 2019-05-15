package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Region_Responce_Model {



    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("RegionName")
    @Expose
    private String regionName;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("Updateddate")
    @Expose
    private Object updateddate;
    @SerializedName("UpdatedBy")
    @Expose
    private Object updatedBy;

    Region_Responce_Model(){}
    public Region_Responce_Model(String regionName,String iD) {
        this.regionName = regionName;
        this.iD = iD;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
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

    public Object getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(Object updateddate) {
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
        return regionName;
    }

}
