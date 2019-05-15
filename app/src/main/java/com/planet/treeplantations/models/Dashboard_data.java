package com.planet.treeplantations.models;

import android.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dashboard_data  {

    @SerializedName("TotalProjectCost")
    @Expose
    private String totalProjectCost;
    @SerializedName("TotalNoOfPlant")
    @Expose
    private String totalNoOfPlant;
    @SerializedName("StateName")
    @Expose
    private String stateName;

    public String getTotalProjectCost() {
        return totalProjectCost;
    }

    public void setTotalProjectCost(String totalProjectCost) {
        this.totalProjectCost = totalProjectCost;
       // notifyPropertyChanged(BR.totalProjectCost);
    }

    public String getTotalNoOfPlant() {
        return totalNoOfPlant;
    }

    public void setTotalNoOfPlant(String totalNoOfPlant) {
        this.totalNoOfPlant = totalNoOfPlant;
       // notifyPropertyChanged(BR.totalNoOfPlant);
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
       // notifyPropertyChanged(BR.stateName);
    }



}
