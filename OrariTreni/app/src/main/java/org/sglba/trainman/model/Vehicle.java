
package com.example.oraritreni.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicle {

    @SerializedName("origine")
    @Expose
    private String origine;
    @SerializedName("destinazione")
    @Expose
    private String destinazione;
    @SerializedName("orarioPartenza")
    @Expose
    private String orarioPartenza;
    @SerializedName("orarioArrivo")
    @Expose
    private String orarioArrivo;
    @SerializedName("categoria")
    @Expose
    private String categoria;
    @SerializedName("categoriaDescrizione")
    @Expose
    private String categoriaDescrizione;
    @SerializedName("numeroTreno")
    @Expose
    private String numeroTreno;

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

    public String getOrarioPartenza() {
        return orarioPartenza;
    }

    public void setOrarioPartenza(String orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }

    public String getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(String orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoriaDescrizione() {
        return categoriaDescrizione;
    }

    public void setCategoriaDescrizione(String categoriaDescrizione) {
        this.categoriaDescrizione = categoriaDescrizione;
    }

    public String getNumeroTreno() {
        return numeroTreno;
    }

    public void setNumeroTreno(String numeroTreno) {
        this.numeroTreno = numeroTreno;
    }

}
