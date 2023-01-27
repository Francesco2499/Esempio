/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SportPlanner.PriceStrategy;

import SportPlanner.PriceStrategy.PriceStandardStrategy;
import SportPlanner.PriceStrategy.PricePromoStrategy;
import SportPlanner.PriceStrategy.PriceIncreaseStrategy;
import SportPlanner.PriceStrategy.PriceGiftStrategy;
import Interface.PriceStrategyInterface;
import SportPlanner.Prenotazione;
import SportPlanner.SportPlanner;
import java.time.LocalTime;

/**
 *
 * @author Danie
 */
public class PriceStrategySingleton {
    private static PriceStrategySingleton singleton;
    
    private PriceStrategySingleton(){};
    
    public static PriceStrategySingleton getInstance(){
        if(singleton == null)
            singleton = new PriceStrategySingleton();
        else
            System.out.println("Istanza già creata");
        return singleton;
    }
    
    public PriceStrategyInterface getImportoStrategy(SportPlanner sp){
        Prenotazione p = sp.getPrenotazioneCorrente();
        switch(p.getData().getDayOfWeek()){
            case SATURDAY  :
            case SUNDAY : return new PriceIncreaseStrategy();
            default :   break; 
        }
        if(p.getOra().isBefore(LocalTime.of(18,00))) return new PricePromoStrategy();
        if(sp.getPrenotazioniCliente(p.getCliente())%10==0 && sp.getPrenotazioniCliente(p.getCliente())!=0) return new PriceGiftStrategy();
        return new PriceStandardStrategy();
    }
}
