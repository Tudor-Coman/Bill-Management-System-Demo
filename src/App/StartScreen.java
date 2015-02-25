/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Tudor
 */
public class StartScreen extends JPanel{
    private JButton gButton;
    private JTextField tf1, tf2, tf3, tf6;
    
    private File produseFile, taxeFile, facturiFile, outFile;

    private JButton bf1, bf2, bf3, bf6;      
    
    private JLabel jl5;
    
    public StartScreen() {
    
        setLayout(null);
        
        gButton = new JButton("Gestionare");
        
        JLabel jl1 = new JLabel("Selectare fisiere: ");
        
        JLabel jl2 = new JLabel("facturi.txt");
        JLabel jl3 = new JLabel("taxe.txt   ");
        JLabel jl4 = new JLabel("produse.txt");
        JLabel jl6 = new JLabel("out.txt    ");
        
        tf1 = new JTextField(20);
        tf2 = new JTextField(20);
        tf3 = new JTextField(20);
        tf6 = new JTextField(20);
        
        bf1 = new JButton();
        bf2 = new JButton();
        bf3 = new JButton();
        bf6 = new JButton();
        
        bf1.addActionListener(new MyFileListener());
        bf2.addActionListener(new MyFileListener());
        bf3.addActionListener(new MyFileListener());
        bf6.addActionListener(new MyFileListener());
        
        bf1.setIcon(UIManager.getIcon("FileView.fileIcon"));
        bf2.setIcon(UIManager.getIcon("FileView.fileIcon"));
        bf3.setIcon(UIManager.getIcon("FileView.fileIcon"));
        bf6.setIcon(UIManager.getIcon("FileView.fileIcon"));
        
        File workingDir = new File(".").getAbsoluteFile();
        File[] files = workingDir.listFiles();
        
        for(File i : files) {
            if(i.getName().compareTo("facturi.txt") == 0) {
                tf1.setText(i.getAbsolutePath());
            }
            if(i.getName().compareTo("taxe.txt") == 0) {
                tf2.setText(i.getAbsolutePath());
            }
            if(i.getName().compareTo("produse.txt") == 0) {
                tf3.setText(i.getAbsolutePath());
            }
        }
        tf6.setText(workingDir.getParentFile().getAbsolutePath());
        
        add(jl1);
        jl1.setBounds(60, 120, jl1.getPreferredSize().width, 
                jl1.getPreferredSize().height);
        add(jl2);
        jl2.setBounds(60, 190, jl2.getPreferredSize().width, 
                jl2.getPreferredSize().height);
        add(jl3);
        jl3.setBounds(60, 200 + jl2.getHeight(), jl3.getPreferredSize().width, 
                jl3.getPreferredSize().height);
        add(jl4);
        jl4.setBounds(60, 210 + jl2.getHeight() + jl3.getHeight(), 
                jl4.getPreferredSize().width, jl4.getPreferredSize().height);
        add(jl6);
        jl6.setBounds(60, 220 + jl2.getHeight() + jl3.getHeight() + 
                jl4.getHeight(), jl6.getPreferredSize().width, 
                jl6.getPreferredSize().height);
        
        int nextOffset = 80 + jl4.getWidth();
        
        add(tf1);
        tf1.setBounds(nextOffset, 190, tf1.getPreferredSize().width, 
                tf1.getPreferredSize().height);
        add(tf2);
        tf2.setBounds(nextOffset, 195 + tf1.getHeight(), 
                tf2.getPreferredSize().width, tf2.getPreferredSize().height);
        add(tf3);
        tf3.setBounds(nextOffset, 200 + tf1.getHeight() + tf2.getHeight(), 
                tf3.getPreferredSize().width, tf3.getPreferredSize().height);
        add(tf6);
        tf6.setBounds(nextOffset, 205 + tf1.getHeight() + tf2.getHeight() + 
                tf3.getHeight(), tf6.getPreferredSize().width, 
                tf6.getPreferredSize().height);
        
        nextOffset += tf3.getWidth();
        int buttonSize = tf1.getHeight();
        
        add(bf1);          
        bf1.setBounds(nextOffset, 190, buttonSize, buttonSize);
        add(bf2);
        bf2.setBounds(nextOffset, 195 + bf1.getHeight(), 
                buttonSize, buttonSize);
        add(bf3);
        bf3.setBounds(nextOffset, 200 + bf1.getHeight() + bf2.getHeight(), 
                buttonSize, buttonSize);
        add(bf6);
        bf6.setBounds(nextOffset, 205 + bf1.getHeight() + bf2.getHeight() + 
                bf3.getHeight(), buttonSize, buttonSize);
        
        add(gButton);
        gButton.setBounds(60, 350, gButton.getPreferredSize().width, 
                gButton.getPreferredSize().height);
        gButton.addActionListener(new MyActionListener());
        
        jl5 = new JLabel();
        add(jl5);
        jl5.setVisible(false);
        jl5.setForeground(Color.red);
        
        
    }
    
    private class MyFileListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser jf = new JFileChooser();
            jf.setCurrentDirectory(new File("."));
            jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int returnVal = jf.showOpenDialog(null);
            File f = jf.getSelectedFile();
            JButton source = (JButton)e.getSource();
            
            if (f != null) {
                if(source == bf1)
                    tf1.setText(f.getPath());
                if(source == bf2)
                    tf2.setText(f.getPath());
                if(source == bf3)
                    tf3.setText(f.getPath());
                if(source == bf6) {
                    tf6.setText(f.getPath());
                }
            }
        }
    }
    
    private class MyActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ArrayList<String> al = new ArrayList<>();
            if (tf1.getText().endsWith("facturi.txt"))
                facturiFile = new File(tf1.getText());
            else
                al.add("facturi.txt");
            if (tf2.getText().endsWith("taxe.txt"))
                taxeFile = new File(tf2.getText());
            else
                al.add("taxe.txt");
            if (tf3.getText().endsWith("produse.txt"))
                produseFile = new File(tf3.getText());
            else
                al.add("produse.txt");
            
            if (new File(tf6.getText()).isDirectory())
                outFile = new File(tf6.getText());
            else
                al.add("out.txt");
            
            if (al.isEmpty()){
                Gestiune.getInstance(produseFile, taxeFile, facturiFile, outFile);
                MainScreen.getInstance().tabbedPanes();
                MainScreen.getInstance().tabbedSatus(true);
                MainScreen.getInstance().tabbedSelector(1);
                jl5.setVisible(false);
            }
            else {
                StringBuilder sb = new StringBuilder();
                if(al.size() == 1) {
                    sb.append("Fisier ");
                    sb.append(al.get(0));
                    for(int i = 1; i < al.size(); i++)
                        sb.append(", ").append(al.get(i));
                    sb.append(" necorespunzator");
                } else {
                    sb.append("Fisiere ");
                    sb.append(al.get(0));
                    for(int i = 1; i < al.size(); i++)
                        sb.append(", ").append(al.get(i));
                    sb.append(" necorespunzatoare");
                }
                jl5.setText(sb.toString());
                jl5.setBounds(60, 450, jl5.getPreferredSize().width, 
                                                jl5.getPreferredSize().height);
                jl5.setVisible(true);
            }
        }
    }
}
