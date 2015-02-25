/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Tudor
 */
public class LoginScreen {
    JFrame screen;
    public LoginScreen() {
        screen = new JFrame();
        screen.setTitle("Tema POO");
        screen.setSize(500, 600);
        Font f = new Font("Tudor1", Font.BOLD, new JLabel().getFont().getSize() 
                + 12);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        screen.setLocation(dim.width/2-250, dim.height/2-300);
        
        screen.setLayout(null);
        
        JPanel jp0 = new JPanel();
        JLabel jl0 = new JLabel("Welcome!"); 
        jl0.setFont(f);
        
        JPanel jp1 = new JPanel();
        JTextField jtf1 = new JTextField(20);
        JLabel jl1 = new JLabel("Username:"); 
        
        JPanel jp2 = new JPanel();
        JPasswordField jtf2 = new JPasswordField(20);
        JLabel jl2 = new JLabel("Password:");
        
        jp0.add(jl0);
        jp1.add(jl1);
        jp1.add(jtf1);
        jp2.add(jl2);
        jp2.add(jtf2);
        
        screen.add(jp0);
        screen.add(jp1);
        screen.add(jp2);
        
        jp0.setBounds(180, 160 - jp0.getPreferredSize().height, 
                jp0.getPreferredSize().width, 
                jp0.getPreferredSize().height);
        
        jp1.setBounds(75, 220, 
                jp1.getPreferredSize().width, 
                jp1.getPreferredSize().height);
        
        jp2.setBounds(75, 220 + jp1.getPreferredSize().height, 
                jp2.getPreferredSize().width, 
                jp2.getPreferredSize().height);
        
        JButton jb = new JButton("Login");
        jb.setBounds(80, jp2.getY() + jp2.getSize().height + 30, 
                jb.getPreferredSize().width,  jb.getPreferredSize().height);
        jb.addActionListener(new LoginListener());
        screen.add(jb);
        
        JLabel jl3 = new JLabel("Hint: Orice combinatie merge");
        f = new Font("Tudor2", Font.PLAIN, 9);
        jl3.setFont(f);
        jl3.setBounds(75, 520, jl3.getPreferredSize().width, 
                jl3.getPreferredSize().height);
        screen.add(jl3);
        
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setVisible(true);
    }
        

    private class LoginListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            screen.setVisible(false);
            MainScreen ms = MainScreen.getInstance();
        }
        
        public String toString() {
            return "Acest listener asculta apasarea butonului Login";
        }
    }
    
    public String toString() {
            return "Aceasta clasa creeaza ecranul de login";
    }
}
