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
public class MagazinComparator implements Comparator{

    public int compare(Object arg0, Object arg1) {
        Magazin m1 = (Magazin) arg0;
        Magazin m2 = (Magazin) arg1;
        
        // se iau in considerare primele 3 zecimale
        int mul = 1000;
        return (int)(m1.getTotalFaraTaxe() * mul - m2.getTotalFaraTaxe() * mul);
    }
}