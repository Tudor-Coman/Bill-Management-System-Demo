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
public class Produs {
    private String denumire;
    private String categorie;
    private String taraOrigine;
    private double pret;
    private boolean original = false;
    
    public void setDenumire(String s) {
        denumire = s;
    }
    public String getDenumire() {
        return denumire;
    }
    public void setCategorie(String s) {
        categorie = s;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setTaraOrigine(String s) {
        taraOrigine = s;
    }
    public String getTaraOrigine() {
        return taraOrigine;
    }
    public void setPret(double d) {
        pret = d;
    }
    public double getPret() {
        return pret;
    }
    
    public boolean equals(Object o) {
        Produs p = (Produs)o;
        if (denumire.compareTo(p.getDenumire()) != 0)
            return false;
        if (categorie.compareTo(p.getCategorie()) != 0)
            return false;
        if (taraOrigine.compareTo(p.getTaraOrigine()) != 0)
            return false;
        return true;
    }
    public void setOriginal() {
        original = true;
    }
    public boolean isOriginal() {
        return original;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Nume: %s%n", denumire));
        sb.append(String.format("Categorie: %s%n", categorie));
        sb.append(String.format("Tara de origine: %s%n", taraOrigine));
        sb.append(String.format("Tara de origine: %s%n", pret));
        return sb.toString();
    }
}
