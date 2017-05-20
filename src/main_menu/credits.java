/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.text.*;

/**
 *
 * @author zovippro1996
 */
public class credits extends JFrame implements ActionListener {

    String trung_contribute = "- Initialize Concepts and Functionalities\n- Coordinator/Progress Tracker\n- Documentation and Presentation\n- Design/Implement UI and UX\n- Resources Gathering\n- Files and Projects Manipulation";
    String tu_contribute = "- Technique Researcher\n- Handle Working Environment Including:\n  +Ground Initialize\n  +Object Rendering\n  +Transforming Function (Add/Move/Rotate/Replace/Remove)";

    public credits() {
        //disable resize
        setResizable(false);
        JPanel pnlButton = new JPanel();

        //make sure the program exits when the frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set Title Bar
        setTitle("Credits");

        //Set size
        setSize(1366, 768);

        //This will center the JFrame in the middle of the screen
        setLocationRelativeTo(null);
        pnlButton.setLayout(null);

        //URL of background image
        URL url = getClass().getClassLoader().getResource("main_menu/resources/images/main-menu-bg.jpg");
        JLabel background = new JLabel(new ImageIcon(url));

        //Set size background = size Frame
        background.setBounds(0, 0, 1366, 768);

        StyleContext context = new StyleContext();
        StyledDocument trung_document = new DefaultStyledDocument(context);
        StyledDocument tu_document = new DefaultStyledDocument(context);
        
        //Text Style in Textpane
        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        //FontSize
        StyleConstants.setFontSize(style, 14);
        //Indent Between Lines
        StyleConstants.setSpaceAbove(style, 4);
        StyleConstants.setSpaceBelow(style, 4);

        // Insert content
        try {
            trung_document.insertString(trung_document.getLength(), trung_contribute, style);
            tu_document.insertString(tu_document.getLength(), tu_contribute, style);
        } catch (BadLocationException badLocationException) {
            System.err.println("Oops");
        }

        //Trung Content Pane
        JTextPane trung_Pane = new JTextPane(trung_document);
        trung_Pane.setEditable(false);
        JScrollPane trung_scrollPane = new JScrollPane(trung_Pane);
        trung_scrollPane.setBounds(260, 320, 300, 165);
        pnlButton.add(trung_scrollPane);

        
        //Tu Content Pane
        JTextPane tu_Pane = new JTextPane(tu_document);
        tu_Pane.setEditable(false);
        JScrollPane tu_scrollPane = new JScrollPane(tu_Pane);
        tu_scrollPane.setBounds(760, 320, 300, 165);
        pnlButton.add(tu_scrollPane);
        
        
        
        //Add Back to Main Menu Button to Credit
        JButton back_button = new JButton("Back to Main");
        back_button.addActionListener(this);
        back_button.setActionCommand("Back");
        back_button.setBounds(50, 650, 200, 50);

        //Add Button to the background
        pnlButton.add(back_button);
        pnlButton.add(background);

        //Add Panel to the Main Menu
        //add PanelButton to the Frame
        add(pnlButton);

        //Make the Credits Frame Visible
        setVisible(true);
    }

    //Set action when receive command from Buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        //Action for Back Button
        if (action.equals("Back")) {
            this.dispose();
            main_menu main = new main_menu();
        }

    }

}
