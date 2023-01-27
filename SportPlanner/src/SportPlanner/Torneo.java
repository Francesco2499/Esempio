/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SportPlanner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Ciccio
 */
public class Torneo {
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String identificativoTorneo;
    private ArrayList<LocalTime> orario;
    private int numeroSquadre;   
    private String descrizione;
    private int prezzoIscrizione;
    private Map<String, Iscrizione> elencoSquadre;
    private Map<String, Campo> campi;

   
    public Torneo(String descrizione, LocalDate dataInizio, LocalDate dataFine, String identificativoTorneo, int numeroSquadre, int prezzoIscrizione) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.descrizione= descrizione;
        this.identificativoTorneo = identificativoTorneo;
        this.numeroSquadre = numeroSquadre;
        this.prezzoIscrizione = prezzoIscrizione;
        this.campi = new HashMap<>();
        this.orario=null;
        this.elencoSquadre = new HashMap<>();
        }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getIdentificativoTorneo() {
        return identificativoTorneo;
    }

    public void setIdentificativoTorneo(String identificativoTorneo) {
        this.identificativoTorneo = identificativoTorneo;
    }

    public ArrayList<LocalTime> getOrario() {
        return orario;
    }

    public void setOrario(ArrayList<LocalTime> orario) {
        this.orario = orario;
    }

    public int getNumeroSquadre() {
        return numeroSquadre;
    }

    public void setNumeroSquadre(int numeroSquadre) {
        this.numeroSquadre = numeroSquadre;
    }

    public int getPrezzoIscrizione() {
        return prezzoIscrizione;
    }

    public void setPrezzoIscrizione(int prezzoIscrizione) {
        this.prezzoIscrizione = prezzoIscrizione;
    }

    public Map<String, Campo> getCampi() {
        return campi;
    }

    public void setCampi(Map<String, Campo> campi) {
        this.campi = campi;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void addCampo(Campo campo) {
        this.campi.put(campo.getIdentificativoCampo(), campo);
    }

    public Map<String, Iscrizione> getElencoSquadre() {
        return elencoSquadre;
    }

    public void setElencoSquadre(Map<String, Iscrizione> elencoSquadre) {
        this.elencoSquadre = elencoSquadre;
    }
    public void gestisciIscrizione(String codiceIscrizione, String nomeSquadra, Cliente cliente) throws Exception {
        System.out.println("Aggiunta squadra"); 
        
        addSquadra(new Iscrizione(codiceIscrizione, this, cliente, nomeSquadra));
    }
    public void addSquadra(Iscrizione i) throws Exception{
        int j=1;
        if(elencoSquadre.size()<this.numeroSquadre)
        this.elencoSquadre.put(i.getIdentificativoIscrizione(), i); 
        else throw new Exception("Il torneo è al completo!");
        Iterator it = elencoSquadre.keySet().iterator();
        while(it.hasNext()){
            System.out.println(j +  ". " + elencoSquadre.get(it.next()).getNomeSquadra());
            j++;
        }
    }

    @Override
    public String toString() {
        return "Torneo: " + "dataInizio=" + dataInizio + ", dataFine=" + dataFine + ", identificativoTorneo=" + identificativoTorneo + ", orario=" + orario + ", numeroSquadre=" + numeroSquadre + ", descrizione=" + descrizione + ", prezzoIscrizione=" + prezzoIscrizione + ", elencoSquadre=" + elencoSquadre + ", campi=" + campi;
    }

    

    
    
    
}
