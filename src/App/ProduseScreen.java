/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Tudor
 */
public class ProduseScreen extends JPanel{
    JScrollPane jsp;
    JTable produseTable;
    private ArrayList<Produs> produse;
    private MyTableModel dtm;
    private ArrayList<String> headers;
    private Comparator currentComparator;
    
    public ProduseScreen() {
        
        this.setLayout(new BorderLayout());
        produse = Gestiune.getInstance().getProduse();
        produse = new ArrayList<>(produse);
        headers = new ArrayList<>();
        
        headers.add("Tara");
        headers.add("Nume");
        headers.add("Categorie");
        headers.add("Pret");
        
        showProduse(null);
        
        produseTable = new JTable(dtm);
        JPopupMenu menu = new JPopupMenu();
        JMenuItem item = new JMenuItem("Cauta");
        item.addActionListener(new FourthScreenListener());
        menu.add(item);
        produseTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
              if (evt.isPopupTrigger()) {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
              }
            }

            public void mouseReleased(MouseEvent evt) {
              if (evt.isPopupTrigger()) {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
              }
            }
          });
        jsp = new JScrollPane(produseTable);
        JButton adaugare = new JButton("Adauga Produs");
        
        JButton op1 = new JButton("Adauga");
        JButton op2 = new JButton("Anulare");
        
        adaugare.addActionListener(new adaugareListener(op1, op2));
        add(jsp, BorderLayout.CENTER);
        add(adaugare, BorderLayout.SOUTH);
        
        produseTable.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int col = produseTable.columnAtPoint(e.getPoint());
                String name = produseTable.getColumnName(col);
                if (name.compareTo("Tara") == 0){
                    sortByCountry.way = !sortByCountry.way;
                    currentComparator = sortByCountry;
                    ((ProduseScreen)jsp.getParent())
                            .showProduse(currentComparator);
                    }
                if (name.compareTo("Nume") == 0){
                    sortByNume.way = !sortByNume.way;
                    currentComparator = sortByNume;
                    ((ProduseScreen)jsp.getParent())
                            .showProduse(currentComparator);
                }
                if (name.compareTo("Categorie") == 0){
                    sortByCategorie.way = !sortByCategorie.way;
                    currentComparator = sortByCategorie;
                    ((ProduseScreen)jsp.getParent())
                            .showProduse(currentComparator);
                }
                if (name.compareTo("Pret") == 0) {
                    sortByPret.way = !sortByPret.way;
                    currentComparator = sortByPret;
                    ((ProduseScreen)jsp.getParent())
                            .showProduse(currentComparator);
                }
            }
        });
        produseTable.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() > 1){
                    int row = produseTable.rowAtPoint(e.getPoint());
                    JButton op1 = new JButton("Modifica");
                    JButton op2 = new JButton(((Integer)row).toString());
                    new adaugareListener(op1, op2).actionPerformed(null);
                }
            }
        });
    }
    
    public void showProduse (Comparator c) {
        
        if ( dtm != null)
            while(dtm.getRowCount() != 0)
                dtm.removeRow(0);
        else {
            dtm = new MyTableModel();
            for(String s: headers) 
                dtm.addColumn(s);
        }
            
        if (c != null)
            produse.sort(c);
        for(Produs p : produse) {
            Object[] data = new Object[headers.size()];
            data[0] = p.getTaraOrigine();
            data[1] = p.getDenumire();
            data[2] = p.getCategorie();
            data[3] = p.getPret();
            dtm.addRow(data);
        }
    }

    
    MyComparator sortByCountry  = new MyComparator() {
        
        public int compare(Object arg0, Object arg1) {
            Produs p1 = (Produs)arg0;
            Produs p2 = (Produs)arg1;
            if(way == false)
                return p1.getTaraOrigine().compareTo(p2.getTaraOrigine());
            else
                return -p1.getTaraOrigine().compareTo(p2.getTaraOrigine());
        }
    };
    
    MyComparator sortByNume = new MyComparator(){
        @Override
        public int compare(Object arg0, Object arg1) {
            Produs p1 = (Produs)arg0;
            Produs p2 = (Produs)arg1;
            if(way == false)
                return p1.getDenumire().compareTo(p2.getDenumire());
            else
                return -p1.getDenumire().compareTo(p2.getDenumire());
        }
    };
    
    MyComparator sortByCategorie = new MyComparator(){
        @Override
        public int compare(Object arg0, Object arg1) {
            Produs p1 = (Produs)arg0;
            Produs p2 = (Produs)arg1;
            if(way == false)
                return p1.getCategorie().compareTo(p2.getCategorie());
            else
                return -p1.getCategorie().compareTo(p2.getCategorie());
        }
    };
    
    MyComparator sortByPret = new MyComparator(){
        @Override
        public int compare(Object arg0, Object arg1) {
            Produs p1 = (Produs)arg0;
            Produs p2 = (Produs)arg1;
            if(way == false)
                return (int)(p1.getPret()*100 - p2.getPret()*100);
            else
                return (int)(p2.getPret()*100 - p1.getPret()*100);
        }
    };
    
    private class adaugareListener implements ActionListener {
        JFrame secondScreen;
        JTextField jtf1;
        JTextField jtf2;
        JTextField jtf3;
        JSpinner jtf4;
        JButton op1, op2;
        int row;
        boolean preset = false;
        Produs vechi;
        
        public adaugareListener(JButton op1, JButton op2) {
            this.op1 = op1;
            this.op2 = op2;
            
            if(op1.getText().compareTo("Adauga") == 0) {
                op1.addActionListener(new SecondScreenListener());
                op2.addActionListener(new SecondScreenListener());
            } else {
                row = Integer.parseInt(op2.getText());
                op2.setText("Sterge");
                preset = true;
                op1.addActionListener(new ThirdScreenListener());
                op2.addActionListener(new ThirdScreenListener());
            }
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            secondScreen = new JFrame();
            secondScreen.setVisible(false);
            secondScreen.setVisible(true);
            
            secondScreen.setSize(300, 200);
            secondScreen.setVisible(true);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            secondScreen.setLocation(dim.width/2-150, dim.height/2-100);
            
            if(preset) {
                vechi = new Produs();
                vechi.setDenumire(((String)
                        ((Vector)dtm.getDataVector().elementAt(row)).get(1)));
                vechi.setCategorie(((String)
                        ((Vector)dtm.getDataVector().elementAt(row)).get(2)));
                vechi.setTaraOrigine((String)
                        ((Vector)dtm.getDataVector().elementAt(row)).get(0));
                vechi.setPret(((Double)
                        ((Vector)dtm.getDataVector().elementAt(row)).get(3)));
            }
            
            JPanel jp1 = new JPanel();
            jtf1 = new JTextField(10);
            if(preset) {
                jtf1.setText(vechi.getDenumire());
                if( Gestiune.getInstance().checkOriginality(vechi))
                    jtf1.setEditable(false);
            }
            jtf1.setHorizontalAlignment(JTextField.RIGHT);
            JLabel jl1 = new JLabel("Denumire:"); 

            JPanel jp2 = new JPanel();
            jtf2 = new JTextField(10);
            if(preset) {
                jtf2.setText(vechi.getCategorie());
                if( Gestiune.getInstance().checkOriginality(vechi))
                    jtf2.setEditable(false);
            }
            jtf2.setHorizontalAlignment(JTextField.RIGHT);
            JLabel jl2 = new JLabel("Categorie:");

            JPanel jp3 = new JPanel();
            jtf3 = new JTextField(10);
            if(preset) {
                jtf3.setText(vechi.getTaraOrigine());
                if( Gestiune.getInstance().checkOriginality(vechi))
                    jtf3.setEditable(false);
            }
            jtf3.setHorizontalAlignment(JTextField.RIGHT);
            JLabel jl3 = new JLabel("Tara:"); 
            jl3.setPreferredSize(jl1.getPreferredSize());

            JPanel jp4 = new JPanel();
            SpinnerNumberModel model = new SpinnerNumberModel(0.0,
                    -Double.MAX_VALUE, Double.MAX_VALUE, 0.01);
            model.setStepSize(0.01);
            
            jtf4 = new JSpinner(model);
            jtf4.setPreferredSize(jtf1.getPreferredSize());
            if(preset) {
                jtf4.setValue(vechi.getPret());
            }
            JLabel jl4 = new JLabel("Pret:");
            jl4.setPreferredSize(jl1.getPreferredSize());
            
            JPanel jp5 = new JPanel();

            jp1.add(jl1);
            jp1.add(jtf1);
            jp2.add(jl2);
            jp2.add(jtf2);
            jp3.add(jl3);
            jp3.add(jtf3);
            jp4.add(jl4);
            jp4.add(jtf4);
            jp5.add(op1);
            jp5.add(op2);

            secondScreen.add(jp1);
            secondScreen.add(jp2);
            secondScreen.add(jp3);
            secondScreen.add(jp4);
            secondScreen.add(jp5);
            secondScreen.setLayout(new BoxLayout(secondScreen.getContentPane(), 
                    BoxLayout.PAGE_AXIS));
            preset = false;
        }
        
        private class SecondScreenListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().compareTo("Anulare") == 0){
                    secondScreen.setVisible(false);
                    secondScreen.dispose();
                }
                if(e.getActionCommand().compareTo("Adauga") == 0) {
                    JButton source = (JButton)e.getSource();
                    
                    if(jtf1.getText().isEmpty() ||
                            jtf2.getText().isEmpty() ||
                            jtf3.getText().isEmpty() ||
                            (Double)jtf4.getValue() == 0.0 ){
                        
                        JOptionPane.showMessageDialog(source.getParent(),
                                        "Toate campurile trebuie completate", 
                                        "Warning!", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                    
                    Produs produsCurent = new Produs();
                    produsCurent.setDenumire(jtf1.getText());
                    produsCurent.setCategorie(jtf2.getText());
                    produsCurent.setTaraOrigine(jtf3.getText());
                    
                    double dvalue = (Double) jtf4.getValue();
                    produsCurent.setPret(dvalue);
                    for (Produs i: produse) {
                        if (i.equals(produsCurent)) {
                            
                            JOptionPane.showMessageDialog(source.getParent(),
                                    "Produs existent", "Warning!",
                                    JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                    
                    produse.add(produsCurent);
                    showProduse(currentComparator);
                    Gestiune.getInstance().addProduct(produsCurent);
                    secondScreen.setVisible(false);
                    secondScreen.dispose();
                }
            }
        }
        
        private class ThirdScreenListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton)e.getSource();
                boolean tryDelete = (Double)jtf4.getValue() == 0.0;
                if(e.getActionCommand().compareTo("Sterge") == 0 || 
                        tryDelete){
                    if( Gestiune.getInstance().checkOriginality(vechi)) {
                        JOptionPane.showMessageDialog(source.getParent(),
                                    "Acest produs a fost comandat", 
                                    "Warning!", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                }
                    
                if(jtf1.getText().isEmpty() ||
                        jtf2.getText().isEmpty() ||
                        jtf3.getText().isEmpty() ||
                        (Double)jtf4.getValue() == 0.0 ){

                    JOptionPane.showMessageDialog(source.getParent(),
                                    "Toate campurile trebuie completate", 
                                    "Warning!", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                for(Produs i : produse) {
                    if (i.equals(vechi)){
                        produse.remove(i);
                        vechi.setPret(0);
                        Gestiune.getInstance().addProduct(vechi);
                        break;
                    }
                }
                if(e.getActionCommand().compareTo("Modifica") == 0 && !tryDelete) {
                    Produs produsCurent = new Produs();
                    produsCurent.setDenumire(jtf1.getText());
                    produsCurent.setCategorie(jtf2.getText());
                    produsCurent.setTaraOrigine(jtf3.getText());
                    
                    double dvalue = (Double) jtf4.getValue();
                    produsCurent.setPret(dvalue);
                    if(produsCurent.getPret() > 0.0){
                        produse.add(produsCurent);
                        
                        Gestiune.getInstance().addProduct(produsCurent);
                    }
                }
                showProduse(currentComparator);
                secondScreen.setVisible(false);
                secondScreen.dispose();
            }
        }
    }
    
    private class FourthScreenListener implements ActionListener{
        JFrame secondScreen;
        JTextField jtf1, jtf2, jtf3;
        @Override
        public void actionPerformed(ActionEvent e) {
            secondScreen = new JFrame();
            secondScreen.setSize(300, 200);
            secondScreen.setVisible(true);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            secondScreen.setLocation(dim.width/2-150, dim.height/2-100);



            JPanel jp1 = new JPanel();
            jtf1 = new JTextField(10);

            jtf1.setHorizontalAlignment(JTextField.RIGHT);
            JLabel jl1 = new JLabel("Denumire:"); 

            JPanel jp2 = new JPanel();
            jtf2 = new JTextField(10);

            jtf2.setHorizontalAlignment(JTextField.RIGHT);
            JLabel jl2 = new JLabel("Categorie:");

            JPanel jp3 = new JPanel();
            jtf3 = new JTextField(10);
                    
            jtf3.setHorizontalAlignment(JTextField.RIGHT);
            JLabel jl3 = new JLabel("Tara:"); 
            jl3.setPreferredSize(jl1.getPreferredSize());


            JPanel jp5 = new JPanel();

            jp1.add(jl1);
            jp1.add(jtf1);
            jp2.add(jl2);
            jp2.add(jtf2);
            jp3.add(jl3);
            jp3.add(jtf3);

            JButton cauta = new JButton("Cauta");
            JButton anulare = new JButton("Anulare");

            cauta.addActionListener(new CautareListener());
            anulare.addActionListener(new CautareListener());
            jp5.add(cauta);
            jp5.add(anulare);

            secondScreen.add(jp1);
            secondScreen.add(jp2);
            secondScreen.add(jp3);
            secondScreen.add(jp5);
            secondScreen.setLayout(new BoxLayout(secondScreen.getContentPane(), 
                    BoxLayout.PAGE_AXIS));
        }

        private class CautareListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().compareTo("anulare") == 0) {
                    secondScreen.setVisible(false);
                    secondScreen.dispose();
                } else {
                    Produs p = new Produs();
                    p.setDenumire(jtf1.getText());
                    p.setCategorie(jtf2.getText());
                    p.setTaraOrigine(jtf3.getText());
                    for(int i = 0; i < produse.size(); i++) {
                        if(produse.get(i).equals(p))
                            produseTable.setRowSelectionInterval(i, i);
                    }
                    secondScreen.setVisible(false);
                    secondScreen.dispose();
                }
            }
        }
    }
}