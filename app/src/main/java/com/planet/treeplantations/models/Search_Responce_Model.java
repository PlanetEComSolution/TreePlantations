package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search_Responce_Model {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Po_mo")
    @Expose
    private String poMo;
    @SerializedName("Land")
    @Expose
    private String land;
    @SerializedName("Poly")
    @Expose
    private String poly;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("Updatedby")
    @Expose
    private String updatedby;
    @SerializedName("Apprvby")
    @Expose
    private String apprvby;
    @SerializedName("Reqfor")
    @Expose
    private String reqfor;
    @SerializedName("UpdatedDate")
    @Expose
    private String updatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoMo() {
        return poMo;
    }

    public void setPoMo(String poMo) {
        this.poMo = poMo;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getPoly() {
        return poly;
    }

    public void setPoly(String poly) {
        this.poly = poly;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getApprvby() {
        return apprvby;
    }

    public void setApprvby(String apprvby) {
        this.apprvby = apprvby;
    }

    public String getReqfor() {
        return reqfor;
    }

    public void setReqfor(String reqfor) {
        this.reqfor = reqfor;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }


}
