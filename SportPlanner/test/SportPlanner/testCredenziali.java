/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SportPlanner;


import org.junit.AfterClass;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ciccio
 */
public class testCredenziali {
     
    
    @BeforeClass
    public static void testInit() {
        System.out.println("Inizio test\n");
    }
    
    @Test
    public void testValidaVerificaPassword() {
        Credenziali credenziali = new Credenziali("mario.rossi", "paol8384");
        try {
            assertTrue(credenziali.verificaPassword("paol8384"));
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @AfterClass
    public static void testEnd() {
        System.out.println("Fine test");
    }
}
