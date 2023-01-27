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
public class Credenziali {
    private String username;
    private String password;

    public Credenziali(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean verificaPassword(String password) throws Exception{
        validaVerificaPassword(password);
        if(this.password.equals(password)) return true;
        else return false;
    }
    
    @Override
    public String toString() {
        return "Credenziali: " + "username=" + username + ", password=" + password;
    }

    private void validaVerificaPassword(String password) throws Exception {
        if (password.equals("")) {
            throw new Exception("Inserire la password");
        }
        if (password.length() < 6) {
            throw new Exception("Inserire una password valida");
        }    
    }
}
