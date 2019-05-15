package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Range_Responce_Model {


    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("RangeName")
    @Expose
    private String rangeName;
    @SerializedName("DivisionID")
    @Expose
    private Integer divisionID;
    @SerializedName("DivisionName")
    @Expose
    private String divisionName;
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

    Range_Responce_Model(){}


    public Range_Responce_Model(String rangeName,String iD) {
        this.rangeName = rangeName;
        this.iD = iD;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public Integer getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
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
        return rangeName;
    }

}
