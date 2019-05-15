package com.planet.treeplantations.models;

public class ID_get_Model {

    private String name;
    private String id;
    public ID_get_Model(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return name;
    }
}
