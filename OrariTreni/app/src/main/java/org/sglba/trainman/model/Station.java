
package com.example.oraritreni.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Station implements Serializable
{

    @SerializedName("codReg")
    @Expose
    private Integer codReg;
    @SerializedName("tipoStazione")
    @Expose
    private Integer tipoStazione;
    @SerializedName("dettZoomStaz")
    @Expose
    private List<DettZoomStaz> dettZoomStaz = null;
    @SerializedName("pstaz")
    @Expose
    private List<Object> pstaz = null;
    @SerializedName("mappaCitta")
    @Expose
    private MappaCitta mappaCitta;
    @SerializedName("codiceStazione")
    @Expose
    private String codiceStazione;
    @SerializedName("codStazione")
    @Expose
    private String codStazione;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("latMappaCitta")
    @Expose
    private Integer latMappaCitta;
    @SerializedName("lonMappaCitta")
    @Expose
    private Integer lonMappaCitta;
    @SerializedName("localita")
    @Expose
    private Localita localita;
    @SerializedName("esterno")
    @Expose
    private Boolean esterno;
    @SerializedName("offsetX")
    @Expose
    private Integer offsetX;
    @SerializedName("offsetY")
    @Expose
    private Integer offsetY;
    @SerializedName("nomeCitta")
    @Expose
    private String nomeCitta;
    private final static long serialVersionUID = -6891584500715014820L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Station() {
    }

    /**
     * 
     * @param mappaCitta
     * @param lonMappaCitta
     * @param lon
     * @param latMappaCitta
     * @param offsetY
     * @param esterno
     * @param offsetX
     * @param tipoStazione
     * @param codReg
     * @param pstaz
     * @param localita
     * @param nomeCitta
     * @param codStazione
     * @param dettZoomStaz
     * @param lat
     * @param codiceStazione
     */
    public Station(Integer codReg, Integer tipoStazione, List<DettZoomStaz> dettZoomStaz, List<Object> pstaz, MappaCitta mappaCitta, String codiceStazione, String codStazione, Double lat, Double lon, Integer latMappaCitta, Integer lonMappaCitta, Localita localita, Boolean esterno, Integer offsetX, Integer offsetY, String nomeCitta) {
        super();
        this.codReg = codReg;
        this.tipoStazione = tipoStazione;
        this.dettZoomStaz = dettZoomStaz;
        this.pstaz = pstaz;
        this.mappaCitta = mappaCitta;
        this.codiceStazione = codiceStazione;
        this.codStazione = codStazione;
        this.lat = lat;
        this.lon = lon;
        this.latMappaCitta = latMappaCitta;
        this.lonMappaCitta = lonMappaCitta;
        this.localita = localita;
        this.esterno = esterno;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.nomeCitta = nomeCitta;
    }

    public Integer getCodReg() {
        return codReg;
    }

    public void setCodReg(Integer codReg) {
        this.codReg = codReg;
    }

    public Integer getTipoStazione() {
        return tipoStazione;
    }

    public void setTipoStazione(Integer tipoStazione) {
        this.tipoStazione = tipoStazione;
    }

    public List<DettZoomStaz> getDettZoomStaz() {
        return dettZoomStaz;
    }

    public void setDettZoomStaz(List<DettZoomStaz> dettZoomStaz) {
        this.dettZoomStaz = dettZoomStaz;
    }

    public List<Object> getPstaz() {
        return pstaz;
    }

    public void setPstaz(List<Object> pstaz) {
        this.pstaz = pstaz;
    }

    public MappaCitta getMappaCitta() {
        return mappaCitta;
    }

    public void setMappaCitta(MappaCitta mappaCitta) {
        this.mappaCitta = mappaCitta;
    }

    public String getCodiceStazione() {
        return codiceStazione;
    }

    public void setCodiceStazione(String codiceStazione) {
        this.codiceStazione = codiceStazione;
    }

    public String getCodStazione() {
        return codStazione;
    }

    public void setCodStazione(String codStazione) {
        this.codStazione = codStazione;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Integer getLatMappaCitta() {
        return latMappaCitta;
    }

    public void setLatMappaCitta(Integer latMappaCitta) {
        this.latMappaCitta = latMappaCitta;
    }

    public Integer getLonMappaCitta() {
        return lonMappaCitta;
    }

    public void setLonMappaCitta(Integer lonMappaCitta) {
        this.lonMappaCitta = lonMappaCitta;
    }

    public Localita getLocalita() {
        return localita;
    }

    public void setLocalita(Localita localita) {
        this.localita = localita;
    }

    public Boolean getEsterno() {
        return esterno;
    }

    public void setEsterno(Boolean esterno) {
        this.esterno = esterno;
    }

    public Integer getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(Integer offsetX) {
        this.offsetX = offsetX;
    }

    public Integer getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(Integer offsetY) {
        this.offsetY = offsetY;
    }

    public String getNomeCitta() {
        return nomeCitta;
    }

    public void setNomeCitta(String nomeCitta) {
        this.nomeCitta = nomeCitta;
    }

}
