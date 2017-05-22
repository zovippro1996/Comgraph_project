/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

import javax.swing.*;
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

    /* --------Attributes--------------- */
    //JPanel for wrapper
    JPanel pnlButton = new JPanel();

    //Content of Contribution
    String trung_contribute = "- Initialize Concepts and Functionalities\n- Coordinator/Progress Tracker\n- Documentation and Presentation\n- Design/Implement UI and UX\n- Resources Gathering\n- Files and Projects Manipulation";
    String tu_contribute = "- Technique Researcher\n- Handle Working Environment Including:\n  +Ground Initialize\n  +Object Rendering\n  +Transforming Function (Add/Move/Rotate/Replace/Remove)";

    //URL of background image
    URL url = getClass().getClassLoader().getResource("main_menu/resources/images/main-menu-bg.jpg");
    JLabel background = new JLabel(new ImageIcon(url));

    //Trung Ava
    URL url_trung = getClass().getClassLoader().getResource("main_menu/resources/images/Trung.png");
    JLabel trung_ava = new JLabel(new ImageIcon(url_trung));

    //Tu Ava
    URL url_tu = getClass().getClassLoader().getResource("main_menu/resources/images/Tu.png");
    JLabel tu_ava = new JLabel(new ImageIcon(url_tu));
    
    //Style for text in credits, detail in constructor
    StyleContext context = new StyleContext();
    StyledDocument trung_document = new DefaultStyledDocument(context);
    StyledDocument tu_document = new DefaultStyledDocument(context);

    //Trung Content Pane
    JTextPane trung_Pane = new JTextPane(trung_document);

    //Tu Content Pane
    JTextPane tu_Pane = new JTextPane(tu_document);

    //Back to Menu Button
    JButton back_button = new JButton("Back to Main");

    /*----------Method---------*/
    //Constructor
    public credits() {
        //Disable resize
        setResizable(false);

        //Make sure the program exits when the frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Credits");
        setSize(1366, 768);

        //This will center the JFrame in the middle of the screen
        setLocationRelativeTo(null);
        
        pnlButton.setLayout(null);

        //Set size background = size Frame
        background.setBounds(0, 0, 1366, 768);

        trung_ava.setBounds(345, 200, 120, 120);
        pnlButton.add(trung_ava);

        tu_ava.setBounds(845, 185, 120, 120);
        pnlButton.add(tu_ava);

        //Text Style in Textpane
        Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
        //FontSize
        StyleConstants.setFontSize(style, 14);
        //Indent Between Lines
        StyleConstants.setSpaceAbove(style, 4);
        StyleConstants.setSpaceBelow(style, 4);

        // Insert content to TextPane
        try {
            trung_document.insertString(trung_document.getLength(), trung_contribute, style);
            tu_document.insertString(tu_document.getLength(), tu_contribute, style);
        } catch (BadLocationException badLocationException) {
            System.err.println("Oops");
        }

        //Setting for trung_Pane
        trung_Pane.setEditable(false);
        JScrollPane trung_scrollPane = new JScrollPane(trung_Pane);
        trung_scrollPane.setBounds(260, 320, 300, 165);
        pnlButton.add(trung_scrollPane);

        //Setting for tu_Pane
        tu_Pane.setEditable(false);
        JScrollPane tu_scrollPane = new JScrollPane(tu_Pane);
        tu_scrollPane.setBounds(760, 320, 300, 165);
        pnlButton.add(tu_scrollPane);

        
        //Setting for BackButton
        back_button.addActionListener(this);
        back_button.setActionCommand("Back");
        back_button.setBounds(50, 650, 200, 50);

        //Add Button to the background
        pnlButton.add(back_button);
        pnlButton.add(background);

        //Add PanelButton to the Frame
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
