
package com.planet.treeplantations.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AllTreePlantDetail {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Data")
    @Expose
    private List<Details_Responce_Model> data = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AllTreePlantDetail() {
    }

    /**
     * 
     * @param status
     * @param data
     */
    public AllTreePlantDetail(Integer status, List<Details_Responce_Model> data) {
        super();
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Details_Responce_Model> getData() {
        return data;
    }

    public void setData(List<Details_Responce_Model> data) {
        this.data = data;
    }

   /* @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("data", data).toString();
    }*/

}
