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
public class Campo {
    private String identificativoCampo;
    private String descrizoneSport;
    private float prezzoSingolaPartita=0;

    public Campo(String identificativoCampo, String descrizoneSport, float prezzoSingolaPartita) {
        this.identificativoCampo = identificativoCampo;
        this.descrizoneSport = descrizoneSport;
        this.prezzoSingolaPartita = prezzoSingolaPartita;
    }
    
    public Campo(String identificativoCampo, String descrizioneSport) {
        this.identificativoCampo = identificativoCampo;
        this.descrizoneSport = descrizioneSport;
        
    }

    public String getDescrizoneSport() {
        return descrizoneSport;
    }

    public void setDescrizoneSport(String descrizoneSport) {
        this.descrizoneSport = descrizoneSport;
    }

    public String getIdentificativoCampo() {
        return identificativoCampo;
    }

    public void setIdentificativoCampo(String identificativoCampo) {
        this.identificativoCampo = identificativoCampo;
    }

    public float getPrezzoSingolaPartita() {
        return prezzoSingolaPartita;
    }

    public void setPrezzoSingolaPartita(float prezzoSingolaPartita) {
        this.prezzoSingolaPartita = prezzoSingolaPartita;
    }

    @Override
    public String toString() {
        return "Campo: " + "identificativoCampo=" + identificativoCampo + ", descrizoneSport=" + descrizoneSport + ", prezzoSingolaPartita=" + prezzoSingolaPartita;
    }
    
    
   
    
    
}
