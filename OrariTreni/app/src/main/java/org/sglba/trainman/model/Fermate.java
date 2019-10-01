
package org.sglba.trainman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fermate {

    @SerializedName("orientamento")
    @Expose
    private Object orientamento;
    @SerializedName("kcNumTreno")
    @Expose
    private Object kcNumTreno;
    @SerializedName("stazione")
    @Expose
    private String stazione;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("listaCorrispondenze")
    @Expose
    private Object listaCorrispondenze;
    @SerializedName("programmata")
    @Expose
    private Integer programmata;
    @SerializedName("programmataZero")
    @Expose
    private Object programmataZero;
    @SerializedName("effettiva")
    @Expose
    private Object effettiva;
    @SerializedName("ritardo")
    @Expose
    private Integer ritardo;
    @SerializedName("partenzaTeoricaZero")
    @Expose
    private Object partenzaTeoricaZero;
    @SerializedName("arrivoTeoricoZero")
    @Expose
    private Object arrivoTeoricoZero;
    @SerializedName("partenza_teorica")
    @Expose
    private Object partenzaTeorica;
    @SerializedName("arrivo_teorico")
    @Expose
    private Integer arrivoTeorico;
    @SerializedName("isNextChanged")
    @Expose
    private Boolean isNextChanged;
    @SerializedName("partenzaReale")
    @Expose
    private Object partenzaReale;
    @SerializedName("arrivoReale")
    @Expose
    private Object arrivoReale;
    @SerializedName("ritardoPartenza")
    @Expose
    private Integer ritardoPartenza;
    @SerializedName("ritardoArrivo")
    @Expose
    private Integer ritardoArrivo;
    @SerializedName("progressivo")
    @Expose
    private Integer progressivo;
    @SerializedName("binarioEffettivoArrivoCodice")
    @Expose
    private String binarioEffettivoArrivoCodice;
    @SerializedName("binarioEffettivoArrivoTipo")
    @Expose
    private String binarioEffettivoArrivoTipo;
    @SerializedName("binarioEffettivoArrivoDescrizione")
    @Expose
    private String binarioEffettivoArrivoDescrizione;
    @SerializedName("binarioProgrammatoArrivoCodice")
    @Expose
    private Object binarioProgrammatoArrivoCodice;
    @SerializedName("binarioProgrammatoArrivoDescrizione")
    @Expose
    private String binarioProgrammatoArrivoDescrizione;
    @SerializedName("binarioEffettivoPartenzaCodice")
    @Expose
    private Object binarioEffettivoPartenzaCodice;
    @SerializedName("binarioEffettivoPartenzaTipo")
    @Expose
    private Object binarioEffettivoPartenzaTipo;
    @SerializedName("binarioEffettivoPartenzaDescrizione")
    @Expose
    private Object binarioEffettivoPartenzaDescrizione;
    @SerializedName("binarioProgrammatoPartenzaCodice")
    @Expose
    private Object binarioProgrammatoPartenzaCodice;
    @SerializedName("binarioProgrammatoPartenzaDescrizione")
    @Expose
    private Object binarioProgrammatoPartenzaDescrizione;
    @SerializedName("tipoFermata")
    @Expose
    private String tipoFermata;
    @SerializedName("visualizzaPrevista")
    @Expose
    private Boolean visualizzaPrevista;
    @SerializedName("nextChanged")
    @Expose
    private Boolean nextChanged;
    @SerializedName("nextTrattaType")
    @Expose
    private Integer nextTrattaType;
    @SerializedName("actualFermataType")
    @Expose
    private Integer actualFermataType;
    @SerializedName("materiale_label")
    @Expose
    private Object materialeLabel;

    public Object getOrientamento() {
        return orientamento;
    }

    public void setOrientamento(Object orientamento) {
        this.orientamento = orientamento;
    }

    public Object getKcNumTreno() {
        return kcNumTreno;
    }

    public void setKcNumTreno(Object kcNumTreno) {
        this.kcNumTreno = kcNumTreno;
    }

    public String getStazione() {
        return stazione;
    }

    public void setStazione(String stazione) {
        this.stazione = stazione;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getListaCorrispondenze() {
        return listaCorrispondenze;
    }

    public void setListaCorrispondenze(Object listaCorrispondenze) {
        this.listaCorrispondenze = listaCorrispondenze;
    }

    public Integer getProgrammata() {
        return programmata;
    }

    public void setProgrammata(Integer programmata) {
        this.programmata = programmata;
    }

    public Object getProgrammataZero() {
        return programmataZero;
    }

    public void setProgrammataZero(Object programmataZero) {
        this.programmataZero = programmataZero;
    }

    public Object getEffettiva() {
        return effettiva;
    }

    public void setEffettiva(Object effettiva) {
        this.effettiva = effettiva;
    }

    public Integer getRitardo() {
        return ritardo;
    }

    public void setRitardo(Integer ritardo) {
        this.ritardo = ritardo;
    }

    public Object getPartenzaTeoricaZero() {
        return partenzaTeoricaZero;
    }

    public void setPartenzaTeoricaZero(Object partenzaTeoricaZero) {
        this.partenzaTeoricaZero = partenzaTeoricaZero;
    }

    public Object getArrivoTeoricoZero() {
        return arrivoTeoricoZero;
    }

    public void setArrivoTeoricoZero(Object arrivoTeoricoZero) {
        this.arrivoTeoricoZero = arrivoTeoricoZero;
    }

    public Object getPartenzaTeorica() {
        return partenzaTeorica;
    }

    public void setPartenzaTeorica(Object partenzaTeorica) {
        this.partenzaTeorica = partenzaTeorica;
    }

    public Integer getArrivoTeorico() {
        return arrivoTeorico;
    }

    public void setArrivoTeorico(Integer arrivoTeorico) {
        this.arrivoTeorico = arrivoTeorico;
    }

    public Boolean getIsNextChanged() {
        return isNextChanged;
    }

    public void setIsNextChanged(Boolean isNextChanged) {
        this.isNextChanged = isNextChanged;
    }

    public Object getPartenzaReale() {
        return partenzaReale;
    }

    public void setPartenzaReale(Object partenzaReale) {
        this.partenzaReale = partenzaReale;
    }

    public Object getArrivoReale() {
        return arrivoReale;
    }

    public void setArrivoReale(Object arrivoReale) {
        this.arrivoReale = arrivoReale;
    }

    public Integer getRitardoPartenza() {
        return ritardoPartenza;
    }

    public void setRitardoPartenza(Integer ritardoPartenza) {
        this.ritardoPartenza = ritardoPartenza;
    }

    public Integer getRitardoArrivo() {
        return ritardoArrivo;
    }

    public void setRitardoArrivo(Integer ritardoArrivo) {
        this.ritardoArrivo = ritardoArrivo;
    }

    public Integer getProgressivo() {
        return progressivo;
    }

    public void setProgressivo(Integer progressivo) {
        this.progressivo = progressivo;
    }

    public String getBinarioEffettivoArrivoCodice() {
        return binarioEffettivoArrivoCodice;
    }

    public void setBinarioEffettivoArrivoCodice(String binarioEffettivoArrivoCodice) {
        this.binarioEffettivoArrivoCodice = binarioEffettivoArrivoCodice;
    }

    public String getBinarioEffettivoArrivoTipo() {
        return binarioEffettivoArrivoTipo;
    }

    public void setBinarioEffettivoArrivoTipo(String binarioEffettivoArrivoTipo) {
        this.binarioEffettivoArrivoTipo = binarioEffettivoArrivoTipo;
    }

    public String getBinarioEffettivoArrivoDescrizione() {
        return binarioEffettivoArrivoDescrizione;
    }

    public void setBinarioEffettivoArrivoDescrizione(String binarioEffettivoArrivoDescrizione) {
        this.binarioEffettivoArrivoDescrizione = binarioEffettivoArrivoDescrizione;
    }

    public Object getBinarioProgrammatoArrivoCodice() {
        return binarioProgrammatoArrivoCodice;
    }

    public void setBinarioProgrammatoArrivoCodice(Object binarioProgrammatoArrivoCodice) {
        this.binarioProgrammatoArrivoCodice = binarioProgrammatoArrivoCodice;
    }

    public String getBinarioProgrammatoArrivoDescrizione() {
        return binarioProgrammatoArrivoDescrizione;
    }

    public void setBinarioProgrammatoArrivoDescrizione(String binarioProgrammatoArrivoDescrizione) {
        this.binarioProgrammatoArrivoDescrizione = binarioProgrammatoArrivoDescrizione;
    }

    public Object getBinarioEffettivoPartenzaCodice() {
        return binarioEffettivoPartenzaCodice;
    }

    public void setBinarioEffettivoPartenzaCodice(Object binarioEffettivoPartenzaCodice) {
        this.binarioEffettivoPartenzaCodice = binarioEffettivoPartenzaCodice;
    }

    public Object getBinarioEffettivoPartenzaTipo() {
        return binarioEffettivoPartenzaTipo;
    }

    public void setBinarioEffettivoPartenzaTipo(Object binarioEffettivoPartenzaTipo) {
        this.binarioEffettivoPartenzaTipo = binarioEffettivoPartenzaTipo;
    }

    public Object getBinarioEffettivoPartenzaDescrizione() {
        return binarioEffettivoPartenzaDescrizione;
    }

    public void setBinarioEffettivoPartenzaDescrizione(Object binarioEffettivoPartenzaDescrizione) {
        this.binarioEffettivoPartenzaDescrizione = binarioEffettivoPartenzaDescrizione;
    }

    public Object getBinarioProgrammatoPartenzaCodice() {
        return binarioProgrammatoPartenzaCodice;
    }

    public void setBinarioProgrammatoPartenzaCodice(Object binarioProgrammatoPartenzaCodice) {
        this.binarioProgrammatoPartenzaCodice = binarioProgrammatoPartenzaCodice;
    }

    public Object getBinarioProgrammatoPartenzaDescrizione() {
        return binarioProgrammatoPartenzaDescrizione;
    }

    public void setBinarioProgrammatoPartenzaDescrizione(Object binarioProgrammatoPartenzaDescrizione) {
        this.binarioProgrammatoPartenzaDescrizione = binarioProgrammatoPartenzaDescrizione;
    }

    public String getTipoFermata() {
        return tipoFermata;
    }

    public void setTipoFermata(String tipoFermata) {
        this.tipoFermata = tipoFermata;
    }

    public Boolean getVisualizzaPrevista() {
        return visualizzaPrevista;
    }

    public void setVisualizzaPrevista(Boolean visualizzaPrevista) {
        this.visualizzaPrevista = visualizzaPrevista;
    }

    public Boolean getNextChanged() {
        return nextChanged;
    }

    public void setNextChanged(Boolean nextChanged) {
        this.nextChanged = nextChanged;
    }

    public Integer getNextTrattaType() {
        return nextTrattaType;
    }

    public void setNextTrattaType(Integer nextTrattaType) {
        this.nextTrattaType = nextTrattaType;
    }

    public Integer getActualFermataType() {
        return actualFermataType;
    }

    public void setActualFermataType(Integer actualFermataType) {
        this.actualFermataType = actualFermataType;
    }

    public Object getMaterialeLabel() {
        return materialeLabel;
    }

    public void setMaterialeLabel(Object materialeLabel) {
        this.materialeLabel = materialeLabel;
    }

}
