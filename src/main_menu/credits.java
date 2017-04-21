/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.ImageIcon;

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
        setSize(1366, 768);

        //This will center the JFrame in the middle of the screen
        setLocationRelativeTo(null);
        pnlButton.setLayout(null);

        //URL of background image
        URL url = getClass().getClassLoader().getResource("main_menu/resources/images/main-menu-bg.jpg");
        JLabel background = new JLabel(new ImageIcon(url));
        background.setBounds(0, 0, 1366, 768);

        //Add Back to Main Menu Button to Credit
        JButton back_button = new JButton("Back to Main");
        back_button.addActionListener(this);
        back_button.setActionCommand("Back");
        back_button.setBounds(85, 600, 200, 50);

        pnlButton.add(back_button);
        pnlButton.add(background);
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
