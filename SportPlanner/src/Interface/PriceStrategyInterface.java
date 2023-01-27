/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import SportPlanner.Prenotazione;

/**
 *
 * @author Danie
 */
public interface PriceStrategyInterface {
    public float calcolaImporto(Prenotazione p);
}
