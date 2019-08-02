
package com.example.oraritreni.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MappaCitta implements Serializable
{

    @SerializedName("urlImagePinpoint")
    @Expose
    private String urlImagePinpoint;
    @SerializedName("urlImageBaloon")
    @Expose
    private String urlImageBaloon;
    private final static long serialVersionUID = 1391753767417045295L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MappaCitta() {
    }

    /**
     * 
     * @param urlImagePinpoint
     * @param urlImageBaloon
     */
    public MappaCitta(String urlImagePinpoint, String urlImageBaloon) {
        super();
        this.urlImagePinpoint = urlImagePinpoint;
        this.urlImageBaloon = urlImageBaloon;
    }

    public String getUrlImagePinpoint() {
        return urlImagePinpoint;
    }

    public void setUrlImagePinpoint(String urlImagePinpoint) {
        this.urlImagePinpoint = urlImagePinpoint;
    }

    public String getUrlImageBaloon() {
        return urlImageBaloon;
    }

    public void setUrlImageBaloon(String urlImageBaloon) {
        this.urlImageBaloon = urlImageBaloon;
    }

}
