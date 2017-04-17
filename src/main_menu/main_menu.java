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

public class main_menu extends JFrame implements ActionListener {
    
    //Note: Typically the main method will be in a
    //separate class. As this is a simple one class
    //example it's all in the one class.
    public static void main(String[] args) {
        
        //Call out the main_menu Constructor
        new main_menu();
    }

    public main_menu()
    {
        JPanel pnlButton = new JPanel();
        
        //make sure the program exits when the frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setSize(860,590);
        
        //This will center the JFrame in the middle of the screen
        setLocationRelativeTo(null);
        pnlButton.setLayout(null);
    
        //Add Create Project Button to the Main Menu
        JButton create_button = new JButton("Create Project");
        create_button.addActionListener(this);
        create_button.setActionCommand("New");
        create_button.setBounds(330,100,200,50);
        
        //Add Load Button to the Main Mennu
        JButton load_button = new JButton("Load Project");
        load_button.addActionListener(this);
        load_button.setActionCommand("Load");
        load_button.setBounds(330, 175, 200, 50);
        
        //Add Credit Button to the Main Menu
        JButton credits_button = new JButton("Credits");
        credits_button.addActionListener(this);
        credits_button.setActionCommand("Credits");
        credits_button.setBounds(330, 250, 200, 50);
        
        //Initialize Exit Button
        JButton exit_button = new JButton("Exit");
        exit_button.addActionListener(this);
        exit_button.setActionCommand("Exit");
        exit_button.setBounds(330, 325, 200, 50);
        
        //Add 4 initialize Button to the Panel
        pnlButton.add(create_button);
        pnlButton.add(load_button);
        pnlButton.add(credits_button);
        pnlButton.add(exit_button);
        
        
        //Add Panel to the Main Menu
        add(pnlButton);
      
        //Make the Main Menu Appear
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
	        if (action.equals("New")) {
	            System.out.println("Not Support Yet");}
                
                else if (action.equals("Load")) {
                    System.out.print("Not support yet");
                }
                
                else if (action.equals("Credits")) {
                    System.out.print("A Dedicated Project from Computer Graphic Course by \n Pham Le Trung \n Le Van Tu");
                }
                
                else if (action.equals("Exit")){
                    System.exit(0);
                }
    }
    
}