package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_Responce {


    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("LoginName")
    @Expose
    private String loginName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("UserRole")
    @Expose
    private String userRole;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("UpdateBy")
    @Expose
    private String updateBy;
    @SerializedName("UpdateDate")
    @Expose
    private String updateDate;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("RegionId")
    @Expose
    private String regionId;
    @SerializedName("RegionName")
    @Expose
    private Object regionName;
    @SerializedName("StateName")
    @Expose
    private String stateName;
    @SerializedName("StateID")
    @Expose
    private String stateID;
    @SerializedName("ProjectCode")
    @Expose
    private Object projectCode;
    @SerializedName("ProjectCodeId")
    @Expose
    private String projectCodeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Object getRegionName() {
        return regionName;
    }

    public void setRegionName(Object regionName) {
        this.regionName = regionName;
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

    public Object getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(Object projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCodeId() {
        return projectCodeId;
    }

    public void setProjectCodeId(String projectCodeId) {
        this.projectCodeId = projectCodeId;
    }

}
