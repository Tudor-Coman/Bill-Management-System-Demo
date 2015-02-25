/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

/**
 *
 * @author Tudor
 */
public interface IMagazin {
    abstract double getTotalFaraTaxe();
    abstract double getTotalCuTaxe();
    abstract double getTotalCuTaxeScutite();
    abstract double getTotalTaraFaraTaxe(String tara);
    abstract double getTotalTaraCuTaxe(String tara);
    abstract double getTotalTaraCuTaxeScutite(String tara);
    abstract double calculScutiriTaxe();
}
