/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SportPlanner.PriceStrategy;

import Interface.PriceStrategyInterface;
import SportPlanner.Prenotazione;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Danie
 */
public class PriceGiftStrategy implements PriceStrategyInterface {

    @Override
    public float calcolaImporto(Prenotazione p) {
        return 0;
    }
    
    
    
}
