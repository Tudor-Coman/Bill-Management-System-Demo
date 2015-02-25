/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Tudor
 */
public class Gestiune {
    
    private static Gestiune gestiune = null;
    
    public static Gestiune getInstance(File produseFile, File taxeFile, 
                                            File facturiFile, File outFile) {
        
        if (gestiune == null) {
            gestiune = new Gestiune();
        }
        gestiune.produseFile = produseFile;
        gestiune.taxeFile = taxeFile;
        gestiune.facturiFile = facturiFile;
        gestiune.outFile = outFile;
        gestiune.organizeData(produseFile, taxeFile, facturiFile, outFile);
        return gestiune;
    }
    
    public void reOrganize() {
            gestiune.organizeData(produseFile, taxeFile, facturiFile, outFile);
    }
    
    public static Gestiune getInstance() {
        return gestiune;
    }

    private ArrayList<Produs> produse;
    private ArrayList<Magazin> magazine;
    private HashMap<String, HashMap<String, Double>> taxe;
    private PrintStream defaultOut;
    private File produseFile, taxeFile, facturiFile, outFile;
    
    private Gestiune() {
        
    }
    
    private void organizeData(File produseFile, File taxeFile, File facturiFile,
            File outFile) {
        
        produse = ProdusFactory.getProduse(produseFile);
        taxe = TaxeFactory.getTaxe(taxeFile);
        magazine = MagazinFactory.getMagazine(facturiFile, taxe, produse);
        
        defaultOut = System.out;
        
        try {
            String outAddress = outFile.getAbsolutePath() + File.separator + 
                    "out.txt";
            FileOutputStream fis = new FileOutputStream(outAddress);  
            PrintStream out = new PrintStream(fis);  
            
            System.setOut(out); 
        } catch(IOException e){
            System.exit(0);
        }
        
        String tara;
        Iterator<String> it;
        Class[] clase = new Class[3];
        clase[0] = MiniMarket.class;
        clase[1] = MediumMarket.class;
        clase[2] = HyperMarket.class;
        
        for (Class j : clase) {
            System.out.println(j.getSimpleName());

            for ( Magazin i : magazine) { 
                if( i.getClass() == j) {
                    printGlobal(i);
                    it = taxe.keySet().iterator();
                    while (it.hasNext()) {
                        tara = it.next();
                        printByTara(tara, i);
                    }
                    System.out.println();
                    Vector<Factura> facturi = i.getFacturi();
                    for (Factura k : facturi) {
                        printGlobal(k);
                        it = taxe.keySet().iterator();
                        while (it.hasNext()) {
                            tara = it.next();
                            printByTara(tara, k);
                        }
                        System.out.println();
                    }
                }
            }
        }
        System.setOut(defaultOut);
        trimOut();
    }
    
    public String removeZeros(Double value) {
        DecimalFormat format = new DecimalFormat("0.####");
        return format.format(value);
    }
    
    public void printGlobal(Magazin i) {
        
        System.out.format("%s%n%n", i.getNume());

        System.out.format("Total %s",removeZeros(i.getTotalFaraTaxe()));
        System.out.format(" %s ", removeZeros(i.getTotalCuTaxe()));
        System.out.println(removeZeros(i.getTotalCuTaxeScutite()));

        System.out.println();
        System.out.println("Tara");
    }
    
    public void printByTara(String tara, Magazin i) {
        System.out.print(tara + " ");
        System.out.print(removeZeros(i.getTotalTaraFaraTaxe(tara)) + " ");
        if (i.getTotalTaraFaraTaxe(tara) == 0){
            System.out.println();
            return;
        }
        System.out.print(removeZeros(i.getTotalTaraCuTaxe(tara)) + " ");
        System.out.print(removeZeros(i.getTotalTaraCuTaxeScutite(tara)));
        System.out.println();
    }
    
    public void printGlobal(Factura i) {
        System.out.format("%s%n%n", i.getDenumire());
        System.out.print("Total ");
        System.out.print(removeZeros(i.getTotalFaraTaxe()) + " ");
        System.out.format("%s%n%n", removeZeros(i.getTotalCuTaxe()));
        System.out.println("Tara");
    }
    
    public void printByTara(String tara, Factura i) {
        System.out.print(tara + " ");
        if (i.getTari().contains(tara) == false) {
            System.out.println("0");
        } else {
            System.out.print(removeZeros(i.getTotalTaraFaraTaxe(tara)) + " ");
            System.out.print(removeZeros(i.getTotalTaraCuTaxe(tara)));
            System.out.println();
        }
    }
    
    public void trimOut() {
        try {
            String outAddress = outFile.getAbsolutePath() + File.separator + 
                    "out.txt";
            BufferedReader bf = new BufferedReader(new FileReader(outAddress));
            ArrayList<String> randuri = new ArrayList();
            while(bf.ready()) {
                randuri.add(bf.readLine() + System.lineSeparator());
            }
            bf.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(outAddress));
            int  i;
            for(i = 0; i < randuri.size() - 2; i++)
                bw.append(randuri.get(i));
            bw.append(randuri.get(i).trim());
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public ArrayList<Produs> getProduse() {
        return produse;
    }
    
    public void addProduct(Produs p) {
        
        boolean countryExists = false;
        boolean placeExists = false;
        int countryNo = 0;
        int placeNo = 0;
        ArrayList<String> fileStrings = new ArrayList<>();
        int totalCountries = 0;
        
        try {
            BufferedReader bf = new BufferedReader(new FileReader(produseFile));
            String firstLine = bf.readLine();
            
            StringTokenizer st = new StringTokenizer(firstLine);
            st.nextToken();
            st.nextToken();
            while(st.hasMoreTokens()) {
                totalCountries++;
                if (st.nextToken().compareTo(p.getTaraOrigine()) == 0) {
                    countryNo = totalCountries;
                    countryExists = true;
                }
            }
            for(Produs i : produse) {
                if(i.getDenumire().compareTo(p.getDenumire()) == 0 && 
                        i.getCategorie().compareTo(p.getCategorie()) == 0){
                    placeExists = true;
                    placeNo = produse.indexOf(i);
                    break;
                }
            }
            String addon = "";
            
            if(!countryExists) {
                countryNo = totalCountries + 1;
                fileStrings.add(firstLine + " " + p.getTaraOrigine());
                addon = " 0";
                totalCountries++;
            } else {
                fileStrings.add(firstLine);
            }
            while(bf.ready()) {
                fileStrings.add(bf.readLine() + addon);
            }
            if(!placeExists) {
                placeNo = produse.size() - 1;
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%s %s", p.getDenumire(), 
                        p.getCategorie()));
                for(int i = 0; i <totalCountries; i++)
                    sb.append(" " + 0);
                fileStrings.add(sb.toString());
            }
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(produseFile));
            for(int j = 0; j < fileStrings.size(); j++) {
                if(fileStrings.get(j).contains(p.getDenumire() + " " + 
                        p.getCategorie())){
                    StringTokenizer s = new StringTokenizer(fileStrings.get(j));
                    StringBuilder sb = new StringBuilder();
                    sb.append(s.nextToken() + " ");
                    sb.append(s.nextToken());
                    int index = sb.length();
                    for(int k = 1; k < countryNo; k++) {
                        sb.append(" " + s.nextToken());
                        
                    }
                    s.nextToken();
                    if (p.getPret() == 0) {
                        sb.append(" 0");
                    } else
                        sb.append(" " + String.format("%.3f", p.getPret()));
                    
                    for(int k = countryNo + 1; k <= totalCountries; k++) {
                        sb.append(" " + s.nextToken());
                    }
                    StringBuilder sb2 = new StringBuilder();
                    for(int i = 0; i < totalCountries; i++)
                        sb2.append(" 0");
                    if(sb2.toString().compareTo(sb.toString().
                            substring(index, sb.length()))!=0){
                        bw.append(sb);
                        bw.newLine();
                    }
                } else {
                    bw.append(fileStrings.get(j));
                    bw.newLine();
                }
            }
            bw.close();
            reOrganize();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean checkOriginality(Produs p) {
        for(Produs i : produse) {
            if(i.equals(p)){
                if(i.isOriginal())
                    return true;
            }
        }
        return false;
    }
    
    public String[] getTari() {
        Object[] arr = taxe.keySet().toArray();
        return Arrays.copyOf(arr, arr.length, String[].class);
    }
    
    public String[] getCategorii() {
        ArrayList<String> categorii = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(taxeFile));
            br.readLine();
            while(br.ready()) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                categorii.add(st.nextToken());
            }
            br.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        Object[] arr = categorii.toArray();
        return Arrays.copyOf(arr, arr.length, String[].class);
    }
    
    public Magazin[] getMagazine() {
        Object[] arr = magazine.toArray();
        return Arrays.copyOf(arr, arr.length, Magazin[].class);
    }
}
