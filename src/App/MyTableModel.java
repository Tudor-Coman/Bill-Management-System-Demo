/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tudor
 */
public class MyTableModel extends DefaultTableModel{

    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        return false;
    }
}
