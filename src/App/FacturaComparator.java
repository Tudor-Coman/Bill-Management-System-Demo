/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.util.Comparator;

/**
 *
 * @author Tudor
 */
public class FacturaComparator implements Comparator{

    public int compare(Object arg0, Object arg1) {
        Factura f1 = (Factura) arg0;
        Factura f2 = (Factura) arg1;
        
        // se iau in considerare primele 3 zecimale
        int mul = 1000;
        return (int)(f1.getTotalCuTaxe() * mul - f2.getTotalCuTaxe() * mul);
    }
    
}
