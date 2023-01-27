/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SportPlanner;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ciccio
 */
public class testTorneo {
    
    @BeforeClass
    public static void testInit() {
        System.out.println("Inizio test\n");
    }
    
    @Test
    public void testGestisciIscrizione() {
        try {
            Torneo t = new Torneo("Calcio", LocalDate.of(2023, 12, 12), LocalDate.of(2023, 12, 24), "1", 10, 55);
            t.gestisciIscrizione("Prova", "Nome squadra", new Cliente("Prova", "Prova", "3333333333", "prova@prova.it", new Credenziali("Prova.prova", "123456789")));
            assertEquals(1, t.getElencoSquadre().size());
        } catch (Exception ex) {
            fail("Unexpected exception" + ex.getMessage());         }
    }

    @AfterClass
    public static void testEnd() {
        System.out.println("Fine test");
    }
}
