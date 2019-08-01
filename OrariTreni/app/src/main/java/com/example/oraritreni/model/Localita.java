
package com.example.oraritreni.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Localita implements Serializable
{

    @SerializedName("nomeLungo")
    @Expose
    private String nomeLungo;
    @SerializedName("nomeBreve")
    @Expose
    private String nomeBreve;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("id")
    @Expose
    private String id;
    private final static long serialVersionUID = 3591801698584083793L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Localita() {
    }

    /**
     * 
     * @param id
     * @param nomeBreve
     * @param nomeLungo
     * @param label
     */
    public Localita(String nomeLungo, String nomeBreve, String label, String id) {
        super();
        this.nomeLungo = nomeLungo;
        this.nomeBreve = nomeBreve;
        this.label = label;
        this.id = id;
    }

    public String getNomeLungo() {
        return nomeLungo;
    }

    public void setNomeLungo(String nomeLungo) {
        this.nomeLungo = nomeLungo;
    }

    public String getNomeBreve() {
        return nomeBreve;
    }

    public void setNomeBreve(String nomeBreve) {
        this.nomeBreve = nomeBreve;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
