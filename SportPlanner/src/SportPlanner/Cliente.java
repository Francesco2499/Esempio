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
public class Cliente {
    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private Credenziali credenziali;

    public Cliente(String nome, String cognome, String telefono, String email, Credenziali credenziali) {
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
        this.credenziali = credenziali;
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Credenziali getCredenziali() {
        return credenziali;
    }

    public void setCredenziali(Credenziali credenziali) {
        this.credenziali = credenziali;
    }

    @Override
    public String toString() {
        return "Cliente: " + "nome=" + nome + ", cognome=" + cognome + ", telefono=" + telefono + ", email=" + email + ", credenziali=" + credenziali;
    }

    
}
