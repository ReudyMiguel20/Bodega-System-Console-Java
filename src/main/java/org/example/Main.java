package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Warehouse bodegaPopular = new Warehouse();
        UserInterface UI = new UserInterface(bodegaPopular);

        UI.start();

        String poop = "damn";
        char[] charer = poop.toCharArray();


        for (int i = 0; i < charer.length; i++) {

        }

        /* Main Ideas */
        //Can implement a new main menu where it manages items and keep the other where it sells the items

        //The main idea is to have a feature to sell items and another to keep track of them and for system management
        //Maybe going to separate all three, and I can put the export-import file on system management

        //Idea - The options where it sells the items can recollect info about statistics on sales, and many more
    }
}