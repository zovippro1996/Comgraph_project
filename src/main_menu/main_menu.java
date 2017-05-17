/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main_menu extends JFrame implements ActionListener {

    JPanel pnlButton = new JPanel();

    //Attribute as 4 Button
    JButton create_button = new JButton("Create Project");
    JButton load_button = new JButton("Load Project");
    JButton credits_button = new JButton("Credits");
    JButton exit_button = new JButton("Exit");

    public static void main(String[] args) {

        //Call out the main_menu Constructor
        main_menu main_1 = new main_menu();
        main_1.setResizable(false);
    }

    public main_menu() {

        //make sure the program exits when the frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setSize(1366, 768);

        //This will center the JFrame in the middle of the screen
        setLocationRelativeTo(null);

        //Get allign Button
        pnlButton.setLayout(null);

        //URL of background image
        URL url = getClass().getClassLoader().getResource("main_menu/resources/images/main-menu-bg.jpg");
        JLabel background = new JLabel(new ImageIcon(url));
        background.setBounds(0, 0, 1366, 768);

        //Add Create Project Button to the Main Menu
        create_button.addActionListener(this);
        create_button.setActionCommand("Create");
        create_button.setBounds(583, 200, 200, 50);

        //Add Load Button to the Main Mennu
        load_button.addActionListener(this);
        load_button.setActionCommand("Load");
        load_button.setBounds(583, 300, 200, 50);

        //Add Credit Button to the Main Menu
        credits_button.addActionListener(this);
        credits_button.setActionCommand("Credits");
        credits_button.setBounds(583, 400, 200, 50);

        //Initialize Exit Button
        exit_button.addActionListener(this);
        exit_button.setActionCommand("Exit");
        exit_button.setBounds(583, 500, 200, 50);

        //Add 4 initialize Button to the Panel
        pnlButton.add(create_button);
        pnlButton.add(load_button);
        pnlButton.add(credits_button);
        pnlButton.add(exit_button);

        //Add Background
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
        if (action.equals("Create")) {

            App app_1 = new App();
            app_1.setVisible(true);

            this.dispose();

        } //Action for Load Button
        else if (action.equals("Load")) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    Scanner s = new Scanner(selectedFile);

                    //Get Building Array
                    ArrayList<String> list = new ArrayList<String>();
                    while (s.hasNext()) {
                        list.add(s.next());
                    }
                    s.close();
                } catch (IOException ex) {
                    Logger.getLogger(main_menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.dispose();

        } //Action for Credits Button
        else if (action.equals("Credits")) {
            credits credit_main = new credits();
            credit_main.setVisible(true);
            this.dispose();

        } //Action for Exit Button
        else if (action.equals("Exit")) {
            System.exit(0);
        }
    }

}
