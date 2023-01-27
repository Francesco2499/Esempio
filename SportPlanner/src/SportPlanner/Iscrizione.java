/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SportPlanner;

/**
 *
 * @author Ciccio
 */
public class Iscrizione {
    private String identificativoIscrizione;
    private float quotaTot;
    private Torneo torneo;
    private Cliente cliente;
    private String nomeSquadra;
    private boolean pagamentoEffettuato;
    
    public Iscrizione(String identificativoIscrizione, Torneo torneo, Cliente cliente, String nomeSquadra) {
        this.identificativoIscrizione = identificativoIscrizione;
        this.quotaTot = torneo.getPrezzoIscrizione();;
        this.torneo = torneo;
        this.cliente = cliente;
        this.nomeSquadra = nomeSquadra;
    }

    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public void setNomeSquadra(String nomeSquadra) {
        this.nomeSquadra = nomeSquadra;
    }

    public String getIdentificativoIscrizione() {
        return identificativoIscrizione;
    }

    public void setIdentificativoIscrizione(String identificativoIscrizione) {
        this.identificativoIscrizione = identificativoIscrizione;
    }

    public float getQuotaTot() {
        return quotaTot;
    }

    public void setQuotaTot(float quotaTot) {
        this.quotaTot = quotaTot;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isPagamentoEffettuato() {
        return pagamentoEffettuato;
    }

    public void setPagamentoEffettuato(boolean pagamentoEffettuato) {
        this.pagamentoEffettuato = pagamentoEffettuato;
    }

    @Override
    public String toString() {
        return "Iscrizione: " + "identificativoIscrizione=" + identificativoIscrizione + ", quotaTot=" + quotaTot + ", torneo=" + torneo.getIdentificativoTorneo() + ", cliente=" + cliente;
    }
    
    
}
