package com.martin.citysearch.about;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 * DTO representing aboutInfo object
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutInfo {

    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("companyAddress")
    @Expose
    private String companyAddress;
    @SerializedName("postalCode")
    @Expose
    private String companyPostal;
    @SerializedName("city")
    @Expose
    private String companyCity;
    @SerializedName("details")
    @Expose
    private String aboutInfo;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPostal() {
        return companyPostal;
    }

    public void setCompanyPostal(String companyPostal) {
        this.companyPostal = companyPostal;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getAboutInfo() {
        return aboutInfo;
    }

    public void setAboutInfo(String aboutInfo) {
        this.aboutInfo = aboutInfo;
    }
}
