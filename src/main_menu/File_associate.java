/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_menu;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author zovippro1996
 */
public class File_associate {

    private int n;
    private int[][] building_matrix = new int[n][5];

    File file = new File("tmp/save project/tmp.txt");

    //Save Project function
    public void save_project() {
        try {

            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.printf("%d\n", n);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 5; j++) {
                    writer.printf("%d ", this.building_matrix[i][j]);
                }
                writer.println();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error writting file");
        }
    }

    //Load_Project function
    public void load_project() {
        try {

            Scanner sc = new Scanner(file);
            n = sc.nextInt();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 5; j++) {
                    this.building_matrix[i][j] = sc.nextInt();
                }

            }

            System.out.printf("%d\n", n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.printf("%d ", this.building_matrix[i][j]);
                }

                System.out.println();
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

}
