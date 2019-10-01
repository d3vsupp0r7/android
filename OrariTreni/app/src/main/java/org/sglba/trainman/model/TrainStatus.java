
package org.sglba.trainman.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrainStatus {

    @SerializedName("tipoTreno")
    @Expose
    private String tipoTreno;
    @SerializedName("orientamento")
    @Expose
    private Object orientamento;
    @SerializedName("codiceCliente")
    @Expose
    private Integer codiceCliente;
    @SerializedName("fermateSoppresse")
    @Expose
    private Object fermateSoppresse;
    @SerializedName("dataPartenza")
    @Expose
    private Object dataPartenza;
    @SerializedName("fermate")
    @Expose
    private List<Fermate> fermate = null;
    @SerializedName("anormalita")
    @Expose
    private Object anormalita;
    @SerializedName("provvedimenti")
    @Expose
    private Object provvedimenti;
    @SerializedName("segnalazioni")
    @Expose
    private Object segnalazioni;
    @SerializedName("oraUltimoRilevamento")
    @Expose
    private Integer oraUltimoRilevamento;
    @SerializedName("stazioneUltimoRilevamento")
    @Expose
    private String stazioneUltimoRilevamento;
    @SerializedName("idDestinazione")
    @Expose
    private String idDestinazione;
    @SerializedName("idOrigine")
    @Expose
    private String idOrigine;
    @SerializedName("cambiNumero")
    @Expose
    private List<Object> cambiNumero = null;
    @SerializedName("hasProvvedimenti")
    @Expose
    private Boolean hasProvvedimenti;
    @SerializedName("descOrientamento")
    @Expose
    private List<String> descOrientamento = null;
    @SerializedName("compOraUltimoRilevamento")
    @Expose
    private String compOraUltimoRilevamento;
    @SerializedName("motivoRitardoPrevalente")
    @Expose
    private Object motivoRitardoPrevalente;
    @SerializedName("descrizioneVCO")
    @Expose
    private String descrizioneVCO;
    @SerializedName("materiale_label")
    @Expose
    private Object materialeLabel;
    @SerializedName("numeroTreno")
    @Expose
    private Integer numeroTreno;
    @SerializedName("categoria")
    @Expose
    private String categoria;
    @SerializedName("categoriaDescrizione")
    @Expose
    private Object categoriaDescrizione;
    @SerializedName("origine")
    @Expose
    private String origine;
    @SerializedName("codOrigine")
    @Expose
    private Object codOrigine;
    @SerializedName("destinazione")
    @Expose
    private String destinazione;
    @SerializedName("codDestinazione")
    @Expose
    private Object codDestinazione;
    @SerializedName("origineEstera")
    @Expose
    private Object origineEstera;
    @SerializedName("destinazioneEstera")
    @Expose
    private Object destinazioneEstera;
    @SerializedName("oraPartenzaEstera")
    @Expose
    private Object oraPartenzaEstera;
    @SerializedName("oraArrivoEstera")
    @Expose
    private Object oraArrivoEstera;
    @SerializedName("tratta")
    @Expose
    private Integer tratta;
    @SerializedName("regione")
    @Expose
    private Integer regione;
    @SerializedName("origineZero")
    @Expose
    private String origineZero;
    @SerializedName("destinazioneZero")
    @Expose
    private String destinazioneZero;
    @SerializedName("orarioPartenzaZero")
    @Expose
    private Integer orarioPartenzaZero;
    @SerializedName("orarioArrivoZero")
    @Expose
    private Integer orarioArrivoZero;
    @SerializedName("circolante")
    @Expose
    private Boolean circolante;
    @SerializedName("binarioEffettivoArrivoCodice")
    @Expose
    private Object binarioEffettivoArrivoCodice;
    @SerializedName("binarioEffettivoArrivoDescrizione")
    @Expose
    private Object binarioEffettivoArrivoDescrizione;
    @SerializedName("binarioEffettivoArrivoTipo")
    @Expose
    private Object binarioEffettivoArrivoTipo;
    @SerializedName("binarioProgrammatoArrivoCodice")
    @Expose
    private Object binarioProgrammatoArrivoCodice;
    @SerializedName("binarioProgrammatoArrivoDescrizione")
    @Expose
    private Object binarioProgrammatoArrivoDescrizione;
    @SerializedName("binarioEffettivoPartenzaCodice")
    @Expose
    private Object binarioEffettivoPartenzaCodice;
    @SerializedName("binarioEffettivoPartenzaDescrizione")
    @Expose
    private Object binarioEffettivoPartenzaDescrizione;
    @SerializedName("binarioEffettivoPartenzaTipo")
    @Expose
    private Object binarioEffettivoPartenzaTipo;
    @SerializedName("binarioProgrammatoPartenzaCodice")
    @Expose
    private Object binarioProgrammatoPartenzaCodice;
    @SerializedName("binarioProgrammatoPartenzaDescrizione")
    @Expose
    private Object binarioProgrammatoPartenzaDescrizione;
    @SerializedName("subTitle")
    @Expose
    private String subTitle;
    @SerializedName("esisteCorsaZero")
    @Expose
    private String esisteCorsaZero;
    @SerializedName("inStazione")
    @Expose
    private Boolean inStazione;
    @SerializedName("haCambiNumero")
    @Expose
    private Boolean haCambiNumero;
    @SerializedName("nonPartito")
    @Expose
    private Boolean nonPartito;
    @SerializedName("provvedimento")
    @Expose
    private Integer provvedimento;
    @SerializedName("riprogrammazione")
    @Expose
    private Object riprogrammazione;
    @SerializedName("orarioPartenza")
    @Expose
    private Integer orarioPartenza;
    @SerializedName("orarioArrivo")
    @Expose
    private Integer orarioArrivo;
    @SerializedName("stazionePartenza")
    @Expose
    private Object stazionePartenza;
    @SerializedName("stazioneArrivo")
    @Expose
    private Object stazioneArrivo;
    @SerializedName("statoTreno")
    @Expose
    private Object statoTreno;
    @SerializedName("corrispondenze")
    @Expose
    private Object corrispondenze;
    @SerializedName("servizi")
    @Expose
    private List<Object> servizi = null;
    @SerializedName("ritardo")
    @Expose
    private Integer ritardo;
    @SerializedName("tipoProdotto")
    @Expose
    private String tipoProdotto;
    @SerializedName("compOrarioPartenzaZeroEffettivo")
    @Expose
    private String compOrarioPartenzaZeroEffettivo;
    @SerializedName("compOrarioArrivoZeroEffettivo")
    @Expose
    private String compOrarioArrivoZeroEffettivo;
    @SerializedName("compOrarioPartenzaZero")
    @Expose
    private String compOrarioPartenzaZero;
    @SerializedName("compOrarioArrivoZero")
    @Expose
    private String compOrarioArrivoZero;
    @SerializedName("compOrarioArrivo")
    @Expose
    private String compOrarioArrivo;
    @SerializedName("compOrarioPartenza")
    @Expose
    private String compOrarioPartenza;
    @SerializedName("compNumeroTreno")
    @Expose
    private String compNumeroTreno;
    @SerializedName("compOrientamento")
    @Expose
    private List<String> compOrientamento = null;
    @SerializedName("compTipologiaTreno")
    @Expose
    private String compTipologiaTreno;
    @SerializedName("compClassRitardoTxt")
    @Expose
    private String compClassRitardoTxt;
    @SerializedName("compClassRitardoLine")
    @Expose
    private String compClassRitardoLine;
    @SerializedName("compImgRitardo2")
    @Expose
    private String compImgRitardo2;
    @SerializedName("compImgRitardo")
    @Expose
    private String compImgRitardo;
    @SerializedName("compRitardo")
    @Expose
    private List<String> compRitardo = null;
    @SerializedName("compRitardoAndamento")
    @Expose
    private List<String> compRitardoAndamento = null;
    @SerializedName("compInStazionePartenza")
    @Expose
    private List<String> compInStazionePartenza = null;
    @SerializedName("compInStazioneArrivo")
    @Expose
    private List<String> compInStazioneArrivo = null;
    @SerializedName("compOrarioEffettivoArrivo")
    @Expose
    private String compOrarioEffettivoArrivo;
    @SerializedName("compDurata")
    @Expose
    private String compDurata;
    @SerializedName("compImgCambiNumerazione")
    @Expose
    private String compImgCambiNumerazione;

    public String getTipoTreno() {
        return tipoTreno;
    }

    public void setTipoTreno(String tipoTreno) {
        this.tipoTreno = tipoTreno;
    }

    public Object getOrientamento() {
        return orientamento;
    }

    public void setOrientamento(Object orientamento) {
        this.orientamento = orientamento;
    }

    public Integer getCodiceCliente() {
        return codiceCliente;
    }

    public void setCodiceCliente(Integer codiceCliente) {
        this.codiceCliente = codiceCliente;
    }

    public Object getFermateSoppresse() {
        return fermateSoppresse;
    }

    public void setFermateSoppresse(Object fermateSoppresse) {
        this.fermateSoppresse = fermateSoppresse;
    }

    public Object getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(Object dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public List<Fermate> getFermate() {
        return fermate;
    }

    public void setFermate(List<Fermate> fermate) {
        this.fermate = fermate;
    }

    public Object getAnormalita() {
        return anormalita;
    }

    public void setAnormalita(Object anormalita) {
        this.anormalita = anormalita;
    }

    public Object getProvvedimenti() {
        return provvedimenti;
    }

    public void setProvvedimenti(Object provvedimenti) {
        this.provvedimenti = provvedimenti;
    }

    public Object getSegnalazioni() {
        return segnalazioni;
    }

    public void setSegnalazioni(Object segnalazioni) {
        this.segnalazioni = segnalazioni;
    }

    public Integer getOraUltimoRilevamento() {
        return oraUltimoRilevamento;
    }

    public void setOraUltimoRilevamento(Integer oraUltimoRilevamento) {
        this.oraUltimoRilevamento = oraUltimoRilevamento;
    }

    public String getStazioneUltimoRilevamento() {
        return stazioneUltimoRilevamento;
    }

    public void setStazioneUltimoRilevamento(String stazioneUltimoRilevamento) {
        this.stazioneUltimoRilevamento = stazioneUltimoRilevamento;
    }

    public String getIdDestinazione() {
        return idDestinazione;
    }

    public void setIdDestinazione(String idDestinazione) {
        this.idDestinazione = idDestinazione;
    }

    public String getIdOrigine() {
        return idOrigine;
    }

    public void setIdOrigine(String idOrigine) {
        this.idOrigine = idOrigine;
    }

    public List<Object> getCambiNumero() {
        return cambiNumero;
    }

    public void setCambiNumero(List<Object> cambiNumero) {
        this.cambiNumero = cambiNumero;
    }

    public Boolean getHasProvvedimenti() {
        return hasProvvedimenti;
    }

    public void setHasProvvedimenti(Boolean hasProvvedimenti) {
        this.hasProvvedimenti = hasProvvedimenti;
    }

    public List<String> getDescOrientamento() {
        return descOrientamento;
    }

    public void setDescOrientamento(List<String> descOrientamento) {
        this.descOrientamento = descOrientamento;
    }

    public String getCompOraUltimoRilevamento() {
        return compOraUltimoRilevamento;
    }

    public void setCompOraUltimoRilevamento(String compOraUltimoRilevamento) {
        this.compOraUltimoRilevamento = compOraUltimoRilevamento;
    }

    public Object getMotivoRitardoPrevalente() {
        return motivoRitardoPrevalente;
    }

    public void setMotivoRitardoPrevalente(Object motivoRitardoPrevalente) {
        this.motivoRitardoPrevalente = motivoRitardoPrevalente;
    }

    public String getDescrizioneVCO() {
        return descrizioneVCO;
    }

    public void setDescrizioneVCO(String descrizioneVCO) {
        this.descrizioneVCO = descrizioneVCO;
    }

    public Object getMaterialeLabel() {
        return materialeLabel;
    }

    public void setMaterialeLabel(Object materialeLabel) {
        this.materialeLabel = materialeLabel;
    }

    public Integer getNumeroTreno() {
        return numeroTreno;
    }

    public void setNumeroTreno(Integer numeroTreno) {
        this.numeroTreno = numeroTreno;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Object getCategoriaDescrizione() {
        return categoriaDescrizione;
    }

    public void setCategoriaDescrizione(Object categoriaDescrizione) {
        this.categoriaDescrizione = categoriaDescrizione;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public Object getCodOrigine() {
        return codOrigine;
    }

    public void setCodOrigine(Object codOrigine) {
        this.codOrigine = codOrigine;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public Object getCodDestinazione() {
        return codDestinazione;
    }

    public void setCodDestinazione(Object codDestinazione) {
        this.codDestinazione = codDestinazione;
    }

    public Object getOrigineEstera() {
        return origineEstera;
    }

    public void setOrigineEstera(Object origineEstera) {
        this.origineEstera = origineEstera;
    }

    public Object getDestinazioneEstera() {
        return destinazioneEstera;
    }

    public void setDestinazioneEstera(Object destinazioneEstera) {
        this.destinazioneEstera = destinazioneEstera;
    }

    public Object getOraPartenzaEstera() {
        return oraPartenzaEstera;
    }

    public void setOraPartenzaEstera(Object oraPartenzaEstera) {
        this.oraPartenzaEstera = oraPartenzaEstera;
    }

    public Object getOraArrivoEstera() {
        return oraArrivoEstera;
    }

    public void setOraArrivoEstera(Object oraArrivoEstera) {
        this.oraArrivoEstera = oraArrivoEstera;
    }

    public Integer getTratta() {
        return tratta;
    }

    public void setTratta(Integer tratta) {
        this.tratta = tratta;
    }

    public Integer getRegione() {
        return regione;
    }

    public void setRegione(Integer regione) {
        this.regione = regione;
    }

    public String getOrigineZero() {
        return origineZero;
    }

    public void setOrigineZero(String origineZero) {
        this.origineZero = origineZero;
    }

    public String getDestinazioneZero() {
        return destinazioneZero;
    }

    public void setDestinazioneZero(String destinazioneZero) {
        this.destinazioneZero = destinazioneZero;
    }

    public Integer getOrarioPartenzaZero() {
        return orarioPartenzaZero;
    }

    public void setOrarioPartenzaZero(Integer orarioPartenzaZero) {
        this.orarioPartenzaZero = orarioPartenzaZero;
    }

    public Integer getOrarioArrivoZero() {
        return orarioArrivoZero;
    }

    public void setOrarioArrivoZero(Integer orarioArrivoZero) {
        this.orarioArrivoZero = orarioArrivoZero;
    }

    public Boolean getCircolante() {
        return circolante;
    }

    public void setCircolante(Boolean circolante) {
        this.circolante = circolante;
    }

    public Object getBinarioEffettivoArrivoCodice() {
        return binarioEffettivoArrivoCodice;
    }

    public void setBinarioEffettivoArrivoCodice(Object binarioEffettivoArrivoCodice) {
        this.binarioEffettivoArrivoCodice = binarioEffettivoArrivoCodice;
    }

    public Object getBinarioEffettivoArrivoDescrizione() {
        return binarioEffettivoArrivoDescrizione;
    }

    public void setBinarioEffettivoArrivoDescrizione(Object binarioEffettivoArrivoDescrizione) {
        this.binarioEffettivoArrivoDescrizione = binarioEffettivoArrivoDescrizione;
    }

    public Object getBinarioEffettivoArrivoTipo() {
        return binarioEffettivoArrivoTipo;
    }

    public void setBinarioEffettivoArrivoTipo(Object binarioEffettivoArrivoTipo) {
        this.binarioEffettivoArrivoTipo = binarioEffettivoArrivoTipo;
    }

    public Object getBinarioProgrammatoArrivoCodice() {
        return binarioProgrammatoArrivoCodice;
    }

    public void setBinarioProgrammatoArrivoCodice(Object binarioProgrammatoArrivoCodice) {
        this.binarioProgrammatoArrivoCodice = binarioProgrammatoArrivoCodice;
    }

    public Object getBinarioProgrammatoArrivoDescrizione() {
        return binarioProgrammatoArrivoDescrizione;
    }

    public void setBinarioProgrammatoArrivoDescrizione(Object binarioProgrammatoArrivoDescrizione) {
        this.binarioProgrammatoArrivoDescrizione = binarioProgrammatoArrivoDescrizione;
    }

    public Object getBinarioEffettivoPartenzaCodice() {
        return binarioEffettivoPartenzaCodice;
    }

    public void setBinarioEffettivoPartenzaCodice(Object binarioEffettivoPartenzaCodice) {
        this.binarioEffettivoPartenzaCodice = binarioEffettivoPartenzaCodice;
    }

    public Object getBinarioEffettivoPartenzaDescrizione() {
        return binarioEffettivoPartenzaDescrizione;
    }

    public void setBinarioEffettivoPartenzaDescrizione(Object binarioEffettivoPartenzaDescrizione) {
        this.binarioEffettivoPartenzaDescrizione = binarioEffettivoPartenzaDescrizione;
    }

    public Object getBinarioEffettivoPartenzaTipo() {
        return binarioEffettivoPartenzaTipo;
    }

    public void setBinarioEffettivoPartenzaTipo(Object binarioEffettivoPartenzaTipo) {
        this.binarioEffettivoPartenzaTipo = binarioEffettivoPartenzaTipo;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getEsisteCorsaZero() {
        return esisteCorsaZero;
    }

    public void setEsisteCorsaZero(String esisteCorsaZero) {
        this.esisteCorsaZero = esisteCorsaZero;
    }

    public Boolean getInStazione() {
        return inStazione;
    }

    public void setInStazione(Boolean inStazione) {
        this.inStazione = inStazione;
    }

    public Boolean getHaCambiNumero() {
        return haCambiNumero;
    }

    public void setHaCambiNumero(Boolean haCambiNumero) {
        this.haCambiNumero = haCambiNumero;
    }

    public Boolean getNonPartito() {
        return nonPartito;
    }

    public void setNonPartito(Boolean nonPartito) {
        this.nonPartito = nonPartito;
    }

    public Integer getProvvedimento() {
        return provvedimento;
    }

    public void setProvvedimento(Integer provvedimento) {
        this.provvedimento = provvedimento;
    }

    public Object getRiprogrammazione() {
        return riprogrammazione;
    }

    public void setRiprogrammazione(Object riprogrammazione) {
        this.riprogrammazione = riprogrammazione;
    }

    public Integer getOrarioPartenza() {
        return orarioPartenza;
    }

    public void setOrarioPartenza(Integer orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }

    public Integer getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(Integer orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }

    public Object getStazionePartenza() {
        return stazionePartenza;
    }

    public void setStazionePartenza(Object stazionePartenza) {
        this.stazionePartenza = stazionePartenza;
    }

    public Object getStazioneArrivo() {
        return stazioneArrivo;
    }

    public void setStazioneArrivo(Object stazioneArrivo) {
        this.stazioneArrivo = stazioneArrivo;
    }

    public Object getStatoTreno() {
        return statoTreno;
    }

    public void setStatoTreno(Object statoTreno) {
        this.statoTreno = statoTreno;
    }

    public Object getCorrispondenze() {
        return corrispondenze;
    }

    public void setCorrispondenze(Object corrispondenze) {
        this.corrispondenze = corrispondenze;
    }

    public List<Object> getServizi() {
        return servizi;
    }

    public void setServizi(List<Object> servizi) {
        this.servizi = servizi;
    }

    public Integer getRitardo() {
        return ritardo;
    }

    public void setRitardo(Integer ritardo) {
        this.ritardo = ritardo;
    }

    public String getTipoProdotto() {
        return tipoProdotto;
    }

    public void setTipoProdotto(String tipoProdotto) {
        this.tipoProdotto = tipoProdotto;
    }

    public String getCompOrarioPartenzaZeroEffettivo() {
        return compOrarioPartenzaZeroEffettivo;
    }

    public void setCompOrarioPartenzaZeroEffettivo(String compOrarioPartenzaZeroEffettivo) {
        this.compOrarioPartenzaZeroEffettivo = compOrarioPartenzaZeroEffettivo;
    }

    public String getCompOrarioArrivoZeroEffettivo() {
        return compOrarioArrivoZeroEffettivo;
    }

    public void setCompOrarioArrivoZeroEffettivo(String compOrarioArrivoZeroEffettivo) {
        this.compOrarioArrivoZeroEffettivo = compOrarioArrivoZeroEffettivo;
    }

    public String getCompOrarioPartenzaZero() {
        return compOrarioPartenzaZero;
    }

    public void setCompOrarioPartenzaZero(String compOrarioPartenzaZero) {
        this.compOrarioPartenzaZero = compOrarioPartenzaZero;
    }

    public String getCompOrarioArrivoZero() {
        return compOrarioArrivoZero;
    }

    public void setCompOrarioArrivoZero(String compOrarioArrivoZero) {
        this.compOrarioArrivoZero = compOrarioArrivoZero;
    }

    public String getCompOrarioArrivo() {
        return compOrarioArrivo;
    }

    public void setCompOrarioArrivo(String compOrarioArrivo) {
        this.compOrarioArrivo = compOrarioArrivo;
    }

    public String getCompOrarioPartenza() {
        return compOrarioPartenza;
    }

    public void setCompOrarioPartenza(String compOrarioPartenza) {
        this.compOrarioPartenza = compOrarioPartenza;
    }

    public String getCompNumeroTreno() {
        return compNumeroTreno;
    }

    public void setCompNumeroTreno(String compNumeroTreno) {
        this.compNumeroTreno = compNumeroTreno;
    }

    public List<String> getCompOrientamento() {
        return compOrientamento;
    }

    public void setCompOrientamento(List<String> compOrientamento) {
        this.compOrientamento = compOrientamento;
    }

    public String getCompTipologiaTreno() {
        return compTipologiaTreno;
    }

    public void setCompTipologiaTreno(String compTipologiaTreno) {
        this.compTipologiaTreno = compTipologiaTreno;
    }

    public String getCompClassRitardoTxt() {
        return compClassRitardoTxt;
    }

    public void setCompClassRitardoTxt(String compClassRitardoTxt) {
        this.compClassRitardoTxt = compClassRitardoTxt;
    }

    public String getCompClassRitardoLine() {
        return compClassRitardoLine;
    }

    public void setCompClassRitardoLine(String compClassRitardoLine) {
        this.compClassRitardoLine = compClassRitardoLine;
    }

    public String getCompImgRitardo2() {
        return compImgRitardo2;
    }

    public void setCompImgRitardo2(String compImgRitardo2) {
        this.compImgRitardo2 = compImgRitardo2;
    }

    public String getCompImgRitardo() {
        return compImgRitardo;
    }

    public void setCompImgRitardo(String compImgRitardo) {
        this.compImgRitardo = compImgRitardo;
    }

    public List<String> getCompRitardo() {
        return compRitardo;
    }

    public void setCompRitardo(List<String> compRitardo) {
        this.compRitardo = compRitardo;
    }

    public List<String> getCompRitardoAndamento() {
        return compRitardoAndamento;
    }

    public void setCompRitardoAndamento(List<String> compRitardoAndamento) {
        this.compRitardoAndamento = compRitardoAndamento;
    }

    public List<String> getCompInStazionePartenza() {
        return compInStazionePartenza;
    }

    public void setCompInStazionePartenza(List<String> compInStazionePartenza) {
        this.compInStazionePartenza = compInStazionePartenza;
    }

    public List<String> getCompInStazioneArrivo() {
        return compInStazioneArrivo;
    }

    public void setCompInStazioneArrivo(List<String> compInStazioneArrivo) {
        this.compInStazioneArrivo = compInStazioneArrivo;
    }

    public String getCompOrarioEffettivoArrivo() {
        return compOrarioEffettivoArrivo;
    }

    public void setCompOrarioEffettivoArrivo(String compOrarioEffettivoArrivo) {
        this.compOrarioEffettivoArrivo = compOrarioEffettivoArrivo;
    }

    public String getCompDurata() {
        return compDurata;
    }

    public void setCompDurata(String compDurata) {
        this.compDurata = compDurata;
    }

    public String getCompImgCambiNumerazione() {
        return compImgCambiNumerazione;
    }

    public void setCompImgCambiNumerazione(String compImgCambiNumerazione) {
        this.compImgCambiNumerazione = compImgCambiNumerazione;
    }

}
