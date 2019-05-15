package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dashboard_Data_Model {


    private String type = "data";

    @SerializedName("ID")
    @Expose
    private String iD;

    @SerializedName("ProjectName")
    @Expose
    private String projectName;

    @SerializedName("Datefrom")
    @Expose
    private String datefrom;

    @SerializedName("DateTo")
    @Expose
    private String dateTo;

    @SerializedName("UploadingDate")
    @Expose
    private String uploadingDate;

    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;

    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;

    @SerializedName("UpdatedBy")
    @Expose
    private String updatedBy;

    @SerializedName("Updateddate")
    @Expose
    private String updateddate;

    @SerializedName("LandTypeName")
    @Expose
    private String landTypeName;

    @SerializedName("LandTypeID")
    @Expose
    private String landTypeID;

    @SerializedName("ProjectCodeName")
    @Expose
    private String projectCodeName;

    /**/
    @SerializedName("PO_MOUNumber")
    @Expose
    private String PO_MOUNumber;

    @SerializedName("PolygonDetai")
    @Expose
    private String PolygonDetai;

    @SerializedName("IsRequested")
    @Expose
    private String IsRequested;
    //
    @SerializedName("FinancialYear")
    @Expose
    private String FinancialYear;

    @SerializedName("RegionName")
    @Expose
    private String RegionName;

    @SerializedName("StateName")
    @Expose
    private String StateName;

    @SerializedName("DivisionName")
    @Expose
    private String DivisionName;


    @SerializedName("RangeName")
    @Expose
    private String RangeName;

    //


    public String getFinancialYear() {
        return FinancialYear;
    }

    public void setFinancialYear(String financialYear) {
        FinancialYear = financialYear;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        RegionName = regionName;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getDivisionName() {
        return DivisionName;
    }

    public void setDivisionName(String divisionName) {
        DivisionName = divisionName;
    }

    public String getRangeName() {
        return RangeName;
    }

    public void setRangeName(String rangeName) {
        RangeName = rangeName;
    }



    public Dashboard_Data_Model() {

    }

    public String getPO_MOUNumber() {
        return PO_MOUNumber;
    }

    public void setPO_MOUNumber(String PO_MOUNumber) {
        this.PO_MOUNumber = PO_MOUNumber;
    }

    public String getPolygonDetai() {
        return PolygonDetai;
    }

    public void setPolygonDetai(String polygonDetai) {
        PolygonDetai = polygonDetai;
    }

    public String getIsRequested() {
        return IsRequested;
    }

    public void setIsRequested(String isRequested) {
        IsRequested = isRequested;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getUploadingDate() {
        return uploadingDate;
    }

    public void setUploadingDate(String uploadingDate) {
        this.uploadingDate = uploadingDate;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(String updateddate) {
        this.updateddate = updateddate;
    }

    public String getLandTypeName() {
        return landTypeName;
    }

    public void setLandTypeName(String landTypeName) {
        this.landTypeName = landTypeName;
    }

    public String getLandTypeID() {
        return landTypeID;
    }

    public void setLandTypeID(String landTypeID) {
        this.landTypeID = landTypeID;
    }

    public String getProjectCodeName() {
        return projectCodeName;
    }

    public void setProjectCodeName(String projectCodeName) {
        this.projectCodeName = projectCodeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
