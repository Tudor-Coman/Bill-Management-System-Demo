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
public class ProdusComandat {
    private Produs produs;
    private double taxa;
    private int cantitate;
    
    public void setProdus(Produs p) {
        produs = p;
    }
    
    public Produs getProdus() {
        return produs;
    }
    
    public void setTaxa(double d) {
        taxa = d;
    }
    
    public double getTaxa() {
        return taxa;
    }
    
    public void setCantitate(int c) {
        cantitate = c;
    }
    
    public int getCantitate() {
        return cantitate;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s", produs.toString()));
        sb.append(String.format("Categorie: %f%n", taxa));
        sb.append(String.format("Tara de origine: %d%n", cantitate));
        return sb.toString();
    }
}
