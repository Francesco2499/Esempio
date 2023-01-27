/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SportPlanner;

import Interface.PriceStrategyInterface;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Ciccio
 */
public class Prenotazione {
    private String identificativoPrenotazione;
    private float importo;
    private boolean pagamentoEffetuato;
    private Cliente cliente;
    private LocalDate data;
    private LocalTime ora;
    private Campo campo;
    
    
    public LocalDate getData() {
        return data;
    }
    
    
    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public Prenotazione(String identificativoPrenotazione, Cliente cliente, LocalDate data, Campo campo) {
        this.identificativoPrenotazione = identificativoPrenotazione;
        this.cliente = cliente;
        this.data = data;
        this.campo = campo;
        this.pagamentoEffetuato=false;
    }
    
    
   
    public String getIdentificativoPrenotazione() {
        return identificativoPrenotazione;
    }

    public void setIdentificativoPrenotazione(String identificativoPrenotazione) {
        this.identificativoPrenotazione = identificativoPrenotazione;
    }

    public float getImporto() {
        return importo;
    }

    public void setImporto(float importo) {
        this.importo = importo;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public boolean isPagamentoEffetuato() {
        return pagamentoEffetuato;
    }

    public void setPagamentoEffetuato(boolean pagamentoEffetuato) {
        this.pagamentoEffetuato = pagamentoEffetuato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Prenotazione{" + "identificativoPrenotazione=" + identificativoPrenotazione + ", importo=" + importo + ", pagamentoEffetuato=" + pagamentoEffetuato + ", cliente=" + cliente + ", data=" + data + ", ora=" + ora + '}';
    }

}
