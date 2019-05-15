package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Division_Responce_Model {

    public Division_Responce_Model(String divisionName,String iD) {
        this.divisionName = divisionName;
        this.iD = iD;
        }

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("DivisionName")
    @Expose
    private String divisionName;
    @SerializedName("StateID")
    @Expose
    private Integer stateID;
    @SerializedName("StateName")
    @Expose
    private String stateName;
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
    private String updateddate;
    @SerializedName("UpdatedBy")
    @Expose
    private String updatedBy;

    Division_Responce_Model(){}



    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public Integer getStateID() {
        return stateID;
    }

    public void setStateID(Integer stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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

    public String getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(String updateddate) {
        this.updateddate = updateddate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    @Override
    public String toString() {
        return divisionName;
    }

}
