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

    public credits() {
        JPanel pnlButton = new JPanel();

        //make sure the program exits when the frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Credits");
        setSize(860, 590);

        //This will center the JFrame in the middle of the screen
        setLocationRelativeTo(null);
        pnlButton.setLayout(null);

        //Add Back to Main Menu Button to Credit
        JButton back_button = new JButton("Back to Main");
        back_button.addActionListener(this);
        back_button.setActionCommand("Back");
        back_button.setBounds(330,100,200,50);
        
        pnlButton.add(back_button);
        //Add Panel to the Main Menu
        add(pnlButton);

        //Make the Main Menu Appear
        setVisible(true);
    }

    //Set action when receive command from Buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        //Action for Create Button
        if (action.equals("Back")) {
            this.dispose();
            main_menu main = new main_menu();
        }

    }

}
