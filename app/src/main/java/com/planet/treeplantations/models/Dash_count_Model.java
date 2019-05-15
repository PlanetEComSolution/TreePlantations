package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dash_count_Model {
    @SerializedName("ttlPrjct")
    @Expose
    private String ttlPrjct;
    @SerializedName("ttlUser")
    @Expose
    private String ttlUser;
    @SerializedName("ttlStateUser")
    @Expose
    private String ttlStateUser;
    @SerializedName("ttlAudit")
    @Expose
    private String ttlAudit;

//    public Dash_count_Model(String ttlPrjct, String ttlUser, String ttlStateUser, String ttlAudit){
//
//
//        }
      //  Dash_count_Model(){}

    public String getTtlPrjct() {
        return ttlPrjct;
    }

    public void setTtlPrjct(String ttlPrjct) {
        this.ttlPrjct = ttlPrjct;
    }

    public String getTtlUser() {
        return ttlUser;
    }

    public void setTtlUser(String ttlUser) {
        this.ttlUser = ttlUser;
    }

    public String getTtlStateUser() {
        return ttlStateUser;
    }

    public void setTtlStateUser(String ttlStateUser) {
        this.ttlStateUser = ttlStateUser;
    }

    public String getTtlAudit() {
        return ttlAudit;
    }

    public void setTtlAudit(String ttlAudit) {
        this.ttlAudit = ttlAudit;
    }



}
