/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SportPlanner;

import SportPlanner.PriceStrategy.PriceStrategySingleton;
import Interface.PriceStrategyInterface;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ciccio
 */
public class SportPlanner {
    private static SportPlanner sportPlanner;
    private Torneo torneoCorrente;
    private Cliente clienteCorrente;
    private Prenotazione prenotazioneCorrente;
    private Map<String,Torneo> elencoTornei;
    private Map<String,Campo> campi;
    private Map<String, Cliente> elencoClienti;
    private Map<String, Prenotazione> elencoPrenotazioni;
    private ArrayList<LocalTime> fasce = new ArrayList<>();
    
    
     private SportPlanner(){
         Integer i;
       this.elencoTornei = new HashMap<>();
       this.campi = new HashMap<>();
       this.elencoClienti = new HashMap<>();
       for(i=12;i<24;i+=2)
       fasce.add(LocalTime.of(i, 00));
       this.elencoPrenotazioni = new HashMap<>();
       loadCampi();
       loadClienti();
       loadTornei();
     /*  LocalDate data1 = LocalDate.of(2022, 11, 15);
        LocalDate data2 = LocalDate.of(2022, 11, 18);
        ArrayList<LocalTime> orari = new ArrayList<>();
        LocalTime time1 = LocalTime.of(10,00);
        orari.add(time1);
        LocalTime time2 = LocalTime.of(20,00);
        orari.add(time2);
        LocalTime time3 = LocalTime.of(18,00);
        orari.add(time3);
        inserisciNuovoTorneo("1", LocalDate.of(2022, 11, 15), LocalDate.of(2022, 11, 18), 10, 70);
        getTorneoCorrente().setOrario(orari);
        for(i = 0; i<4 ; i++){
            setCampo(i.toString(), "Prova1");
        }
        confermaInserimento();*/
   }

    public ArrayList<LocalTime> getFasce() {
        return fasce;
    }
    
    public static SportPlanner getInstance(){
    if(sportPlanner == null)
        sportPlanner = new SportPlanner();
    else
        System.out.println("Istanza già creata");

    return sportPlanner;
   }
    
    public void inserisciNuovoTorneo(String descrizione, String identificativoTorneo, LocalDate dataInizio,LocalDate dataFine ,int numeroSquadre, int prezzoIscrizione) throws Exception{
         for (Map.Entry<String, Torneo> entry : elencoTornei.entrySet()) {
            if (entry.getKey().equals(identificativoTorneo)) {
                throw new Exception("Codice torneo già esistente!");
            }
        }
         
        validaCampiTorneo(descrizione, identificativoTorneo, dataInizio,  dataFine, prezzoIscrizione);
        this.torneoCorrente = new Torneo(descrizione, dataInizio,dataFine, identificativoTorneo,numeroSquadre,prezzoIscrizione);
        System.out.println("\nNuovo Torneo Inserito");
   }

 
    public Prenotazione getPrenotazioneCorrente() {
        return prenotazioneCorrente;
    }

    public void setPrenotazioneCorrente(Prenotazione prenotazioneCorrente) {
        this.prenotazioneCorrente = prenotazioneCorrente;
    }

    public Map<String, Prenotazione> getElencoPrenotazioni() {
        return elencoPrenotazioni;
    }

    public void setElencoPrenotazioni(Map<String, Prenotazione> elencoPrenotazioni) {
        this.elencoPrenotazioni = elencoPrenotazioni;
    }

  
   
    public void setCampo(String identificativoCampo, String desc) throws Exception{
       if(torneoCorrente!=null){
           Campo campo = campi.get(identificativoCampo);
           if(campo!=null){
               System.out.println("ID-> " + identificativoCampo + ": " + desc);
               this.torneoCorrente.addCampo(campo);
               System.out.println("Campo aggiunto al Torneo");
           }
           else
               throw new Exception("Campo non presente nel sistema!");
        }
       else
           throw new Exception("Non stai creando nessun nuovo torneo!");
    }
   
    public void confermaInserimento() throws Exception{
       if(torneoCorrente!=null){
            this.elencoTornei.put(torneoCorrente.getIdentificativoTorneo(),torneoCorrente);
            System.out.println("Inserimento Torneo Concluso");
       }
        else
           throw new Exception("Non stai creando nessun nuovo torneo!");
   }
    
    public boolean registrazioneCliente(String nome, String cognome, String telefono, String email, Credenziali cred) throws Exception{
        validaCampiCliente(nome, cognome, telefono, email, cred);
        int flagUser=0;
        int flagEmail=0;
        String aux;
        Iterator it = elencoClienti.keySet().iterator();
        while(it.hasNext()){
            aux=(String)it.next();
            if(email.equals(elencoClienti.get(aux).getEmail())){
                flagEmail=1;
                break;
            }
            if(cred.getUsername().equals(aux)){
                flagUser=1;
                break;
            }
        }
        if(flagEmail==1)
            throw new Exception("Email già utilizzata");
        else if(flagUser==1)
            throw new Exception("Username già usato");
        else{
            this.clienteCorrente = new Cliente(nome,cognome,telefono,email, cred);
            return true;
        }
    }

    public void confermaRegistrazione() throws Exception{
        if(clienteCorrente!=null){
           this.elencoClienti.put(clienteCorrente.getCredenziali().getUsername(),clienteCorrente);
           System.out.println("Inserimento Cliente Concluso");
       }
        else
           throw new Exception("Nessun cliente in corso di registrazione");
    }
     
    public String login(String username, String password) throws Exception {
        validaInserimentoCredenziali(username, password);
        String aux;
        Iterator it = elencoClienti.keySet().iterator();
        while(it.hasNext()){
            aux=(String) it.next();
            if (username.equals(aux)) {
                if (elencoClienti.get(aux).getCredenziali().verificaPassword(password)) return ("Login effettuato!");
                else throw new Exception("Password errata!");
            }
        } throw new Exception ("Username errato!");
    }
    
    public float gestisciPrenotazione(String identificativoCampo, LocalDate data, String username) throws Exception{
        validaCampoData(identificativoCampo, data);
        String codicePrenotazione= "";
       Campo c =campi.get(identificativoCampo);
       if(c!=null){
            do{
                  codicePrenotazione=generaCodice();
            }while(elencoPrenotazioni.get(codicePrenotazione)!=null);
            prenotazioneCorrente = new Prenotazione(codicePrenotazione, elencoClienti.get(username), data,c);
            return prenotazioneCorrente.getImporto();
       }
       return 0;
    }
    
    public float scegliFasciaOraria(LocalTime ora){
        prenotazioneCorrente.setOra(ora);
        prenotazioneCorrente.setImporto(calcolaImporto());
        System.out.println("Fascia oraria: " + prenotazioneCorrente.getOra() + " aggiunta correttamente");
        return prenotazioneCorrente.getImporto(); 
    }
    
    public void confermaPrenotazione() throws Exception{
       if(prenotazioneCorrente!=null){
            this.elencoPrenotazioni.put(prenotazioneCorrente.getIdentificativoPrenotazione(),prenotazioneCorrente);
            System.out.println("Prenotazione Partita conclusa");
       }
        else
           throw new Exception("Non stai creando nessuna nuova partita!");
    }
    
    public String gestisciIscrizione(String identificativoTorneo, String nomeSquadra, String username) throws Exception{
        validaTorneo(identificativoTorneo, nomeSquadra);
        String codiceIscrizione= ""; 
       Torneo t =elencoTornei.get(identificativoTorneo);
       if(t!=null){
            do{ 
                  codiceIscrizione=generaCodice();
            }while(t.getElencoSquadre().get(codiceIscrizione)!=null);
            System.out.println("Creo iscrizione");
            t.gestisciIscrizione(codiceIscrizione, nomeSquadra, elencoClienti.get(username));
            return codiceIscrizione;
       }
       else
           throw new Exception("Campo non esistente!");
    }
        
    public String generaCodice() {
        return UUID.randomUUID().toString().substring(0, 5);
    }
    
    public static SportPlanner getSportPlanner() {
        return sportPlanner;
    }

    public Map<String, Cliente> getElencoClienti() {
        return elencoClienti;
    }

    public Torneo getTorneoCorrente() {
        return torneoCorrente;
    }

    public Map<String, Torneo> getElencoTornei() {
        return elencoTornei;
    }

    public Map<String, Campo> getCampi() {
        return campi;
    }
    
    public Torneo getTorneo(String identitificativoTorneo){
        return elencoTornei.get(identitificativoTorneo);
    }

    public Cliente getClienteCorrente() {
        return clienteCorrente;
    }

    public Cliente getCliente(String username){
        return elencoClienti.get(username);
    }
    
    public void loadCampi(){
        Campo cam1 = new Campo("1", "Campo Calcio a 5 (A)", (float) 5.5);
        Campo cam2 = new Campo("2", "Campo Calcio a 5 (B)", (float) 5.5);
        Campo cam3 = new Campo("3", "Campo Calcio a 7", 7);
        Campo cam4 = new Campo("4", "Campo Basket (A)", 6);
        Campo cam5 = new Campo("5", "Campo Basket (B)", 6);
        Campo cam6 = new Campo("6", "Campo Padel a 4", 5);
        Campo cam7 = new Campo("7", "Campo Tennis a 2", 7);
        Campo cam8 = new Campo("8", "Campo Tennis a 4", (float) 6.5);
        this.campi.put("1", cam1);
        this.campi.put("2", cam2);
        this.campi.put("3", cam3);
        this.campi.put("4", cam4);
        this.campi.put("5", cam5);
        this.campi.put("6", cam6);
        this.campi.put("7", cam7);
        this.campi.put("8", cam8);
        System.out.println("Caricamento Campi Completato");
    }
    
    public void loadClienti() {
                Credenziali cr1 = new Credenziali("mario123", "12345678");
        Credenziali cr2 = new Credenziali("luigi456", "00112233");

        Cliente cln1 = new Cliente("Mario", "Rossi", "3335566777", "mario.rossi@unict.it", cr1);
        Cliente cln2 = new Cliente("Luigi", "Verdi", "3394455678", "luigi.verdi@unict.it", cr2);
        this.elencoClienti.put(cln1.getCredenziali().getUsername(), cln1);
        this.elencoClienti.put(cln2.getCredenziali().getUsername(), cln2);
        System.out.println("Caricamento Clienti Completato");
    }
    
    private void loadTornei() {
        LocalDate in = LocalDate.of(2023, 3, 1);
        LocalDate in1 = LocalDate.of(2023, 2, 10);
        LocalDate in2 = LocalDate.of(2023, 2, 1);
        LocalDate fi = LocalDate.of(2023, 3, 10);
        LocalDate fi1 = LocalDate.of(2023, 2, 22);        
        LocalDate fi2 = LocalDate.of(2023, 2, 18);
        ArrayList<LocalTime> orario = new ArrayList<>();
        ArrayList<LocalTime> orario1 = new ArrayList<>();
        ArrayList<LocalTime> orario2 = new ArrayList<>();
        orario.add(LocalTime.parse("14:00"));
        orario.add(LocalTime.parse("18:00"));
        orario1.add(LocalTime.parse("14:00"));
        orario1.add(LocalTime.parse("18:00"));
        orario1.add(LocalTime.parse("20:00"));
        orario2.add(LocalTime.parse("16:00"));
        orario2.add(LocalTime.parse("14:00"));
        orario2.add(LocalTime.parse("18:00"));
        Torneo t1 = new Torneo("tennis", in, fi, "1", 8, 40);
        t1.addCampo(campi.get("7"));
        t1.setOrario(orario);
        Torneo t2 = new Torneo("Calcio", in1, fi1, "2", 14, 70);
        t2.addCampo(campi.get("1"));
        t2.addCampo(campi.get("2"));
        t2.setOrario(orario1);
        Torneo t3 = new Torneo("Basket", in2, fi2, "3", 10, 65);
        t3.addCampo(campi.get("4"));
        t3.addCampo(campi.get("5"));
        t3.setOrario(orario2);
        elencoTornei.put(t1.getIdentificativoTorneo(), t1);
        elencoTornei.put(t2.getIdentificativoTorneo(), t2);
        elencoTornei.put(t3.getIdentificativoTorneo(), t3);
        System.out.println("Caricamento Tornei completato");
    }
    
    public int getPrenotazioniCliente(Cliente c){
        Iterator it = elencoPrenotazioni.keySet().iterator();
        int total=0;
        String p;
        while(it.hasNext()){
            p = (String) it.next();
            if(elencoPrenotazioni.get(p).getCliente().equals(c))
                total++;
        }
        return total;
    }
    
    public int getIscrizioniCliente(Cliente c){
        Iterator it = elencoTornei.keySet().iterator();
        int total=0;
        Torneo t;
        while(it.hasNext()){
            t = (Torneo) it.next();
            Iterator i = t.getElencoSquadre().keySet().iterator();
            while(i.hasNext()){
                if(t.getElencoSquadre().get(i.next()).getCliente().equals(c))
                    total++;
            }
        }
        return total;
    }
    /*
    public void loadTornei() throws Exception {
        inserisciNuovoTorneo("1",LocalDate.of(11,11,2022, Month.MARCH, 0), 15, 30, 30);
        selezionaAmbientazione("2");
        confermaInserimento();

        inserisciNuovaPartita("2", 12, 9, 2022, 18, 30, 60);
        selezionaAmbientazione("4");
        confermaInserimento();

        System.out.println("Caricamento Partite Completato");
    }*/
    

    public float calcolaImporto() {
        if (prenotazioneCorrente != null) {
            PriceStrategySingleton strategy = PriceStrategySingleton.getInstance();
            PriceStrategyInterface impInterface = strategy.getImportoStrategy(this);
            float importo = impInterface.calcolaImporto(prenotazioneCorrente);
            System.out.println(importo);
            return importo;
        }
        return 0;
    }
    
    private void validaCampiTorneo(String descrizione, String identificativoTorneo, LocalDate dataInizio, LocalDate dataFine, int prezzoIscrizione) throws Exception {
        if (descrizione.equals("")) {
            throw new Exception("Compilare la descrizione");
        }
        if (identificativoTorneo.equals("")) {
            throw new Exception("Compilare il codice del torneo");
        }
        if(dataInizio.isBefore(LocalDate.now())){
            throw new Exception("La data di inizio deve essere successiva a quella attuale!");
        }
        if(dataFine.isBefore(LocalDate.now())){
            throw new Exception("La data di fine deve essere successiva a quella attuale!");
        }
        if(dataFine.isBefore(dataInizio)){
            throw new Exception("La data di fine deve essere successiva a quella di inizio!");
        }
        if(dataFine == null || dataInizio == null){
            throw new Exception("La data di inizio di fine non sono valide!");
        }
        if(prezzoIscrizione<0 || prezzoIscrizione > 150){
            throw new Exception("Errore con l'importo selezionato!");
        }
       
    }
    
    private void validaCampiCliente(String nome, String cognome, String telefono, String email, Credenziali credenziali) throws Exception {
        if (nome.equals("") || cognome.equals("") || telefono.equals("") || email.equals("") || credenziali.getUsername().equals("") || credenziali.getPassword().equals("")) {
            throw new Exception("Compilare tutti i campi");
        }
        if (nome.length() < 3) {
            throw new Exception("Inserire un nome valido");
        }
        if (cognome.length() < 3) {
            throw new Exception("Inserire un cognome valido");
        }
        if (telefono.length() < 9) {
            throw new Exception("Inserire un telefono valido");
        }
        Pattern p = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        Matcher matcher = p.matcher(email);
        if(!matcher.find()){
            throw new Exception("Inserire una e-mail valida");
        }
        if (credenziali.getUsername().length() < 3) {
            throw new Exception("Inserire un username valido");
        }
        if (credenziali.getPassword().length() < 6) {
            throw new Exception("Inserire una password valida");
        }
    }

    private void validaInserimentoCredenziali(String username, String password) throws Exception {
         if (username.equals("") || password.equals("")) {
            throw new Exception("Compilare tutti i campi");
        }
        if (username.length() < 3) {
            throw new Exception("Inserire un username valido");
        }
        if (password.length() < 6) {
            throw new Exception("Inserire una password valida");
        }
    }

    private void validaCampoData(String identificativoCampo, LocalDate data) throws Exception {
        if (identificativoCampo.equals("")) {
            throw new Exception("Inserire il campo!");
        }
        if(Integer.parseInt(identificativoCampo) <= 0 || Integer.parseInt(identificativoCampo)>campi.size())
           throw new Exception("Inserire un identificativo del campo corretto!");
        if(data.isBefore(LocalDate.now())){
            throw new Exception("La data deve essere successiva a quella attuale!");
        }
    }

    private void validaTorneo(String identificativoTorneo, String nomeSquadra) throws Exception {
        if (identificativoTorneo.equals("")) {
            throw new Exception("Inserire il codice di un torneo!");
        }
        if(Integer.parseInt(identificativoTorneo) <= 0 || Integer.parseInt(identificativoTorneo)>elencoTornei.size())
           throw new Exception("Inserire un identificativo del torneo corretto!");
        if (nomeSquadra.equals("")) {
            throw new Exception("Inserire il nome di una squadra!");
        }
    }
 }
