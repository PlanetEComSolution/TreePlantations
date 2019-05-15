package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.planet.treeplantations.R;

public class Project_Responce_Model {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("ProjectName")
    @Expose
    private String projectName;
    @SerializedName("PO_MOUNumberID")
    @Expose
    private String pOMOUNumberID;
    @SerializedName("PO_MOUNumber")
    @Expose
    private Object pOMOUNumber;
    @SerializedName("RegionName")
    @Expose
    private String regionName;
    @SerializedName("RegionID")
    @Expose
    private String regionID;
    @SerializedName("StateName")
    @Expose
    private String stateName;
    @SerializedName("StateID")
    @Expose
    private String stateID;
    @SerializedName("ProjectCost")
    @Expose
    private Integer projectCost;
    @SerializedName("Agency")
    @Expose
    private String agency;
    @SerializedName("IsActive")
    @Expose
    private String isActive;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("UpdatedBy")
    @Expose
    private Object updatedBy;
    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;

    public Project_Responce_Model(String projectName,String id) {
        this.projectName = projectName;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPOMOUNumberID() {
        return pOMOUNumberID;
    }

    public void setPOMOUNumberID(String pOMOUNumberID) {
        this.pOMOUNumberID = pOMOUNumberID;
    }

    public Object getPOMOUNumber() {
        return pOMOUNumber;
    }

    public void setPOMOUNumber(Object pOMOUNumber) {
        this.pOMOUNumber = pOMOUNumber;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public Integer getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(Integer projectCost) {
        this.projectCost = projectCost;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getIsActive() {
        return isActive.equals("true") ? "Active" : "DeActive";
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
      //  this.isActive = isActive .equals("true") ? "Active" : "DeActive";
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return projectName;
    }
}
