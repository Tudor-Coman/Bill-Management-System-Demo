/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Tudor
 */
class StatisticiScreen extends JPanel {

    String[] tari;
    String[] categorii;
    
    Magazin[] magazine;
    JPanel afis;
    
    public StatisticiScreen() {
        magazine = Gestiune.getInstance().getMagazine();
        this.setLayout(new BorderLayout());
        JComboBox jb = new JComboBox();
        jb.addItem(new MyComboBoxModel("magazin", null, null, 
                "Top magazin vanzari"));
        
        tari = Gestiune.getInstance().getTari();
        for(Object i : tari)
            jb.addItem(new MyComboBoxModel("magazin", null, i.toString(),
                "Top magazin vanzari in " + i.toString()));
        
        categorii = Gestiune.getInstance().getCategorii();
        for(Object i : categorii)
            jb.addItem(new MyComboBoxModel("magazin", i.toString(), null,
                "Top magazin vanzari " + i.toString()));
        
        jb.addItem(new MyComboBoxModel("factura", null, null, 
                "Factura cea mai mare"));
        jb.addActionListener(new MyComboBoxListener());
        
        this.add(jb, BorderLayout.NORTH);
        
        afis = new JPanel();
        afis.setLayout(null);
        this.add(afis, BorderLayout.CENTER);

        jb.setSelectedIndex(0);
    }
    private class MyComboBoxModel {
        String type; // magazin, factura
        String categorie;
        String tara;
        String mesaj;
        public MyComboBoxModel(String type, String categorie, String tara, 
                String mesaj) {
            this.type = type;
            this.categorie = categorie;
            this.tara = tara;
            this.mesaj = mesaj;
        }
        
        public String toString() {
            return mesaj;
        }
    }
    
    private class MyComboBoxListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String numeMagazin = null;
            JComboBox jb =(JComboBox) e.getSource();
            MyComboBoxModel m = (MyComboBoxModel)jb.getSelectedItem();
            if(m.type.compareTo("magazin") == 0) {
                if(m.categorie == null && m.tara == null) {
                    Magazin max = magazine[0];
                    for(int i = 1; i < magazine.length; i++) {
                        if(magazine[i].getTotalCuTaxe() > 
                                magazine[i - 1].getTotalCuTaxe()) {
                            max = magazine[i];
                        }
                    }
                   afisare(max.getNume(), max.getTotalFaraTaxe(), 
                           max.getTotalCuTaxe(), max.getTotalCuTaxeScutite());
                } else if (m.tara!= null) {
                    Magazin max = magazine[0];
                    for(int i = 1; i < magazine.length; i++) {
                        if(magazine[i].getTotalTaraCuTaxe(m.tara) > 
                                magazine[i - 1].getTotalTaraCuTaxe(m.tara)) {
                            max = magazine[i];
                        }
                    }
                   afisare(max.getNume(), max.getTotalTaraFaraTaxe(m.tara), 
                           max.getTotalTaraCuTaxe(m.tara), 
                           max.getTotalTaraCuTaxeScutite(m.tara));

                } else  if(m.categorie != null) {
                    Magazin max = magazine[0];
                    for(int i = 1; i < magazine.length; i++) {
                        if(magazine[i].getTotalCategorieCuTaxe(m.categorie) > 
                                magazine[i - 1].getTotalCategorieCuTaxe(m.categorie)) {
                            max = magazine[i];
                        }
                    }
                   afisare(max.getNume(), max.getTotalCategorieFaraTaxe(m.categorie), 
                           max.getTotalCategorieCuTaxe(m.categorie), 
                           max.getTotalCategorieCuTaxeScutite(m.categorie));
                }
            } else if (m.type.compareTo("factura") == 0) {
                Factura max = null;
                
                for(Magazin i : magazine) {
                    Vector<Factura> facturi = i.getFacturi();
                    for(int j = 0 ; j < facturi.size(); j++) {
                        if ( max == null) {
                            max = facturi.get(j);
                            numeMagazin = i.getNume();
                        }
                        else if(max.getTotalFaraTaxe() <
                                facturi.get(j).getTotalFaraTaxe()) {
                            max = facturi.get(j);
                            numeMagazin = i.getNume();
                        }
                    }
                }
                afisare(numeMagazin + " : " + max.getDenumire() , 
                        max.getTotalFaraTaxe(), 
                        max.getTotalCuTaxe(), null);
            }
        }
    }
    
    public void afisare(String title, Double arg1, Double arg2, Double arg3) {
        afis.removeAll();
        JLabel titlul = new JLabel(title);
        Font f = new Font("Tudor2", Font.BOLD, new JLabel().getFont().getSize() + 12);
        titlul.setFont(f);
        afis.add(titlul);
        titlul.setBounds((500 - titlul.getPreferredSize().width)/2,
                90, titlul.getPreferredSize().width, titlul.getPreferredSize().height);
        
       if(arg3 != null) {
           JLabel jl1 = new JLabel("Total fara taxe");
           JLabel jl2 = new JLabel("Total cu taxe");
           JLabel jl3 = new JLabel("Total cu taxe scutite");
           
           JLabel jp1 = new JLabel(String.format("%.3f", arg1));
           JLabel jp2 = new JLabel(String.format("%.3f", arg2));
           JLabel jp3 = new JLabel(String.format("%.3f", arg3));
           
           int align;
           jl1.setBounds(50, 200, jl1.getPreferredSize().width,
                   jl1.getPreferredSize().height);
           align = jl1.getPreferredSize().width / 2 - 
                   jp1.getPreferredSize().width / 2;
           jp1.setBounds(50+align, 230, jl1.getPreferredSize().width,
                   jl1.getPreferredSize().height);
           
           jl2.setBounds(180, 200, jl2.getPreferredSize().width,
                   jl2.getPreferredSize().height);
           align = jl2.getPreferredSize().width / 2 - 
                   jp2.getPreferredSize().width / 2;
           jp2.setBounds(180 + align, 230, jl2.getPreferredSize().width,
                   jl2.getPreferredSize().height);
           
           jl3.setBounds(300, 200, jl3.getPreferredSize().width,
                   jl3.getPreferredSize().height);
           align = jl3.getPreferredSize().width / 2 - 
                   jp3.getPreferredSize().width / 2;
           jp3.setBounds(300 + align, 230, jl3.getPreferredSize().width,
                   jl3.getPreferredSize().height);
           
           afis.add(jl1); afis.add(jp1);
           afis.add(jl2); afis.add(jp2);
           afis.add(jl3); afis.add(jp3);
           afis.setVisible(false);
           afis.setVisible(true);
       } else {
           JLabel jl1 = new JLabel("Total fara taxe");
           JLabel jl2 = new JLabel("Total cu taxe");
           
           JLabel jp1 = new JLabel(String.format("%.3f", arg1));
           JLabel jp2 = new JLabel(String.format("%.3f", arg2));
           
           int align;
           jl1.setBounds(120, 200, jl1.getPreferredSize().width,
                   jl1.getPreferredSize().height);
           align = jl1.getPreferredSize().width / 2 - 
                   jp1.getPreferredSize().width / 2;
           jp1.setBounds(120+align, 230, jl1.getPreferredSize().width,
                   jl1.getPreferredSize().height);
           
           jl2.setBounds(290, 200, jl2.getPreferredSize().width,
                   jl2.getPreferredSize().height);
           align = jl2.getPreferredSize().width / 2 - 
                   jp2.getPreferredSize().width / 2;
           jp2.setBounds(290 + align, 230, jl2.getPreferredSize().width,
                   jl2.getPreferredSize().height);

           afis.add(jl1); afis.add(jp1);
           afis.add(jl2); afis.add(jp2);
           afis.setVisible(false);
           afis.setVisible(true);
       }
    }
}
