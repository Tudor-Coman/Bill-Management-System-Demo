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
public class TaxeFactory {
    private TaxeFactory() {}
    
    public static HashMap<String, HashMap<String, Double>> getTaxe(File f) {
        
        BufferedReader bufferedT;
        FileReader fisierT;
        HashMap<String, HashMap<String, Double>> taxe = new HashMap<>();
        try {
            fisierT = new FileReader(f);
            bufferedT = new BufferedReader(fisierT);
            ArrayList<String> tari = new ArrayList();
            StringTokenizer tok = new StringTokenizer(bufferedT.readLine());
            String categorie;
            HashMap<String, Double> curent;
            
            tok.nextToken();
            while(tok.hasMoreElements()) {
                tari.add(tok.nextToken());
            }
            while(bufferedT.ready()) {
                tok = new StringTokenizer(bufferedT.readLine());
                categorie = tok.nextToken();
                for (int i = 0; i < tari.size(); i++) {
                    double taxa = Double.parseDouble(tok.nextToken());
                    if (taxe.get(tari.get(i)) == null) {
                        curent = new HashMap<>();
                    } else {
                        curent = taxe.get(tari.get(i));
                    }
                    curent.put(categorie, taxa);
                    taxe.put(tari.get(i), curent);
                }
            }
        } catch(IOException e) {
            System.out.println("problema in TaxeFactory");
            System.exit(0);
        }
        
        return taxe;
    }
}
