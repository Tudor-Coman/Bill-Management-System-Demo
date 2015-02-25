/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.util.Vector;

/**
 *
 * @author Tudor
 */
public class HyperMarket extends Magazin {
    
    public double calculScutiriTaxe() {
        Vector<Factura> serieFacturi = getFacturi();
        double valoareVanzari = 0;
        for (Factura i : serieFacturi) {
            valoareVanzari += i.getTotalCuTaxe();
        }
        for (Factura i : serieFacturi) {
            if ( i.getTotalCuTaxe() > 10/100 * valoareVanzari)
                return 1;
        }
        return 0;
    }
}
