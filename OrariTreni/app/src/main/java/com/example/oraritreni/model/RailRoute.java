
package com.example.oraritreni.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RailRoute {

    @SerializedName("soluzioni")
    @Expose
    private List<Soluzioni> soluzioni = null;
    @SerializedName("origine")
    @Expose
    private String origine;
    @SerializedName("destinazione")
    @Expose
    private String destinazione;
    @SerializedName("errore")
    @Expose
    private Object errore;

    public List<Soluzioni> getSoluzioni() {
        return soluzioni;
    }

    public void setSoluzioni(List<Soluzioni> soluzioni) {
        this.soluzioni = soluzioni;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public Object getErrore() {
        return errore;
    }

    public void setErrore(Object errore) {
        this.errore = errore;
    }

}
