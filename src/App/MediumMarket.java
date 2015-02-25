/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

/**
 *
 * @author Tudor
 */
public class MediumMarket extends Magazin {
    
    public double calculScutiriTaxe() {
        Vector<Factura> serieFacturi = getFacturi();
        double valoareVanzari = 0;
        
        HashMap<String, Double> categorii = new HashMap();
        
        for (Factura i : serieFacturi) {
            valoareVanzari += i.getTotalCuTaxe();
        }
        
        for (Factura i : serieFacturi) {
            HashSet<String> temp = i.getCategorii();
            for(String j : temp) {
                if (categorii.get(j) != null)
                    categorii.put(j, categorii.get(j) + i.getTotalCategorieCuTaxe(j));
                else
                    categorii.put(j, i.getTotalCategorieCuTaxe(j));
                
                if(categorii.get(j) > 50/100 * valoareVanzari)
                    return 5;
            }
        }
        
        return 0;
    }
}
