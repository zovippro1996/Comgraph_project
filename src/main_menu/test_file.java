/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

import java.io.*;

/**
 *
 * @author zovippro1996
 */
public class test_file {

    public static void main(String[] args) {
        try {

            File dir = new File("tmp/save project");
            dir.mkdirs();
            
            //Create New File
            File file = new File(dir, "tmp.txt");
            file.createNewFile();
            
            FileInputStream in = new FileInputStream(file) ;
            FileOutputStream out = new FileOutputStream(file) ;

            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
