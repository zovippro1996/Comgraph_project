/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author zovippro1996
 */

public class credits extends JFrame implements ActionListener {
    
    public credits()
    {
        JPanel pnlButton = new JPanel();
        
        //make sure the program exits when the frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Credits");
        setSize(860,590);
        
        
        //This will center the JFrame in the middle of the screen
        setLocationRelativeTo(null);
        pnlButton.setLayout(null);
        
        
        //Add Panel to the Main Menu
        add(pnlButton);
      
        //Make the Main Menu Appear
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
