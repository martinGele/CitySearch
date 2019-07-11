package com.martin.citysearch.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("_id")
    @Expose
    private Integer id;
    @SerializedName("coord")
    @Expose
    private Coord coord;

    public City(String country, String name, Integer id, Coord coord) {
        this.country = country;
        this.name = name;
        this.id = id;
        this.coord = coord;
    }

    public City(String country, String name, Integer id) {
        this.country = country;
        this.name = name;
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

}