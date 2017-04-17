/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class main_menu extends JFrame {
    
    //Note: Typically the main method will be in a
    //separate class. As this is a simple one class
    //example it's all in the one class.
    public static void main(String[] args) {
        
        new main_menu();
    }

    public main_menu()
    {
        JPanel pnlButton = new JPanel();
        
        //make sure the program exits when the frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setSize(860,640);
        //This will center the JFrame in the middle of the screen
        setLocationRelativeTo(null);
        pnlButton.setLayout(null);
    
        JButton create_button = new JButton("Create Project");
        create_button.setBounds(330,100,200,50);
        
        JButton load_button = new JButton("Load Project");
        load_button.setBounds(330, 175, 200, 50);
        
        JButton credits_button = new JButton("Credits");
        credits_button.setBounds(330, 250, 200, 50);
        
        JButton exit_button = new JButton("Exit");
        exit_button.setBounds(330, 325, 200, 50);
        
   
        pnlButton.add(create_button);
        pnlButton.add(load_button);
        pnlButton.add(credits_button);
        pnlButton.add(exit_button);
        
        add(pnlButton);      
        setVisible(true);
    }
    
}