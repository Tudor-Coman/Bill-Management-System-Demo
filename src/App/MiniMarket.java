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
public class MiniMarket extends Magazin {
    
    public double calculScutiriTaxe() {
        Vector<Factura> serieFacturi = getFacturi();
        double valoareVanzari = 0;
        HashMap<String, Double> tari = new HashMap();
        
        for (Factura i : serieFacturi) {
            valoareVanzari += i.getTotalCuTaxe();
        }
        for (Factura i : serieFacturi) {
            HashSet<String> temp = i.getTari();
            for(String j : temp) {
                if (tari.get(j) != null)
                    tari.put(j, tari.get(j) + i.getTotalTaraCuTaxe(j));
                else
                    tari.put(j, i.getTotalTaraCuTaxe(j));
                if(tari.get(j) > valoareVanzari / 2)
                    return 10;
            }
        }
        return 0;
    }
}
