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
import java.util.StringTokenizer;

/**
 *
 * @author Tudor
 */
public class ProdusFactory {
    private ProdusFactory() {}
    
    public static ArrayList<Produs> getProduse(File fisier) {
        
        BufferedReader bufferedP;
        FileReader fisierP;
        StringTokenizer tok;
        Produs curent;
        ArrayList<Produs> produse = new ArrayList();
        
        try {
            fisierP = new FileReader("produse.txt");
            bufferedP = new BufferedReader(fisierP);
            tok = new StringTokenizer(bufferedP.readLine());
            tok.nextToken(); tok.nextToken();
            ArrayList<String> tari = new ArrayList();
            while(tok.hasMoreTokens()) {
                tari.add(tok.nextToken());
            }
            while (bufferedP.ready()) {
                tok = new StringTokenizer(bufferedP.readLine());
                String produs = tok.nextToken();
                String categorie = tok.nextToken();
                for (int i = 0; i < tari.size(); i++) {
                    curent = createProdus(produs, categorie,
                            Double.parseDouble(tok.nextToken()),
                            tari.get(i));
  
                    if(curent.getPret() != 0) {
                        produse.add(curent);
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println("a file not found");
            System.exit(0);
        }
        return produse;
    }
    
    public static Produs createProdus(String produs, String categorie, Double pret,
            String tara) {
        Produs curent = new Produs();
        curent.setDenumire(produs);
        curent.setCategorie(categorie);
        curent.setPret(pret);
        curent.setTaraOrigine(tara);
        return curent;
    }
}
