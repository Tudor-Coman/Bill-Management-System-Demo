/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.util.HashSet;
import java.util.Vector;

/**
 *
 * @author Tudor
 */
public class Factura {
    private String denumire;
    private Vector<ProdusComandat> serieProduse;
    
    public double getTotalFaraTaxe(){
        double total = 0;
        for(ProdusComandat p : serieProduse) {
            total += p.getProdus().getPret() * p.getCantitate();
        }
        return total;
    }
    
    public double getTotalCuTaxe() {
        double total = 0;
        double temp;
        for(ProdusComandat p : serieProduse) {
            temp = p.getProdus().getPret() * p.getCantitate();
            total += temp * (100 + p.getTaxa()) / 100;
        }
        return total;
    }
    
    public double getTaxe() {
        double total = 0;
        double temp;
        for(ProdusComandat p : serieProduse) {
            temp = p.getProdus().getPret() * p.getCantitate();
            total += temp * p.getTaxa() / 100;
        }
        return total;        
    }
    
    public double getTotalTaraFaraTaxe(String tara) {
        double total = 0;
        for(ProdusComandat p : serieProduse) {
            if (p.getProdus().getTaraOrigine().compareTo(tara) == 0)
                total += p.getProdus().getPret() * p.getCantitate();
        }
        return total;       
    }
    
    public double getTotalTaraCuTaxe(String tara) {
        double total = 0;
        double temp;
        for(ProdusComandat p : serieProduse) {
            if (p.getProdus().getTaraOrigine().compareTo(tara) == 0) {
                temp = p.getProdus().getPret() * p.getCantitate();
                total += temp * (100 + p.getTaxa()) / 100;
            }
        }
        return total;
    }
    
    public double getTaxeTara(String tara) {
        double total = 0;
        double temp;
        for(ProdusComandat p : serieProduse) {
            if (p.getProdus().getTaraOrigine().compareTo(tara) == 0) {
                temp = p.getProdus().getPret() * p.getCantitate();
                total += temp * p.getTaxa() / 100;
            }
        }
        return total;         
    }
    
    public double getTotalCategorieFaraTaxe(String categorie) {
        double total = 0;
        for(ProdusComandat p : serieProduse) {
            if (p.getProdus().getCategorie().compareTo(categorie) == 0)
                total += p.getProdus().getPret() * p.getCantitate();
        }
        return total;       
    }
    
    public double getTotalCategorieCuTaxe(String categorie) {
        double total = 0;
        double temp;
        for(ProdusComandat p : serieProduse) {
            if (p.getProdus().getCategorie().compareTo(categorie) == 0) {
                temp = p.getProdus().getPret() * p.getCantitate();
                total += temp * (100 + p.getTaxa()) / 100;
            }
        }
        return total;
    }
    
    public double getTaxeCategorie(String categorie) {
        double total = 0;
        double temp;
        for(ProdusComandat p : serieProduse) {
            if (p.getProdus().getCategorie().compareTo(categorie) == 0) {
                temp = p.getProdus().getPret() * p.getCantitate();
                total += temp * p.getTaxa() / 100;
            }
        }
        return total;         
    }
    
    public HashSet<String> getTari () {
        HashSet<String> tari = new HashSet();
        for (ProdusComandat p : serieProduse) {
            String temp = p.getProdus().getTaraOrigine();
            if (tari.contains(temp) == false)
                tari.add(temp);
        }
        return tari;
    }
    
    public HashSet<String> getCategorii () {
        HashSet<String> categorii = new HashSet();
        for (ProdusComandat p : serieProduse) {
            String temp = p.getProdus().getCategorie();
            if (categorii.contains(temp) == false)
                categorii.add(temp);
        }
        return categorii;
    }    
    
    public void setDenumire(String s) {
        denumire = s;
    }
    
    public void addProdusComandat(ProdusComandat f) {
        if ( serieProduse == null) {
            serieProduse = new Vector<>();
        }
        serieProduse.add(f);
    }
    
    public String getDenumire() {
        return denumire;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Denumire: %s%n", denumire));
        sb.append(String.format("Total fara taxe: %f%n", getTotalFaraTaxe()));
        sb.append(String.format("Total cu taxe: %f%n", getTotalCuTaxe()));
        return sb.toString();
    }
}
