/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SportPlanner;
 
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ciccio
 */
public class testSportPlanner {
    static SportPlanner sportPlanner; 
    public testSportPlanner() {
    }
    
    @BeforeClass
    public static void testInit() {
        System.out.println("Inizio test\n");
        sportPlanner = SportPlanner.getInstance();
    }
    
    @Test
    public void testInserisciNuovoTorneo(){
        try {
            ArrayList<LocalTime> orario = new ArrayList<>();
            orario.add(LocalTime.of(18, 30));
            sportPlanner.inserisciNuovoTorneo("Calcio", "7", LocalDate.of(2023, 11,21), LocalDate.of(2023, 11, 22), 10, 50);
            sportPlanner.getTorneoCorrente().setOrario(orario);
            assertNotNull(sportPlanner.getTorneoCorrente());
        } catch (Exception ex) {
            fail("Unexpected exception" + ex.getMessage());        
        }
    }
    
    @Test
     public void testSelezionaCampo(){
        try {
            ArrayList<LocalTime> orario = new ArrayList<>();
            orario.add(LocalTime.of(18, 30));
            sportPlanner.inserisciNuovoTorneo("Calcio","8", LocalDate.of(2023, 11,21), LocalDate.of(2023, 11, 22), 10, 50);
            sportPlanner.getTorneoCorrente().setOrario(orario);
            sportPlanner.setCampo("1", "Campo calcio a 5");
            assertNotNull(sportPlanner.getTorneoCorrente().getCampi());
            assertTrue(sportPlanner.getTorneoCorrente().getCampi().containsKey("1"));
        } catch (Exception ex) {
            fail("Unexpected exception" + ex.getMessage());
        }
    }
    
    @Test
    public void testConfermaInserimento(){
        try {
            ArrayList<LocalTime> orario = new ArrayList<>();
            orario.add(LocalTime.of(18, 30));
            sportPlanner.inserisciNuovoTorneo("Calcio", "4", LocalDate.of(2023, 11,21), LocalDate.of(2023, 11, 22), 10, 50);
            sportPlanner.getTorneoCorrente().setOrario(orario);
            sportPlanner.setCampo("1", "Campo calcio a 5");
            sportPlanner.confermaInserimento();
            assertNotNull(sportPlanner.getElencoTornei());
            assertTrue(sportPlanner.getElencoTornei().containsKey("1"));
            assertEquals(4,sportPlanner.getElencoTornei().size());
        } catch (Exception ex) {
            fail("Unexpected exception" + ex.getMessage());
        }

    }
    
      @Test
    public void testRegistrazioneCliente(){
        try {
            Credenziali c = new Credenziali("mario.bianco", "mario1234");
            assertTrue(sportPlanner.registrazioneCliente("Mario", "Bianco", "3345548677", "mario.bianco@unict.it", c));
            assertNotNull(sportPlanner.getClienteCorrente());
        } catch (Exception ex) {
            fail("Unexpected exception" + ex.getMessage());      
        }
        
    }
    
    @Test
    public void testConfermaRegistrazione(){
        
        try {
            Credenziali c = new Credenziali("mario.bianchi", "mario1234");
            assertTrue(sportPlanner.registrazioneCliente("Mario", "Bianchi", "3345548677", "mario.bianchi@unict.it", c));
            sportPlanner.confermaRegistrazione();
            // 3 poiché 2 clienti vengono aggiunti con il metodo loadClienti
            assertEquals(3,sportPlanner.getElencoClienti().size());
            assertNotNull(sportPlanner.getCliente("mario.bianchi"));
        } catch (Exception ex) {
            fail("Unexpected exception" + ex.getMessage());
        }
    }
    
    @Test
    public void testLogin(){
        try {
            Credenziali c = new Credenziali("mario.bianchi1", "mario1234");
            assertTrue(sportPlanner.registrazioneCliente("Mario", "Bianchi", "3345548677", "mario.bianchi@unict.prova.it", c));
            sportPlanner.confermaRegistrazione();
            assertEquals("Login effettuato!", sportPlanner.login("mario.bianchi", "mario1234"));
        } catch (Exception ex) {
            fail("Unexpected exception" + ex.getMessage());        
        }
      
    }
    
    @Test
    public void testGestisciPrenotazione(){
        try {
            Credenziali c = new Credenziali("mario.bianchi5", "mario1234");
            assertTrue(sportPlanner.registrazioneCliente("Mario", "Bianchi", "3345548677", "mario.bianchi@unictprova4.it", c));
            sportPlanner.confermaRegistrazione();
            assertEquals("Login effettuato!", sportPlanner.login("mario.bianchi", "mario1234"));
            // 3 poiché 2 clienti vengono aggiunti con il metodo loadClienti
            sportPlanner.gestisciPrenotazione("1", LocalDate.of(2023, 11, 11), "mario.bianchi5");
            assertNotNull(sportPlanner.getPrenotazioneCorrente());
        } catch (Exception ex) {
            fail("Unexpected exception" + ex.getMessage());
        }
    }
    
    @Test
    public void testScegliFasciaOraria(){
        try {
            Credenziali c = new Credenziali("mario.bianchi4", "mario1234");
            assertTrue(sportPlanner.registrazioneCliente("Mario", "Bianchi", "3345548677", "mario.bianchi@unictprova3.it", c));
            sportPlanner.confermaRegistrazione();
            assertEquals("Login effettuato!", sportPlanner.login("mario.bianchi", "mario1234"));
            // 3 poiché 2 clienti vengono aggiunti con il metodo loadClienti
            sportPlanner.gestisciPrenotazione("1", LocalDate.of(2023, 11, 11), "mario.bianchi4");
            sportPlanner.scegliFasciaOraria(LocalTime.of(12, 00));
        } catch (Exception ex) {
            fail("Unexpected exception" + ex.getMessage());
        }
    }
    
    @Test
    public void testConfermaPrenotazione(){
        try {
            Credenziali c = new Credenziali("mario.bianchi2", "mario1234");
            assertTrue(sportPlanner.registrazioneCliente("Mario", "Bianchi", "3345548677", "mario.bianchi@unictprova1.it", c));
            sportPlanner.confermaRegistrazione();
            assertEquals("Login effettuato!", sportPlanner.login("mario.bianchi", "mario1234"));
            // 3 poiché 2 clienti vengono aggiunti con il metodo loadClienti
            sportPlanner.gestisciPrenotazione("1", LocalDate.of(2023, 11, 11), "mario.bianchi2");
            sportPlanner.scegliFasciaOraria(LocalTime.of(12, 00));
            sportPlanner.confermaPrenotazione();
            assertEquals(1, sportPlanner.getElencoPrenotazioni().size());
        } catch (Exception ex) {
            fail("Unexpected exception" + ex.getMessage());
        }
    }
    
    @Test
    public void testGestisciIscrizione(){
        try {
            Credenziali c = new Credenziali("mario.bianchi3", "mario1234");
            assertTrue(sportPlanner.registrazioneCliente("Mario", "Bianchi", "3345548677", "mario.bianchi@unictprova2.it", c));
            sportPlanner.confermaRegistrazione();
            assertEquals("Login effettuato!", sportPlanner.login("mario.bianchi", "mario1234"));
            // 3 poiché 2 clienti vengono aggiunti con il metodo loadClienti
            String iscr = sportPlanner.gestisciIscrizione("1","Prova","mario.bianchi3");
            assertNotNull(sportPlanner.getElencoTornei().get("1").getElencoSquadre().get(iscr));
            assertEquals(1,sportPlanner.getElencoTornei().get("1").getElencoSquadre().size());
        } catch (Exception ex) {
            fail("Unexpected exception" + ex.getMessage());
        }
    }
    
    @Test
    public void testValidaCampiTorneo(){
        try {
            sportPlanner.inserisciNuovoTorneo("", "20", LocalDate.of(2023, 11,21), LocalDate.of(2023, 11, 22), 10, 50);
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Compilare la descrizione");
        }
        try {
            sportPlanner.inserisciNuovoTorneo("Calcio", "", LocalDate.of(2023, 11,21), LocalDate.of(2023, 11, 22), 10, 50);
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Compilare il codice del torneo");
        }
        try {
            sportPlanner.inserisciNuovoTorneo("Calcio", "10", LocalDate.of(2022, 11,21), LocalDate.of(2022, 11, 22), 10, 50);
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "La data di inizio deve essere successiva a quella attuale!");
        }
        try {
            sportPlanner.inserisciNuovoTorneo("Calcio", "11", LocalDate.of(2023, 11,21), LocalDate.of(2022, 11, 22), 10, 50);
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "La data di fine deve essere successiva a quella attuale!");
        }
        try {
            sportPlanner.inserisciNuovoTorneo("Calcio", "12", LocalDate.of(2023, 11,21), LocalDate.of(2023, 11, 20), 10, 50);
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "La data di fine deve essere successiva a quella di inizio!");
        }
        try {
            sportPlanner.inserisciNuovoTorneo("Calcio", "14", LocalDate.of(2023, 11,21), LocalDate.of(2023, 11, 22), 10, 160);
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Errore con l'importo selezionato!");
        }
    }
    
    @Test
    public void testValidaCampiCliente(){
        Credenziali c = new Credenziali("mario.bianco", "mario1234");
        try {
            sportPlanner.registrazioneCliente("", "", "", "", c);
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Compilare tutti i campi");
        }
        try {
            sportPlanner.registrazioneCliente("ab", "asfd", "ssffffffadd", "dsadas", c);            
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire un nome valido");
        }
        try {
            sportPlanner.registrazioneCliente("abcd", "ab", "fffffffffff", "fffff", c); 
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire un cognome valido");
        }
        try {
            sportPlanner.registrazioneCliente("abcd", "absdcfffff", "123", "sdaffasffa", c); 
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire un telefono valido");
        }
        try {
            sportPlanner.registrazioneCliente("abcd", "absdc", "12345678910", "ssvd", c); 
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire una e-mail valida");
        }
        try {
            Credenziali cr = new Credenziali("ab", "acsdaa");
            sportPlanner.registrazioneCliente("abcd", "absdc", "12345678910", "mariobianco@gmailesempio.com", cr); 
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire un username valido");
        }
        try {
            Credenziali cr = new Credenziali("abcfsf", "acs");
            sportPlanner.registrazioneCliente("abcd", "absdc", "12345678910", "mariobianco@gmailesempio.com", cr); 
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire una password valida");
        }
    }
    
    @Test
    public void testValidaInserimentoCredenziali(){
        
        try {
            Credenziali c = new Credenziali("mario.bianco1", "mario1234");
            sportPlanner.registrazioneCliente("abcde", "abcde", "12345678910", "mario.bianco@gmail.com", c);
            sportPlanner.confermaRegistrazione();
            sportPlanner.login("", "");
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Compilare tutti i campi");
        }
        try {
            Credenziali c = new Credenziali("mario.bianco2", "mario1234");
            sportPlanner.registrazioneCliente("abcde", "abcde", "12345678910", "mario.bianco@gmail.prova1.com", c);
            sportPlanner.confermaRegistrazione();
            sportPlanner.login("ab", "abcd");
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire un username valido");
        }
        try {
            Credenziali c = new Credenziali("mario.bianco3", "mario1234");
            sportPlanner.registrazioneCliente("abcde", "abcde", "12345678910", "mario.bianco@gmail.prova2.com", c);
            sportPlanner.confermaRegistrazione();
            sportPlanner.login("abcd", "abcd");
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire una password valida");
        }
    }
    
    @Test
    public void testValidaCampoData(){
        try {
            sportPlanner.gestisciPrenotazione("", LocalDate.now(), "mario123");
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire il campo!");
        }
        try {
            sportPlanner.gestisciPrenotazione("0", LocalDate.now(), "mario123");
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire un identificativo del campo corretto!");
        }
        try {
            sportPlanner.gestisciPrenotazione("1", LocalDate.of(2022, 12, 21), "mario123");
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "La data deve essere successiva a quella attuale!");
        }
    }
    
    @Test
    public void testValidaTorneo(){
        try {
            sportPlanner.gestisciIscrizione("", "Prova", "mario123");
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire il codice di un torneo!");
        }
        try {
            sportPlanner.gestisciIscrizione("0", "Prova", "mario123");
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire un identificativo del torneo corretto!");
        }
        try {
            sportPlanner.gestisciIscrizione("1", "", "mario123");
            fail("Expected exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Inserire il nome di una squadra!");
        }
    }
   
    
    @AfterClass
    public static void testEnd() {
        System.out.println("Fine test");
    }
  

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
