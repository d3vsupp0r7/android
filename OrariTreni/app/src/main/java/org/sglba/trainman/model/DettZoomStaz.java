
package com.example.oraritreni.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DettZoomStaz implements Serializable
{

    @SerializedName("codiceStazione")
    @Expose
    private String codiceStazione;
    @SerializedName("zoomStartRange")
    @Expose
    private Integer zoomStartRange;
    @SerializedName("zoomStopRange")
    @Expose
    private Integer zoomStopRange;
    @SerializedName("pinpointVisibile")
    @Expose
    private Boolean pinpointVisibile;
    @SerializedName("pinpointVisible")
    @Expose
    private Boolean pinpointVisible;
    @SerializedName("labelVisibile")
    @Expose
    private Boolean labelVisibile;
    @SerializedName("labelVisible")
    @Expose
    private Boolean labelVisible;
    @SerializedName("codiceRegione")
    @Expose
    private Object codiceRegione;
    private final static long serialVersionUID = 3146623904181957183L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DettZoomStaz() {
    }

    /**
     * 
     * @param codiceRegione
     * @param zoomStartRange
     * @param labelVisibile
     * @param pinpointVisibile
     * @param zoomStopRange
     * @param labelVisible
     * @param pinpointVisible
     * @param codiceStazione
     */
    public DettZoomStaz(String codiceStazione, Integer zoomStartRange, Integer zoomStopRange, Boolean pinpointVisibile, Boolean pinpointVisible, Boolean labelVisibile, Boolean labelVisible, Object codiceRegione) {
        super();
        this.codiceStazione = codiceStazione;
        this.zoomStartRange = zoomStartRange;
        this.zoomStopRange = zoomStopRange;
        this.pinpointVisibile = pinpointVisibile;
        this.pinpointVisible = pinpointVisible;
        this.labelVisibile = labelVisibile;
        this.labelVisible = labelVisible;
        this.codiceRegione = codiceRegione;
    }

    public String getCodiceStazione() {
        return codiceStazione;
    }

    public void setCodiceStazione(String codiceStazione) {
        this.codiceStazione = codiceStazione;
    }

    public Integer getZoomStartRange() {
        return zoomStartRange;
    }

    public void setZoomStartRange(Integer zoomStartRange) {
        this.zoomStartRange = zoomStartRange;
    }

    public Integer getZoomStopRange() {
        return zoomStopRange;
    }

    public void setZoomStopRange(Integer zoomStopRange) {
        this.zoomStopRange = zoomStopRange;
    }

    public Boolean getPinpointVisibile() {
        return pinpointVisibile;
    }

    public void setPinpointVisibile(Boolean pinpointVisibile) {
        this.pinpointVisibile = pinpointVisibile;
    }

    public Boolean getPinpointVisible() {
        return pinpointVisible;
    }

    public void setPinpointVisible(Boolean pinpointVisible) {
        this.pinpointVisible = pinpointVisible;
    }

    public Boolean getLabelVisibile() {
        return labelVisibile;
    }

    public void setLabelVisibile(Boolean labelVisibile) {
        this.labelVisibile = labelVisibile;
    }

    public Boolean getLabelVisible() {
        return labelVisible;
    }

    public void setLabelVisible(Boolean labelVisible) {
        this.labelVisible = labelVisible;
    }

    public Object getCodiceRegione() {
        return codiceRegione;
    }

    public void setCodiceRegione(Object codiceRegione) {
        this.codiceRegione = codiceRegione;
    }

}
