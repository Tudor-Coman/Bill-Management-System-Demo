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
public abstract class Magazin implements IMagazin {
    private String nume;
    private Vector<Factura> serieFacturi = null;
    
    public double getTotalFaraTaxe() {
        double total = 0;
        for(Factura i : serieFacturi) {
            total += i.getTotalFaraTaxe();
        }
        return total;
    }
    
    public double getTotalCuTaxe() {
        double total = 0;
        for(Factura i : serieFacturi) {
            total += i.getTotalCuTaxe();
        }
        return total;
    }
    
    public double getTotalCuTaxeScutite() {
        double scutiri = calculScutiriTaxe();
        return getTotalCuTaxe() * (100 - scutiri) / 100;
    }
    
    public double getTotalTaraFaraTaxe(String tara) {
        double total = 0;
        for(Factura i : serieFacturi) {
            total += i.getTotalTaraFaraTaxe(tara);
        }
        return total;
    }
    
    public double getTotalTaraCuTaxe(String tara) {
        double total = 0;
        for(Factura i : serieFacturi) {
            total += i.getTotalTaraCuTaxe(tara);
        }
        return total;
    }
    
    public double getTotalTaraCuTaxeScutite(String tara) {
        double scutiri = calculScutiriTaxe();
        return getTotalTaraCuTaxe(tara) * (100 - scutiri) / 100;    
    }
    
    public double getTotalCategorieFaraTaxe(String categorie) {
        double total = 0;
        for(Factura i : serieFacturi) {
            total += i.getTotalCategorieFaraTaxe(categorie);
        }
        return total;
    }
    
    public double getTotalCategorieCuTaxe(String categorie) {
        double total = 0;
        for(Factura i : serieFacturi) {
            total += i.getTotalCategorieCuTaxe(categorie);
        }
        return total;
    }
    
    public double getTotalCategorieCuTaxeScutite(String categorie) {
        double scutiri = calculScutiriTaxe();
        return getTotalCategorieCuTaxe(categorie) * (100 - scutiri) / 100;    
    }
    
    public abstract double calculScutiriTaxe();
    
    public Vector<Factura> getFacturi() {
        return serieFacturi;
    }
    
    public void setNume(String s) {
        nume = s;
    }
    
    public String getNume() {
        return nume;
    }
    
    public void addFactura(Factura f) {
        if ( serieFacturi == null) {
            serieFacturi = new Vector<>();
        }
        serieFacturi.add(f);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Nume: %s%n", nume));
        for(Factura i : serieFacturi)
            sb.append(String.format("%n")).append(i.toString());
        return "";
    }
}
