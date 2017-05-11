/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

import java.io.*;
import java.util.*;

/**
 *
 * @author zovippro1996
 */
public class test_file {

    public static void main(String[] args) {
        
       
        
        int n = 3;
        int[] type = new int[n];
        int[][] pos = new int[n][5];
        
        int i,j;
        
        int sum = 0;
        for (i=0 ; i<n; i++)
            for (j=0; j<5; j++)
            {
                pos[i][j] = sum;
                ++sum;
                System.out.printf("%d", pos[i][j]);
                
            }
        
        
        try {

            File dir = new File("tmp/save project");

            //Create New File
            File file = new File(dir, "tmp.txt");
            file.createNewFile();

            try {
                

                FileOutputStream out = new FileOutputStream(file);
                out.write(n);
                out.close();
                
            } catch (IOException e) {
                // do something
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
