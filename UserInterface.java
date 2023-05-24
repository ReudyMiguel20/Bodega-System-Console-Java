package org.example;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Warehouse warehouse;

    public UserInterface(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean userWantToExit = false;

        System.out.println("Welcome to the Bodega System v0.1");
        while (!userWantToExit) {
            commandList();
            switch (scanner.nextLine()) {
                case "1" -> {
                    this.warehouse.itemManagementList();
                }
                case "2" -> {

                }
            }
        }
    }

    public static void commandList() {
        System.out.println("""
                
                ===Command List===
                1 - Manage Items
                2 - Inventory Management""");
    }



}


