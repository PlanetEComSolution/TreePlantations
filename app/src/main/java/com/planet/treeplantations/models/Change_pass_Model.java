package com.planet.treeplantations.models;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

public class Change_pass_Model extends BaseObservable {

    private String name;
    private String email;
    private String pass;
    private String passnew;


    public final ObservableField<String> passwordd =  new ObservableField<>("");
    public final ObservableField<String> passwordnew = new ObservableField<>("");


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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;

    }

    public String getPassnew() {
        return passnew;
    }

    public void setPassnew(String passnew) {
        this.passnew = passnew;
    }



}
