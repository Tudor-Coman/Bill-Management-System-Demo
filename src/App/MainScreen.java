/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;


import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Tudor
 */
class MainScreen {
    
    private static MainScreen main = null;
    
    public static MainScreen getInstance() {
        if (main == null)
            main = new MainScreen();
        return main;
    }
    
    private JTabbedPane tabbedPane;
    
    private MainScreen() {
        JFrame mainScreen = new JFrame();
        mainScreen.setTitle("Tema POO");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainScreen.setSize(500, 600);
        mainScreen.setLocation(dim.width/2-250, dim.height/2-300);
        
        tabbedPane = new JTabbedPane();

        JPanel startScreen = new StartScreen();
        tabbedPane.addTab("Start", startScreen);
        
        
        
        tabbedPane.addTab("Produse", null);
        tabbedPane.addTab("Statistici", null);
        
        
        tabbedPane.setEnabledAt(1, false);
        tabbedPane.setEnabledAt(2, false);
        
        tabbedPane.setSize(100, 100);
        mainScreen.add(tabbedPane);
        
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setVisible(true);
    }

    public void tabbedPanes() {
        tabbedPane.remove(2);
        tabbedPane.remove(1);
        
        JPanel produseScreen = new ProduseScreen();
        JPanel statisticiScreen = new StatisticiScreen();

        tabbedPane.addTab("Produse", produseScreen);
        tabbedPane.addTab("Statistici", statisticiScreen);
    }
    
    
    public void tabbedSatus(boolean value) {
        tabbedPane.setEnabledAt(1, value);
        tabbedPane.setEnabledAt(2, value);
    }
    
    public void tabbedSelector(int value) {
        tabbedPane.setSelectedIndex(value);
    }
    
    private JPanel produseScreenMaker() {
        JPanel screen = new JPanel();
        return screen;
    }

    private JPanel statisticiScreenMaker() {
        JPanel screen = new JPanel();
        return screen;
    }
}
