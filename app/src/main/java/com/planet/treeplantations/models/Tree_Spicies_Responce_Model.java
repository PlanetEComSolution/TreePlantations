package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tree_Spicies_Responce_Model {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("TreeSpecies")
    @Expose
    private String treeSpecies;
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
    private String updatedBy;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getTreeSpecies() {
        return treeSpecies;
    }

    public void setTreeSpecies(String treeSpecies) {
        this.treeSpecies = treeSpecies;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }




}
