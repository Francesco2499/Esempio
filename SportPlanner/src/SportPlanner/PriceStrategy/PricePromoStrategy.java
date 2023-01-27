/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SportPlanner.PriceStrategy;

import Interface.PriceStrategyInterface;
import SportPlanner.Prenotazione;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Ciccio
 */
public class PricePromoStrategy implements PriceStrategyInterface {

    public PricePromoStrategy() {
    }
    
    @Override
    public float calcolaImporto(Prenotazione p) {
        if(p!=null && p.getCampo().getPrezzoSingolaPartita()!=0){
            return (float) ((float)  p.getCampo().getPrezzoSingolaPartita() - p.getCampo().getPrezzoSingolaPartita()*0.15);
        }
        return 0;
    }
    
}
