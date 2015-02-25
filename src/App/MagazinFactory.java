/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author Tudor
 */
public class MagazinFactory {
    private MagazinFactory() {}
    
    public static ArrayList<Magazin> getMagazine(File fisier,
                    HashMap<String, HashMap<String, Double>> taxe,
                    ArrayList<Produs> produse) {
        
        ArrayList<Magazin> magazine = null;
        BufferedReader bufferedF;
        FileReader fisierF;
        String linieCurenta, tipMagazin, numeMagazin;
        String numeProdus, taraProdus, categorieProdus;
        int cantitateProdus;
        StringTokenizer tok;
        Magazin magazinCurent = null;
        Factura facturaCurenta;
        ProdusComandat produsComandatCurent;
        double taxaCurenta;
        
        try {
            fisierF = new FileReader(fisier);
            bufferedF = new BufferedReader(fisierF);
            magazine = new ArrayList<>();
            
            while(bufferedF.ready()) {
                linieCurenta = bufferedF.readLine();
                
                if (linieCurenta.startsWith("Magazin")) {
                    tok = new StringTokenizer(linieCurenta, ":");
                    tok.nextToken();
                    tipMagazin = tok.nextToken();
                    numeMagazin = tok.nextToken();
                    switch(tipMagazin) {
                        case "MiniMarket": magazinCurent = new MiniMarket();
                            break;
                        case "MediumMarket": magazinCurent = new MediumMarket();
                            break;
                        case "HyperMarket": magazinCurent = new HyperMarket();
                            break;
                        default: System.out.println("tip magazin invalid");
                                 System.exit(0);
                    }
                    magazinCurent.setNume(numeMagazin);
                    magazine.add(magazinCurent);
                }
                
                if(linieCurenta.startsWith("Factura")) {
                    facturaCurenta = new Factura();
                    magazinCurent.addFactura(facturaCurenta);
                    facturaCurenta.setDenumire(linieCurenta);
                    bufferedF.readLine();
                    linieCurenta = bufferedF.readLine();

                    while(linieCurenta.isEmpty() != true) {
                        
                        tok = new StringTokenizer(linieCurenta);
                        numeProdus = tok.nextToken();
                        taraProdus = tok.nextToken();
                        cantitateProdus = Integer.parseInt(tok.nextToken());
                        for (Produs i : produse) {
                            if(i.getDenumire().compareTo(numeProdus) == 0 && 
                                i.getTaraOrigine().compareTo(taraProdus) == 0) {
                                
                                produsComandatCurent = new ProdusComandat();
                                produsComandatCurent.setProdus(i);
                                i.setOriginal();
                                produsComandatCurent
                                            .setCantitate(cantitateProdus);
                                taxaCurenta = taxe.get(taraProdus)
                                                    .get(i.getCategorie());
                                produsComandatCurent.setTaxa(taxaCurenta);
                                facturaCurenta.addProdusComandat
                                                    (produsComandatCurent);
                                break;
                            }
                        }
                        
                        if(bufferedF.ready() == false)
                            break;
                        else
                            linieCurenta = bufferedF.readLine();
                    }
                }
            }
        } catch(IOException e) {
            System.out.println("probleme in MagazinFactory");
            System.exit(0);
        }
        
        magazine.sort(new MagazinComparator());
        for(Magazin i: magazine)
            i.getFacturi().sort(new FacturaComparator());
        return magazine;
    }
}
